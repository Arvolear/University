printer(Element) :-
	(Element mod 2 =:= 0
	-> write(0), write(' ')
	; write(Element), write(' ')).

parseArray([]) :-
	writeln("").

parseArray([Head | Tail]) :-
	printer(Head),
	parseArray(Tail).

printArray([]) :-
	writeln("").

printArray([Head | Tail]) :-
	write(Head), write(' '),
	printArray(Tail).

main :-
	Array = [1, 2, 23, 5, 6, 7, 10, 11, 12, 0],
	write("Input array: "),
	printArray(Array),
	write("Output array: "),
	parseArray(Array).
