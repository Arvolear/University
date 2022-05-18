import random

ALPHABET = 'йЙцЦуУкКеЕнНгГшШщЩзЗхХїЇфФіІвВаАпПрРоОлЛдДжЖєЄяЯчЧсСмМиИтТьЬбБюЮґҐ .,!?:;-'
MAX_GENERATIONS = 10000
POPULATION = 100
TOP_POPULATION = 80
MUTATION_PERCENTAGE = 0.1

# takes random alpha from the alphabet
def randomAlpha():
    return random.choice(ALPHABET)

# generates random genotype from random alphas
def randomGenotype(length):
    return [randomAlpha() for _ in range(length)]

class Entity:
    def __init__(self, genotype):
        self.genotype = genotype
        self.fitness = 0

    # function that calculates fitness of this entity the higher the fitness, the better the entity
    def calculateFitness(self, anticipation):
        self.fitness = 0

        for i in range(len(anticipation)):
            if self.genotype[i] == anticipation[i]:
                self.fitness += 1

    # breeds this entity with the provided partner
    def breed(self, partner):
        childGenotype = []

        for i in range(len(self.genotype)):
            # breeding point < 0.5, gen is taken from this entity
            if random.random() < 0.5:
                childGenotype.append(self.genotype[i])
            # breeding point >= 0.5, gen is taken from the partner entity
            else:
                childGenotype.append(partner.genotype[i])

            # mutation if threshold reached
            if random.random() <= MUTATION_PERCENTAGE:
                childGenotype[i] = randomAlpha()

        return Entity(childGenotype)

# taking top population% for breeding
# tested that this approach is the best
def getRandomEntity(population):
    return random.choice(population[TOP_POPULATION:])

# creates new population from the old population
def createNewPopulation(population):
    newPopulation = []

    # breed 2 entities. Ignore the case when entities are the same
    for _ in range(POPULATION):
        partner1 = getRandomEntity(population)
        partner2 = getRandomEntity(population)

        newPopulation.append(partner1.breed(partner2))

    return newPopulation

def main():
    generation = 1
    anticipation = input('Please input the anticipated genotype (in Ukrainian):\n')

    population = [Entity(randomGenotype(len(anticipation))) for _ in range(POPULATION)]

    # loop until the best entity genome mathes the anticipated genome
    while generation < MAX_GENERATIONS:
        for e in population:
            e.calculateFitness(anticipation)

        population = sorted(population, key = lambda e: e.fitness)
        
        print(f'Generation: {generation}, best genotype: {"".join(population[-1].genotype)}, best fitness: {population[-1].fitness}')

        # if best genome == anticipated genome
        if (population[-1].fitness == len(anticipation)):
            break

        population = createNewPopulation(population)
        generation += 1

    if (generation == MAX_GENERATIONS):
        print('Not successful')

if __name__ == '__main__':
    main()