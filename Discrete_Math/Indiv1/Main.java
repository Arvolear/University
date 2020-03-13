import Algorithm.*;

public class Main
{
	public static void main(String args[])
	{
		Solver solver = new Solver();

		solver.load("Input/4.txt");

		solver.solve();

		solver.displaySolution();
	}
}
