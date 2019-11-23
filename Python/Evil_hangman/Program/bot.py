from botstorage import *

class Bot:
    def __init__(self):
        self.storage = BotStorage()
        self.lastWord = '.'
        self.lastLetter = '.'
        self.curLetter = '.'
        self.curInd = -1;

        self.stupidLetters = ['e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'p', 'y', 'v', 'g', 'b', 'q', 'w', 'f', 'j', 'k', 'z', 'x'];

    def setWord(self, word):
        self.lastWord = str(word);

        f = open("../BotDictionary/length" + str(len(word)) + ".txt", 'r')
        buff = f.read().splitlines()
        for i in buff:
            self.storage.push_back(i)

    def getChar(self, word):
        if self.storage.getSize() == 1:
            answer = self.storage.getTop()
            for i in range(0, len(answer)):
                if word[i] == '_':
                    return answer[i]

        ### stupidLetters ###

        while self.curInd < 25:
            self.curInd += 1
            self.curLetter = self.stupidLetters[self.curInd]
            fine = False

            #self.storage.show();

            if str(self.lastWord) == str(word) and self.curInd > 0:
                self.storage.removeWords(self.lastLetter);
            else:
                newStorage = self.storage.extractSet(word);
                if newStorage:
                    self.storage = newStorage

            possible = self.storage.getAll()
            for i in range(0, len(possible)):
                #print(possible[i])
                for j in range(0, len(possible[i])):
                    if word[j] == '_' and possible[i][j] == self.curLetter:
                        fine = True
                        break

            if fine:
                self.lastWord = str(word);
                self.lastLetter = self.curLetter

                #print(self.curLetter)
                return self.curLetter

        #self.storage.show();
        #print()

        ### stupidLetters ###

        return ''


    def getWord(self):
        return self.lastWord
