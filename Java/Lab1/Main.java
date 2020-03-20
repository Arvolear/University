import java.util.Scanner;

class Main
{
	public static void main(String args[])
	{
		int n = 0;

		System.out.println("Enter number of points");
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		Point points[] = new Point[n];

		System.out.println("Enter n points");

		for (int i = 0; i < n; i++)
		{
			double x, y;
			x = sc.nextDouble();
			y = sc.nextDouble();

			points[i] = new Point(x, y);
		}

		System.out.println("Enter center of the circle");
		double x = sc.nextDouble();
		double y = sc.nextDouble();

		Point center = new Point(x, y);
		
		System.out.println("Enter radius of the circle");
		double radius = sc.nextDouble();

		for (int i = 0; i < n; i++)
		{
			System.out.println(points[i].isInCircle(center, radius));
		}
	}
}
