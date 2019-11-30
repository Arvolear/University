// lower_bound binary search
// outputs an address of the first element in the array that is >= than the searched element
// if there is no such element - outputs -1

arr: .dw 1, 3, 3, 3, 4, 4, 5 // initial search array

search: .dw 3 // value to search

left: .dw 0 // left pointer
right: .dw 0 // right pointer
mid: .dw 0 // middle pointer (left + right) / 2;

begin:
	MOV left, &arr // set initial left position

	SUB &search, &2 
	MOV right, %A // set initial right position

	loop:
		ADD left, right
		DIV %A, &2
		MOV mid, %A // calculate middle = (left + right) / 2;

		AND mid, &1 // if middle is odd. Ex: (0 + 6) / 2 = 3. Odd address does not exist... 

		JE %A, &1, odd // if middle is odd
		JNE %A, &1, ok // else if middle is even

		odd:
			SUB mid, &1 // subtract 1 from the middle
			MOV mid, %A

		ok:
		
		JG search, *mid, moveLeft // if searched element > middle, left = middle
		JE search, *mid, moveRight // else if searched element == middle, right = middle
		JL search, *mid, moveRight // else if searched element < middle, right = middle

		moveLeft:
			MOV left, mid // left = middle
			JMP skip // skip moveRight

		moveRight: 
			MOV right, mid // right = middle

		skip:

		SUB right, &2 // if (right - 2) == left, the element is either [left] or [right], break
	JNE left, %A, loop

	JG search, *right, notFound // if searched element > right, no element in the array to fit the lower_bound condition
	JG search, *left, outRight // else if searched element > left, output right
							  // else output left

	outLeft:
		OUT left 
		JMP bye // return

	outRight:
		OUT right
		JMP bye // return

	notFound:
		OUT &-1
	
	bye:

STOP
.END begin
