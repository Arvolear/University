from storage import *
from Task import *

class HangMan:
    def __init__(self, length):
        self.word = ['_'] * length
        self.length = length
        self.wrong = 0
        self.storage = Storage()

        f = open("../Dictionary/length" + str(length) + ".txt", 'r')
        buff = f.read().splitlines()
        for i in buff:
            self.storage.push_back(i)

    def play(self):
        self.show('')

        while (True):
            # calling move function #
            symbol = move(self.word)

            self.performMove(symbol)

            self.show(symbol)
            #self.storage.show()

            if self.isWin() == -1:
                print("Murderer! (just kidding), better luck next time!\n")
                self.storage.show()
                return
            elif self.isWin() == 1:
                print("Congratulations! You have saved the hangman!\n")
                return

    def performMove(self, symbol):
        newStorage = Storage()
        self.storage.copyTo(newStorage)

        newStorage.removeWords(symbol)
        randomNum = random.randint(0, 100);

        # remove all string that have the letter #
        if not newStorage.empty() and randomNum >= 36:
            self.storage.clear()
            self.storage = newStorage
            self.wrong += 1
            return

        # else extract random set of string that have this letter #
        newStorage, positions = self.storage.extractSet(symbol)

        # if set is empty - bad luck #
        if positions == '':
            self.wrong += 1
            return

        self.storage = newStorage;
        indices = positions.split(' ');
        for i in indices:
            if i != ' ' and i != '':
                self.word[int(i)] = symbol

    def isWin(self):
        # amount to lose #
        if self.wrong >= 11:
            return -1

        for i in self.word:
            if i == '_':
                return 0
        return 1

    def show(self, symbol):
        print("Entered word length:", self.length)
        print("Current word state:", end=' ')
        for i in self.word:
            print(i, end='')
        print()
        print("Last entered symbol:", symbol)
        print("\n")

        f = open("../Pics/hangman" + str(self.wrong) + ".txt", 'r')
        buff = f.read().splitlines()
        for i in buff:
            print(i)

        print('\n##########################################################\n')

