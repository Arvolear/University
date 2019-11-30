.org 0

arr: .dw 1, 2, 2, 2, 3, 4, 5

search: .dw 3

left: .dw 0
right: .dw 0
mid: .dw 0

begin:
	LDA #arr
	STA left

	LDA #search
	SUB #2
	STA right

	loop:
		LDA left
		ADD right
		DIV #2
		STA mid

		AND #1

		CMP #1
		JE odd
		JNE ok

		odd:
			LDA mid
			SUB #1
			STA mid

		ok:
		
		LDA @mid
		
		CMP search
		JE moveRight
		JG moveLeft
		JL moveRight

		moveLeft:
			LDA mid
			STA left
			JMP skip

		moveRight:
			LDA mid
			STA right

		skip:

		LDA right
		SUB #2
	CMP left
	JNE loop
	
	LDA @right

	CMP search
	JG notFound

	LDA @left

	CMP search
	JG outRight
	
	outLeft:
		LDA left
		OUT #1
		JMP bye

	outRight:
		LDA right
		OUT #1
		JMP bye

	notFound:
		LDA #-1
		OUT #1

	bye:

HALT
.END begin
