seq(N, A, B, Res) :-
	(N == 0 
	-> Res = 0
	; (N > 0,
		N1 is N - 1,
		A1 is A + 1,
		B1 is B + 2,
		seq(N1, A1, B1, Res1),
		Res = Res1 + 1 / (A1 * B1)
	)).

seq(N, String, Res) :-
	seq(N, 0, 1, String),
	Res is String.

main :-
	writeln("Please input N"),
	read(N),
	seq(N, String, Res),
	writeln(String = Res).
