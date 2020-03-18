# This class represents an Eilenberg machine
# its data structure is the following:
# 
# state0 --> dict0
# state1 --> dict1
# ...
# staten --> dictn
# 
# Each dict consists of {'keyWord' -> stateIndex} and each state can be marked as Terminal
# 
# We will loop through the inputStr, starting in the state0,
# and look in the data structure for the conditional jump from the currentState
# to the nextState with the given character as inputStr[i].
# 
# If inputStr end is reached, the answer will be isTerminal[currentState]
#
class Machine:
	# accepts initialState = dict
	def __init__(self, initialState):
		self.states = [initialState] # set initialState
		self.terminal = [False] # initialState is not terminal
		self.currentState = 0
	
	# accepts new state to be added
	def addState(self, newState):
		self.states.append(newState) # append new state
		self.terminal.append(False) # new state in not terminal

	# add conditional jump to an existing state
	def addCondition(self, stateIndex, wordFrom, stateTo):
		self.states[stateIndex][wordFrom] = stateTo

	# set the given state as terminal
	def setTerminal(self, stateIndex):
		self.terminal[stateIndex] = True;

	# this function performs conditional jumps
	def nextState(self, symbolFrom):
		newState = self.states[self.currentState].get(symbolFrom) # get newState (may be None)
		if newState is not None:
			self.currentState = newState # set current state as new state
			if self.currentState >= len(self.states) or self.currentState < 0: # if the state leads to nonexisting state
				return False
			return True
		return False

	# funciton that destinguishes the given word
	# return True or False
	def accept(self, word):
		for ch in word: # loop
			if not self.nextState(ch): # if current state is invalid
				return False
		return self.terminal[self.currentState] # end of string is reached, check terminal

# function that configures the machine
# machine will accept:
# ((a|c).b)*
def configureMachine():
	newMachine = Machine({'a' : 1}) # set initialState (letter 'a' leads to state 1)
	newMachine.addCondition(0, 'c', 1) # add conditional jump (letter 'c' leads to state 1)
	newMachine.addState({'b' : 0}) # add new state (letter 'b' leads to state 0)
	
	newMachine.setTerminal(0) # initial state is terminal
	
	return newMachine
	
print("Please input a string to be matched :")
inputStr = input()

newMachine = configureMachine()
print(newMachine.accept(inputStr))
