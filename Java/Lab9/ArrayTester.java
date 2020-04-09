import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class ArrayTester
{
	ArrayList < Point > points;
	Random generator;

	public ArrayTester()
	{
		points = new ArrayList<>();
		generator = new Random();
	}

	private void printPoints()
	{
		int i = 0;

		System.out.println();

		for (Point p : points)
		{
			System.out.println("Point " + i + ":");
			System.out.println(p);
			System.out.println();

			i++;
		}
	}

	private void deletePoints(List < String > indices)
	{
		/* second argument = comparator */
		Collections.sort(indices);
		
		Iterator < Point > iter = points.iterator();
		
		int i = 0;

		while (iter.hasNext())
		{
			Point p = iter.next();
			int pos = Collections.binarySearch(indices, String.valueOf(i));
			
			if (pos >= 0)
			{
				iter.remove();			
			}
				
			i++;
		}
	}

	public void test()
	{
		int numberOfElems = generator.nextInt(5) + 5;
		int maxContained = (int)Math.round(Math.sqrt(numberOfElems));

		for (int i = 0; i < numberOfElems; i++)
		{
			Point point = new Point();

			point.setX(generator.nextInt(maxContained));
			point.setY(generator.nextInt(maxContained));

			points.add(point);
		}

		printPoints();

		List < String > toDelete = new ArrayList<>();

		System.out.println("\nPlease input elements indices you wish to delete:");
		
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		String line = sc.next();
		toDelete = Arrays.asList(line.split(" "));

		deletePoints(toDelete);
		printPoints();
	}
}
