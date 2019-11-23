import random

class BotNode:
    def __init__(self, data):
        self.data = data
        self.next = None

class BotStorage:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    def empty(self):
        return False if self.size > 0 else True

    def getSize(self):
        return self.size

    def push_back(self, data):
        newNode = BotNode(data)

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
        newNode = BotNode(data)

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

    def extractSet(self, word):
        newStorage = BotStorage()

        curNode = self.head

        while curNode is not None:
            fine = True
            letters = []

            for i in range(0, len(word)):
                if word[i] != '_':
                    letters.append(word[i])

            for i in range(0, len(word)):
                if word[i] != '_':
                    if curNode.data[i] != word[i]:
                        fine = False
                else:
                    for j in range(0, len(letters)):
                        if curNode.data[i] == letters[j]:
                            fine = False

            if fine:
                newStorage.push_back(curNode.data);

            curNode = curNode.next

        return newStorage

    def copyTo(self, newDict):
        curNode = self.head

        while curNode is not None:
            newDict.push_back(curNode.data);
            curNode = curNode.next

    def getTop(self):
        return self.head.data

    def getAll(self):
        curNode = self.head
        ans = []
        while curNode:
            ans.append(str(curNode.data))
            curNode = curNode.next

        return ans

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
