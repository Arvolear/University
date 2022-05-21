import os
import sys
import numpy as np
import scipy.special as sc
from PIL import Image

DATASETS = ['one', 'three', 'five', 'ten', 'fifty']

INPUT_NODES = 1024 # 32x32 image
HIDDEN_NODES = 100
OUTPUT_NODES = len(DATASETS)
LEARNING_GRADE = 0.25
EPOCHS = 150

def loadImageData(path):
    image = Image.open(path).convert('L')

    data = np.array(image)
    data = (255 - data) * 0.99 + 0.01

    return data.flatten()

def loadDataset(name):
    imagesData = []
    path = f'datasets/{name}'

    for file in os.listdir(path):
        if not file.startswith('.'):
            imagesData.append(loadImageData(f'{path}/{file}'))

    return imagesData

class NeuralNetwork:
    def __init__(self, inputNodes, hiddenNodes, outputNodes, learningGrade):
        self.inputNodes = inputNodes
        self.hiddenNodes = hiddenNodes
        self.outputNodes = outputNodes

        self.weightsInputHidden = np.random.normal(0.0, pow(hiddenNodes, -0.5), (hiddenNodes, inputNodes))
        self.weightsHiddenOutput = np.random.normal(0.0, pow(outputNodes, -0.5), (outputNodes, hiddenNodes))

        self.activationFunction = lambda x: sc.expit(x)
        self.learningGrade = learningGrade

    def train(self, inputs, targets):
        inputs = np.array(inputs, ndmin=2).T
        targets = np.array(targets, ndmin=2).T

        hiddenInputs = np.dot(self.weightsInputHidden, inputs)
        hiddenOutputs = self.activationFunction(hiddenInputs)
        finalInputs = np.dot(self.weightsHiddenOutput, hiddenOutputs)
        finalOutputs = self.activationFunction(finalInputs)

        outputErrors = targets - finalOutputs
        hiddenErrors = np.dot(self.weightsHiddenOutput.T, outputErrors)

        self.weightsHiddenOutput += self.learningGrade * np.dot(
            outputErrors * finalOutputs * (1.0 - finalOutputs), 
            np.transpose(hiddenOutputs)
        )
        self.weightsInputHidden += self.learningGrade * np.dot(
            hiddenErrors * hiddenOutputs * (1.0 - hiddenOutputs), 
            np.transpose(inputs)
        )

    def query(self, inputs):
        inputs = np.array(inputs, ndmin=2).T

        hiddenInputs = np.dot(self.weightsInputHidden, inputs)
        hiddenOutputs = self.activationFunction(hiddenInputs)

        finalInputs = np.dot(self.weightsHiddenOutput, hiddenOutputs)
        finalOutputs = self.activationFunction(finalInputs)

        return finalOutputs

    def _save(self, where, what):
        with open(where, 'w') as file:
            for r in what:
                row = []
                for el in r:
                    row.append(str(el))
                file.write(f'{",".join(list(row))}\n')

    def _load(self, where):
        with open(where, 'r') as file:
            data = file.readlines()
            data = [r.strip() for r in data]
            data = [r.split(',') for r in data]
            data = [[float(el) for el in r] for r in data]
            return np.array(data)

    def save(self):
        self._save('network/weightsHiddenOutput.txt', self.weightsHiddenOutput)
        self._save('network/weightsInputHidden.txt', self.weightsInputHidden)

    def load(self):
        self.weightsHiddenOutput = self._load('network/weightsHiddenOutput.txt')
        self.weightsInputHidden = self._load('network/weightsInputHidden.txt')
    
def train():
    neuralNetwork = NeuralNetwork(INPUT_NODES, HIDDEN_NODES, OUTPUT_NODES, LEARNING_GRADE)

    for e in range(EPOCHS):
        print(f'Epoch {e}')

        for i, name in enumerate(DATASETS):
            targets = np.zeros(OUTPUT_NODES) + 0.01
            targets[i] = 0.99

            dataset = loadDataset(name)

            for imageData in dataset:
                neuralNetwork.train(imageData, targets)

    neuralNetwork.save()

def recognize():
    neuralNetwork = NeuralNetwork(INPUT_NODES, HIDDEN_NODES, OUTPUT_NODES, LEARNING_GRADE)
    neuralNetwork.load()

    for name in DATASETS:
        datasetPath = f'tests/{name}'

        for file in os.listdir(datasetPath):
            if not file.startswith('.'):
                imagePath = f'{datasetPath}/{file}'
                imageData = loadImageData(imagePath)

                result = neuralNetwork.query(imageData).flatten()
                bestIndex = int(np.argmax(result))

                print(f'Recognized element {imagePath} as {DATASETS[bestIndex]}')

def main():
    if len(sys.argv) != 2:
        print('Wrong number of arguments provided')

    if sys.argv[1] == 'train':
        train()
    elif sys.argv[1] == 'recognize':
        recognize()

if __name__ == '__main__':
    main()