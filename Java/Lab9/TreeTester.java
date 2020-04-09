import java.util.TreeSet;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class TreeTester
{
	TreeSet < Point > points;
	Random generator;

	public TreeTester()
	{
		points = new TreeSet<>();
		generator = new Random();
	}

	private void print(Set < Point > set)
	{
		int i = 0;

		System.out.println();

		for (Point p : set)
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

	private void specificFunctions()
	{
		SortedSet < Point > cutSet = points.subSet(new Point(2, 1), new Point(4, 4));
		NavigableSet < Point > tailSet = points.tailSet(new Point(2, 2), true);
		NavigableSet < Point > descSet = points.descendingSet();

		System.out.println("\nSet from [2;1] to (4,4)");
		print(cutSet);
		
		System.out.println("\nSet from [2;2]");
		print(tailSet);
		
		System.out.println("\nDescending set");
		print(descSet);

		if (points.contains(new Point(3, 3)))
		{
			System.out.println("Set has point [3,3], removing\n");
			points.remove(new Point(3, 3));
		}
		else
		{
			System.out.println("\nSet does not have point [3,3]\n");
		}

		System.out.println("\nSet smallest element:\n");
		System.out.println(points.first());
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

		print(points);

		List < String > toDelete = new ArrayList<>();

		System.out.println("\nPlease input elements indices you wish to delete:");
		
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		String line = sc.next();
		toDelete = Arrays.asList(line.split(" "));

		deletePoints(toDelete);
		print(points);

		specificFunctions();
	}
}
