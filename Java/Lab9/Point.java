class Point implements Comparable < Point >
{
	private double x;
	private double y;

	Point()
	{
		this.x = 0.0;
		this.y = 0.0;
	}

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

	@Override
	public String toString()
	{
		return "x = " + x + "; " + "y = " + y;
	}

	@Override
	public int compareTo(Point other)
	{
		double eps = 0.0000001;
			
		double valX = x - other.x;

		if (Math.abs(valX) < eps)
		{
			double valY = y - other.y;

			if (Math.abs(valY) < eps)
			{
				return 0;
			}

			return (int)valY;
		}

		return (int)valX;
	}

	@Override
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

	@Override
	public int hashCode()
	{
		return (int)(x * y);
	}
}
