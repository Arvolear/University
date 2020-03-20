public class Rectangle extends Figure
{
	private double width;
	private double height;

	public Rectangle(double width, double height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public double area()
	{
		return width * height;
	}

	@Override
	public double perimeter()
	{
		return (width + height) * 2;
	}

	public double diagonal()
	{
		return Math.sqrt(width * width + height * height);
	}
}
