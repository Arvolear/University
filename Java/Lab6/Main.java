import java.util.ArrayList;

public class Main
{
	public static void main(String args[])
	{
		ArrayList < Figure > figures = new ArrayList<>();

		figures.add(new Circle(10));
		figures.add(new Triangle(5, 6, 7));
		figures.add(new Rectangle(10, 15));
		figures.add(new Circle(2));
		figures.add(new Rectangle(7, 8));
		figures.add(new Triangle(1, 2, 3));

		for (int i = 0; i < figures.size(); i++)
		{
			System.out.println("Figure " + i + " area = " + figures.get(i).area());
			System.out.println("Figure " + i + " perimeter = " + figures.get(i).perimeter());

			if (figures.get(i) instanceof Rectangle)
			{
				System.out.println("Figure " + i + " diagonal = " + ((Rectangle)(figures.get(i))).diagonal());
			}

			System.out.println();
		}
	}
}
