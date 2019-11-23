from bot import *

bot = Bot();

def move(word):
    if bot.getWord() == '.':
        bot.setWord(word)

    return bot.getChar(word)
