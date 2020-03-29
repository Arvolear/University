# turns out in the previous task I have implemented deterministic finate state machine, 
# hence the class structure has changed only a little

# class that represents a deterministic finate state machine.
# Machine is built on top of list of dicts, where
# each dict features a state with its elements
# discribing conditional jumps between the states 
# { "Letter" : "New state index" }
#
# machine has single input state but multiple output states
# 
# each state can be terminal
#
class Machine:
	def __init__(self, alphabet, wordFrom, stateTo):
		self.alphabet = alphabet # our alphabet
		self.states = [{wordFrom : stateTo}] # state [0] - input state
		self.terminal = [False] # input state is not terminal
		self.currentState = 0 # = input state
	
	# adds new state with defined terminality
	def addState(self, newState, term):
		self.states.append(newState) # add state
		self.terminal.append(term) # add terminality

	# adds conditional jump to an existing state
	def addCondition(self, stateIndex, wordFrom, stateTo):
		self.states[stateIndex][wordFrom] = stateTo

	# sets state terminality
	def setTerminal(self, stateIndex, term):
		self.terminal[stateIndex] = term;
	
	# function that modifies currentState
	# basically function processes conditional jumps and
	# defines their availbility
	def nextState(self, symbolFrom):
		newState = self.currentState # copy currentState

		newState = self.states[newState].get(symbolFrom) # perform actual jump, from the intermediate step
		if newState is None:
			return False
		
		self.currentState = newState # assign currentState
		return True

	# function that eliminates unreachable states
	def eliminateUnreachable(self):
		used = [False for i in range(0, len(self.terminal))] # "used" list for BFS, all False

		q = [0] # BFS queue with the 0 state
		used[0] = True # 0 state is visited

		# BFS
		while q:
			state = q.pop(0) # pop the head

			for key in self.states[state].keys(): # loop through all the head transitions
				val = self.states[state].get(key)
				if val is not None and used[val] is False: # if new state is not visited
					used[val] = True # visit it
					q.append(val)

		prefixSum = [0] # helper data structure to relink states after deleting unreachable once
						# relink is needed because after states removal indices will become obsolete

		for i in range(1, len(used)): # count prefix indices offset
			prefixSum.append(prefixSum[i - 1])
			if used[i] is False:
				prefixSum[i] += 1

		i = 0
		while i < len(self.states): # loop through all the states
			if used[i] is False: # if unreachable
				self.states.pop(i) # pop it 
				continue

			for key in self.states[i].keys(): # if reachable, loop through all keys and shift them by prefix value
				self.states[i][key] -= prefixSum[self.states[i][key]]
			
			i += 1 # increment only if didn't delete anything

	# function that reduces a deterministic finite state machine
	def optimize(self):
		self.eliminateUnreachable() # delete unreachable
		
		# construct a table of all pairs of states
		table = [[0 for i in range(0, len(self.states))] for j in range(0, len(self.states))]

		# fill the table with 1, where i or j state is terminal
		# fill only left corner
		for i in range(0, len(self.states)):
			for j in range(0, i):
				if self.terminal[i] or self.terminal[j]:
					table[i][j] = 1

		# while the values into the table are added
		added = True
		while (added):
			added = False
			for i in range(0, len(self.states)): # loop through the table
				for j in range(0, i): # loop only through the left corner
					if table[i][j] == 0: # if state is not "marked"
						for c in self.alphabet: # loop through the alphabet
							newState1 = self.states[i].get(c) # get conditional jump state from i
							newState2 = self.states[j].get(c) # get conditional jump state from j

							if newState1 is not None and newState2 is not None: # if the states are fine
								if table[newState1][newState2] == 1: # if that cell is marked - mark current cell
									table[i][j] = 1 # mark
									added = True # new value is added - continue
									break
		
		# loop that merges equivalent states
		# they are unmarked in the table
		for i in range(len(table) - 1, -1, -1): # smaller state consumes bigger state
			for j in range(0, i): 
				if table[i][j] == 0: 
					for state in self.states: # loop though all the states
						for key in state.keys(): # loop through all the keys
							if state[key] == i: # relink the state that points to i with j
								state[key] = j; # relink to point to j
		
		# delete unreachable states
		self.eliminateUnreachable()	

	# function that accepts input strings
	def accept(self, word):
		for ch in word: # loop through the string
			if not self.nextState(ch): # is next state is unreachable -> bad string
				return False
		
		return self.terminal[self.currentState] # check state's terminality

	# returns number of states
	def size(self):
		return len(self.states)

	# simple output function
	# prints this machine to the console
	def output(self):
		for i in range(0, len(self.states)):
			print("state", i)

			for key in self.states[i].keys():
				print("key:", key, ", val:", self.states[i][key], end=' ')

			if i == 0:
				print("input state", end='')
			elif self.terminal[i]:
				print("terminal state", end='')

			print()

# lets configure out machine
# illustration attached
def configureAutomaton(alphabet):

	machine = Machine(alphabet, 'a', 2) # zero state
	machine.addCondition(0, 'b', 3)

	machine.addState({'a' : 3}, False) # first state
	machine.addCondition(1, 'b', 2)

	machine.addState({'a' : 4}, True) # second state
	machine.addCondition(2, 'b', 1)
	
	machine.addState({'a' : 1}, False) # third state
	machine.addCondition(3, 'b', 4)
	
	machine.addState({'a' : 2}, False) # fourth state
	machine.addCondition(4, 'b', 3)

	machine.addState({'Z' : 6}, True) # fifth state !UNREACHABLE!
	machine.addState({'Y' : 2}, True) # sixth state !UNREACHABLE!

	return machine

alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

newMachine = configureAutomaton(alphabet) # construct the machine
print("\n\tInput machine:")
newMachine.output()

print("\nOptimizing...")
newMachine.optimize()

print("\n\tOutput machine:")
newMachine.output()
print()
