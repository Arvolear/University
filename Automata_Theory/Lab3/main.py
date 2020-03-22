# class that represents an Eilenberg machine.
# Machine is built on top of list of dicts, where
# each dict features a state with its elements
# discribing conditional jumps between the states 
# { "Letter" : "New state index" }
#
# machine has single input state but multiple output states
# minimum states number is 3: input, intermediate, output
# 
# each state can be terminal
#
class Machine:
	def __init__(self, wordFrom, stateTo):
		self.states = [{wordFrom : 1}] # input state[0] links to state [1]
		self.states.append({wordFrom : stateTo}) # state [1] - intermediate
		self.terminal = [None] # input state terminality is undefined 
		self.terminal.append(False) # intermediate state is not terminal
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

		while self.terminal[newState] is None: # if we are in input state -> skip it
			newState = self.states[newState].get(symbolFrom)
			if newState is None: # check if next state is unreachable
				return False

		newState = self.states[newState].get(symbolFrom) # perform actual jump, from the intermediate step
		if newState is None:
			return False
		
		while self.terminal[newState] is None: # we are in the input state once again, skip it + perform actual jump
			newState = self.states[newState].get(symbolFrom) # first skip
			if newState is None:
				return False
			newState = self.states[newState].get(symbolFrom) # second skip
			if newState is None:
				return False

		self.currentState = newState # assign currentState
		return True

	# function that accepts input strings
	def accept(self, word):
		for ch in word: # loop through the string
			if not self.nextState(ch): # is next state is unreachable -> bad string
				return False
		
		while self.terminal[self.currentState] is None: # if we are in the input state -> skip to the next intermediate one
			self.currentState += 1

		return self.terminal[self.currentState] # check state's terminality

	# function that loops current machine, it is called when '*' operation is being processed
	def cycle(self):
		for i in range(0, len(self.states)): # loop through all self.states
			if self.terminal[i] is not None and self.terminal[i]: # state is terminal
				for key in self.states[0]: # loop through all links from the input state
					self.addCondition(i, key, 0); # add those links to the aforementioned terminal states
		
		for i in range(0, len(self.states)): # loop through all self.states
			if self.terminal[i] is None and \
			self.terminal[i + 1] is not None and self.terminal[i + 1]: # set all states that are adjacent to input state as terminal
				self.setTerminal(i + 1, True);

	# function that appends one machine to another, it is called when '.' operation is being processed
	def append(self, other):
		for i in range(0, len(other.states)): # loop through all other.states
			for key in other.states[i].keys(): # loop through all keys and add to values self.size() -> new indices
				other.states[i][key] = other.states[i][key] + self.size()

		for i in range(0, len(self.states)): # loop through all self.states
			if (self.terminal[i] is None and (self.terminal[i + 1] is not None and self.terminal[i + 1])) or \
			(self.terminal is not None and self.terminal[i]): # if state is an input state with next terminal or a terminal one
				for key in other.states[0]: # loop through all other input state links
					self.addCondition(i, key, self.size()); # add links to other.inputState
			
			if self.terminal[i] is not None and self.terminal[i]: # set new terminality to other.input + 1 state terminality
				self.setTerminal(i, other.terminal[1])

		# append all other states
		for i in range(0, len(other.states)):
			self.addState(other.states[i], other.terminal[i])

	# function is called when '|' operation is being processed
	def merge(self, other):	
		for i in range(0, len(other.states)): # loop through all other states
			for key in other.states[i].keys(): # loop through all keys
				other.states[i][key] = other.states[i][key] + self.size() # set new indices
		
		for i in range(0, len(self.states)): # loop through all self.states
			if self.terminal[i] is not None and self.terminal[i]: # if it is terminal
				for key in self.states[i]: # loop through all links
					if self.terminal[self.states[i][key]] is None: # if link is to input state
						self.addCondition(i, key, self.states[i][key] + 1) # set it to input state + 1 (it will be an intermediate one)
				
		for key in other.states[0]: # add new conditions to self input state 
			self.addCondition(0, key, self.size());

		# append all other states
		for i in range(0, len(other.states)):
			self.addState(other.states[i], other.terminal[i])

	# returns number of states
	def size(self):
		return len(self.states)

# class that parses and constructs an Eilenberg machine
class Parser:
	def __init__(self, expr):
		self.expr = expr

	# simple priority function
	def priority(self, operation):
		if operation == "*":
			return 3
		
		if operation == ".":
			return 2
		
		if operation == "|":
			return 1

		return 0

	# calls corresponding cycle, append and merge functions
	def calculate(self, left, right, operation):
		if operation == "*":
			left.cycle()

		if operation == ".":
			left.append(right)
		
		if operation == "|":
			left.merge(right)

		return left

	# function that processes current operation
	def process(self, machines, operations):
		operation = operations.pop()

		# left assosiative - only one operand
		if (operation == "*"):
			right = machines.pop()
			machines.append(self.calculate(right, right, operation))
		# two operands			
		else:
			right = machines.pop();
			left = machines.pop();
			machines.append(self.calculate(left, right, operation))

	# postfix - infix parser
	def parse(self):
		machines = [] # intermediate machines
		operations = [] # intermediate operations

		for symb in self.expr: # loop through the input string 
			if symb == "(":
				operations.append("(")

			elif symb.isalpha(): # ONLY SINGLE CHARACTERS SUPPORTED
				machine = Machine(symb, 2) # construct machine
				machine.addState({}, True)
				machines.append(machine)

			elif symb == ")": 
				while operations and operations[-1] != "(": # process until '(' is found
					self.process(machines, operations)
					
				if operations:
					operations.pop();
			
			else:	
				while operations and self.priority(operations[-1]) >= self.priority(symb): # process all higher priority operators
					self.process(machines, operations)

				operations.append(symb)
				
		while operations: # forward processing
			self.process(machines, operations)

		return machines[-1] # answer

print("Please input a regular expression to construct an automaton from :")
expr = input()

print("Please input a string to be matched :")
inputStr = input()

parser = Parser(expr) # constuct parser
newMachine = parser.parse() # construct the machine

print(newMachine.accept(inputStr))
