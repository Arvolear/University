from hangman import *

def main():
    print("\n#########################################################\n")
    print("################ Welcome to the Hangman! ################\n")
    print("#########################################################\n")
    print("Please enter desired amount of letters in the word [3-13]")

    length = int(input())
    if length < 3 or length > 13:
        print("Wrong word length!\nPlease try one more time.")
        length = int(input())
        if length < 3 or length > 13:
            print("Wrong word length!\nQuitting...")
            return

    hangMan = HangMan(length)

    print("\n####### Without further ado, let the game Begin! ########\n")
    hangMan.play()

if __name__== "__main__":
    main()
