# importing json parser library on order to
# parse the file into dictionary
import json

# Tiny-little binary tree class that will represent json tree. 
# Self.value holds either key: "*", "." , "|" or <val> if key is "atm"
class Node:	
	def __init__(self, value):
		self.value = value;

		self.left = None;
		self.right = None;

# called with json dictionary to construct
# a binary tree with the aforementioned structure
# returns root of the tree
def createTree(jDict):
	key = jDict["key"] # get local key
	val = jDict["val"] # get local val

	node = None

	# binary operator is faced =>
	# constuct left and right nodes with 
	# previously extracted value
	if key == "|" or key == ".":
		node = Node(key)
		node.left = createTree(val["fst"]) # call with "fst" expr
		node.right = createTree(val["snd"]) # call with "scd" expr

	# unary operator =>
	# construct only left node with
	# previously extracted value
	if key == "*":
		node = Node(key)
		node.left = createTree(val) # call with "val"

	# word or letter
	# contruct the node on spot
	if key == "atm":
		node = Node(val) # construct the node with the word

	return node

# returns key priority
def priority(key):
	if key == "*":
		return 3
	if key == ".":
		return 2
	if key == "|":
		return 1
	
	# word or letter
	return 4

# inorder traversal of the constructed tree
# returns final string
def inorder(root, parent):
	s = "" # here will save the result

	if root is not None:
		s = inorder(root.left, root) # left child call
		
		addBefore = ""
		addAfter = ""

		# braces handler
		# add braces if a child key priority is less
		# that a parent key priority
		if parent is not None:
			if priority(root.value) < priority(parent.value):
				addBefore = "("
				addAfter = ")"

			s = addBefore + s + root.value;
		else:
			s += root.value # no braces needed for the root node

		s += inorder(root.right, root) + addAfter # right child call

	return s

print("Please enter the file to be parsed:")

path = input();

with open(path) as inFile:
	jDict = json.load(inFile)

root = createTree(jDict)
answer = inorder(root, None)

print(answer)
