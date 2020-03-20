public class Triangle extends Figure
{
	private double first;
	private double second;
	private double third;

	public Triangle(double first, double second, double third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public double area()
	{
		double halfP = perimeter() / 2.0;
		return Math.sqrt(halfP * (halfP - first) * (halfP - second) * (halfP - third));
	}

	@Override
	public double perimeter()
	{
		return first + second + third;
	}
}
