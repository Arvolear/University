makeList(List, N) :-
  	length(List, N),
	mapList(=(0), List).

mapList(_, []).

mapList(ToCall, [Head | Tail]) :-
	call(ToCall, Head),
	mapList(ToCall, Tail).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

printMatrix(_, _, []) :-
	writeln("").

printMatrix(N, Cur, [Head | Tail]) :-
	Cur1 is Cur + 1,
	
	(Cur1 mod N =:= 0
	-> nl
	; write("")),

	write(Head),
	printMatrix(N, Cur1, Tail).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

replace(Index, List, Element, Result) :-
  nth0(Index, List, _, Tmp),
  nth0(Index, Result, Element, Tmp).

fillCol(Matrix, First, [Second | _], Result) :-
	Offset is First + Second,
	replace(Offset, Matrix, 1, Result).

fillRow(Matrix, N, [First | Tail], Result) :-
	Offset is First * N,
	fillCol(Matrix, Offset, Tail, Result).

fillMatrix(_, _, []).

fillMatrix(Matrix, Index, Cur, N, [Head | Tail], Result) :-
	(Cur == Index
	-> fillRow(Matrix, N, Head, Result)
	; Cur1 is Cur + 1,
	  fillMatrix(Matrix, Index, Cur1, N, Tail, Result)).


constructMatrix(Matrix, N, Graph, Output) :-
	fillMatrix(Matrix, 0, 0, N, Graph, Result),
	fillMatrix(Result, 1, 0, N, Graph, Result1),
	fillMatrix(Result1, 2, 0, N, Graph, Result2),
	fillMatrix(Result2, 3, 0, N, Graph, Result3),
	fillMatrix(Result3, 4, 0, N, Graph, Output).

main :-
	N = 10,
	Graph = [[0, 2], 
			 [0, 3],
			 [1, 9],
			 [8, 8],
			 [7, 6]],
	
	writeln(N),
	printMatrix(1, 0, Graph),
	
	Size is N * N,
	makeList(Matrix, Size),

	constructMatrix(Matrix, N, Graph, Output),
	printMatrix(N, -1, Output).
