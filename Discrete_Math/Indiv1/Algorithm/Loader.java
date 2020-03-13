package Algorithm;

import java.util.ArrayList;
import java.io.File; 
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

class Loader
{
	private ArrayList < ArrayList < Double > > weights;
	private ArrayList < Double > producers;
	private ArrayList < Double > consumers;

	private File file;

	Loader(String path)
	{
		file = new File(path);
	}

	void load() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);

		weights = new ArrayList<>();
		producers = new ArrayList<>();
		consumers = new ArrayList<>();
				
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();

			Pattern pattern = Pattern.compile("\\s+");

			double array[] = pattern.splitAsStream(line.trim()).mapToDouble(Double::parseDouble).toArray();

			if (!scanner.hasNextLine())
			{
				for (int i = 0; i < array.length; i++)
				{
					consumers.add(array[i]);	
				}
			}
			else
			{
				ArrayList < Double > tmp = new ArrayList<>();

				for (int i = 0; i < array.length - 1; i++)
				{
					tmp.add(array[i]);	
				}

				weights.add(tmp);
				producers.add(array[array.length - 1]);
			}
		}
	}

	ArrayList < ArrayList < Double > > getWeights()
	{
		return weights;
	}

	ArrayList < Double > getProducers()
	{
		return producers;
	}

	ArrayList < Double > getConsumers()
	{
		return consumers;
	}
}
