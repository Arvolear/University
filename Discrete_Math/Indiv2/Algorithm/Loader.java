package Algorithm;

import java.util.ArrayList;
import java.io.File; 
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

class Loader
{
	private ArrayList < ArrayList < Double > > adjMatrix;

	private File file;

	Loader(String path)
	{
		file = new File(path);
	}

	private void resizeMatrix(int newSize)
	{
		for (int i = 0; i < adjMatrix.size(); i++)
		{
			while (adjMatrix.get(i).size() < newSize)
			{
				adjMatrix.get(i).add(0.0);
			}
		}

		while (adjMatrix.size() < newSize)
		{
			ArrayList < Double > tmp = new ArrayList<>();

			for (int j = 0; j < newSize; j++)
			{
				tmp.add(0.0);
			}

			adjMatrix.add(tmp);
		}
	}

	void load() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);
		adjMatrix = new ArrayList<>();

		int currentVertex = 0;
				
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();

			Pattern pattern = Pattern.compile("\\s+");

			double array[] = pattern.splitAsStream(line.trim()).mapToDouble(Double::parseDouble).toArray();

			for (int i = 0; i < array.length; i += 2)
			{
				int vertex = (int)array[i];

				if (adjMatrix.size() <= vertex)
				{
					resizeMatrix(vertex + 1);
				}

				adjMatrix.get(currentVertex).set(vertex, array[i + 1]);
			}

			currentVertex++;
		}
	}

	ArrayList < ArrayList < Double > > getAdjMatrix()
	{
		return adjMatrix;
	}
}
