package Algorithm;

import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Solver
{
	MatrixHelper matrixHelper;
	GraphHelper graphHelper;

	ArrayList < ArrayList < Double > > adjMatrix;

	public Solver() 
	{
		matrixHelper = new MatrixHelper();
		graphHelper = new GraphHelper();
	}

	public void load(String path) throws Exception
	{
		Loader loader = new Loader(path);

		loader.load();

		adjMatrix = loader.getAdjMatrix();
	}

	public String a1Probability(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		int array[] = pattern.splitAsStream(input.trim()).mapToInt(Integer::parseInt).toArray();

		if (array.length != 3)
		{
			throw new Exception();
		}

		int fromState = array[0];
		int toState = array[1];
		int steps = array[2];

		ArrayList < ArrayList < Double > > ans = matrixHelper.binPowMatrix(adjMatrix, steps);

		if (ans == null)
		{
			throw new Exception();
		}
			
		double cut = (int)(ans.get(fromState).get(toState) * 10000) / 10000.0;

		return String.valueOf(cut);
	}

	public String a2Probability(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		double array[] = pattern.splitAsStream(input.trim()).mapToDouble(Double::parseDouble).toArray();

		int steps = (int)array[array.length - 1];

		ArrayList < ArrayList < Double > > ans = new ArrayList<>();
		ArrayList < Double > tmp = new ArrayList<>();

		for (int i = 0; i < array.length - 1; i++)
		{
			tmp.add(array[i]);
		}

		if (tmp.size() != adjMatrix.size())
		{
			throw new Exception();
		}

		ans.add(tmp);

		for (int i = 0; i < steps; i++)
		{
			ans = matrixHelper.mulMatrix(ans, adjMatrix);
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ans.get(0).size(); i++)
		{
			double cut = (int)(ans.get(0).get(i) * 10000) / 10000.0;

			builder.append(cut);
			builder.append(" ");
		}

		return builder.toString();
	}

	public String a3(String input) throws Exception
	{
		// TODO

		return "";
	}

	public String b1Accesible(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		int array[] = pattern.splitAsStream(input.trim()).mapToInt(Integer::parseInt).toArray();
		
		if (array.length != 2)
		{
			throw new Exception();
		}
		
		int fromState = array[0];
		int toState = array[1];

		ArrayList < Integer > bfs = graphHelper.bfs(adjMatrix, fromState);

		if (bfs.get(toState) == -1)
		{
			return "Unaccesible";
		}

		return "Accesible";
	}
	
	public String b2Accesible(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		int array[] = pattern.splitAsStream(input.trim()).mapToInt(Integer::parseInt).toArray();
		
		if (array.length != 1)
		{
			throw new Exception();
		}
		
		int fromState = array[0];

		ArrayList < Integer > bfs = graphHelper.bfs(adjMatrix, fromState);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < bfs.size(); i++)
		{
			if (bfs.get(i) >= 0)
			{
				builder.append(i);
				builder.append(" ");
			}
		}

		return builder.toString();
	}
	
	public String b3Meaningful(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		int array[] = pattern.splitAsStream(input.trim()).mapToInt(Integer::parseInt).toArray();
		
		if (array.length != 1)
		{
			throw new Exception();
		}
		
		int fromState = array[0];

		boolean means = graphHelper.dfs(adjMatrix, fromState, fromState);

		if (means)
		{
			return "Meaningful";
		}

		return "Not meaningful";
	}
	
	public String b4Communicate(String input) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s+");

		int array[] = pattern.splitAsStream(input.trim()).mapToInt(Integer::parseInt).toArray();
		
		if (array.length != 2)
		{
			throw new Exception();
		}
		
		int firstState = array[0];
		int secondState = array[1];

		boolean firstToSecond = graphHelper.dfs(adjMatrix, firstState, secondState);
		boolean secondToFirst = graphHelper.dfs(adjMatrix, secondState, firstState);

		if (firstToSecond && secondToFirst)
		{
			return "Communicative";
		}

		return "Not communicative";
	}

	public String b5EqualityClasses(String input) throws Exception
	{
		if (input.length() != 0)
		{
			throw new Exception();
		}
	
		ArrayList < ArrayList < Integer > > components = graphHelper.strongComponents(adjMatrix);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < components.size(); i++)
		{
			for (int j = 0; j < components.get(i).size(); j++)
			{
				builder.append(components.get(i).get(j));

				if (j < components.get(i).size() - 1)
				{
					builder.append(" ");
				}
			}

			builder.append("; ");
		}

		return builder.toString();
	}

	public String b6Absorbing(String input) throws Exception
	{
		if (input.length() != 0)
		{
			throw new Exception();
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < adjMatrix.size(); i++)
		{
			boolean absorbing = true;

			for (int j = 0; j < adjMatrix.get(i).size(); j++)
			{
				if (i != j && adjMatrix.get(i).get(j) > 0.0)
				{
					absorbing = false;
					break;
				}
			}

			if (absorbing)
			{
				builder.append(i);
				builder.append(" ");
			}
		}

		return builder.toString();
	}
	
	public String c1Irreducible(String input) throws Exception
	{
		if (input.length() != 0)
		{
			throw new Exception();
		}
	
		ArrayList < ArrayList < Integer > > components = graphHelper.strongComponents(adjMatrix);

		StringBuilder builder = new StringBuilder();

		if (components.size() == 1)
		{
			builder.append("Irreducible");
		}
		else
		{
			builder.append("Reducible -> ");
			builder.append(components.size());
		}

		return builder.toString();
	}
	
	public String c2AllMeaningful(String input) throws Exception
	{
		if (input.length() != 0)
		{
			throw new Exception();
		}
	
		ArrayList < ArrayList < Integer > > components = graphHelper.strongComponents(adjMatrix);

		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();

		if (components.size() == 1)
		{
			builder1.append("Meaningful: ");
			builder2.append("Not: ");

			for (int i = 0; i < adjMatrix.size(); i++)
			{
				boolean means = graphHelper.dfs(adjMatrix, i, i);

				if (means)
				{
					builder1.append(i);
					builder1.append(" ");
				}
				else
				{
					builder2.append(i);
					builder2.append(" ");
				}
			}
		}
		else
		{
			builder1.append("Reducible");
		}

		return builder1.toString() + builder2.toString();
	}

	public Object[][] getInput()
	{
		Object result[][] = new Object[adjMatrix.size() + 1][];

		Object notch[] = new Object[adjMatrix.get(0).size() + 1];
		notch[0] = "R\\C";

		for (int i = 0; i < adjMatrix.size(); i++)
		{
			notch[i + 1] = Integer.valueOf(i);
		}

		result[0] = notch;

		for (int i = 0; i < adjMatrix.size(); i++)
		{
			Object objs[] = new Object[adjMatrix.get(i).size() + 1];

			for (int j = 0; j < adjMatrix.get(i).size(); j++)
			{
				if (adjMatrix.get(i).get(j).equals(0.0))
				{
					objs[j + 1] = "-";
				}
				else
				{
					objs[j + 1] = adjMatrix.get(i).get(j);
				}
			}

			result[i + 1] = objs;
		}

		for (int i = 0; i < adjMatrix.size(); i++)
		{
			result[i + 1][0] = Integer.valueOf(i);
		}

		return result;
	}

	public ArrayList < ArrayList < Double > > getAdjMatrix()
	{
		return adjMatrix;
	}
}
