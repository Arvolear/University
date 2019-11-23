import random

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class Storage:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    def empty(self):
        return False if self.size > 0 else True

    def getSize(self):
        return self.size

    def push_back(self, data):
        newNode = Node(data)

        if self.empty():
            self.head = self.tail = newNode
        else:
            self.tail.next = newNode
            self.tail = self.tail.next

        self.size += 1

    def pop_back(self):
        if self.empty():
            return;
        elif self.getSize() == 1:
            self.head = None
            self.tail = None
        else:
            curNode = self.head
            while curNode.next.next is not None:
                curNode = curNode.next

            curNode.next = None
            self.tail = curNode

        self.size -= 1

    def push_front(self, data):
        newNode = Node(data)

        if self.empty():
            self.head = self.tail = newNode
        else:
            newNode.next = self.head
            self.head = newNode

        self.size += 1

    def pop_front(self):
        if self.empty():
            return;
        elif self.getSize() == 1:
            self.head = None
            self.tail = None
        else:
            self.head = self.head.next

        self.size -= 1

    def removeWords(self, letter):
        if self.empty():
            return

        while self.head and \
              self.head.data.find(letter, 0, len(self.head.data)) != -1:
            self.pop_front()

        while self.tail and \
              self.tail.data.find(letter, 0, len(self.tail.data)) != -1:
            self.pop_back()

        prevNode = None
        curNode = self.head;

        while curNode is not None:
            if curNode.data.find(letter, 0, len(curNode.data)) != -1:
                    prevNode.next = curNode.next
                    curNode = prevNode.next
                    self.size -= 1
                    continue

            prevNode = curNode
            curNode = curNode.next

    def extractSet(self, letter):
        storageSet = {}

        curNode = self.head;

        while curNode is not None:
            key = ""
            for i in range(0, len(curNode.data)):
                if curNode.data[i] == letter:
                    key += str(i)
                    key += ' '

            if key:
                if str(key) not in storageSet:
                    storageSet[str(key)] = []

                storageSet[str(key)].append(curNode.data)

            curNode = curNode.next

        if len(storageSet) - 1 < 0:
            return 0, ''

        randomSet = random.randint(0, len(storageSet) - 1)
        randomSet = max(0, randomSet)
        greatestVal = list(storageSet.keys())[randomSet]
        greatestSet = storageSet[greatestVal]

        # greatestSet = []
        # greatestVal = ""
        #
        # for i in storageSet:
        #     if not greatestVal or \
        #        len(storageSet[i]) > len(storageSet[greatestVal]):
        #         greatestVal = i
        #         greatestSet = storageSet[i]

        newStorage = Storage()
        for i in greatestSet:
            newStorage.push_back(i)

        return newStorage, str(greatestVal)

    def copyTo(self, newDict):
        curNode = self.head

        while curNode is not None:
            newDict.push_back(curNode.data);
            curNode = curNode.next

    def clear(self):
        self.head = None
        self.tail = None
        self.size = 0

    def show(self):
        curNode = self.head
        counter = 1

        print ("Size =", self.size)
        while curNode is not None:
            print ("[", curNode.data, "] ", end='')
            if counter % 8 == 0:
                print()

            curNode = curNode.next
            counter += 1

        print()
