class Point
{
	private double x;
	private double y;

	Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	double getX()
	{
		return x;
	}

	double getY()
	{
		return y;
	}

	void setX(double x)
	{
		this.x = x;
	}

	void setY(double y)
	{
		this.y = y;
	}

	boolean isInCircle(Point center, double radius)
	{
		double d = Math.sqrt((x - center.x) * (x - center.x) + (y - center.y) * (y - center.y));

		return d <= radius;
	}

	public String toString()
	{
		return x + " " + y;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null)
		{
			return false;
		}

		if (obj instanceof Point)
		{	
			Point other = (Point)(obj);

			return x == other.x && y == other.y;
		}
	
		return false;
	}

	public int hashCode()
	{
		return (int)(x * y);
	}
}
