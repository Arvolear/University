public class Rectangle
{
	private double x;
	private double y;
	private double width;
	private double height;

	public Rectangle()
	{
		this.x = 0;
		this.y = 0;
		this.width = 1;
		this.height = 1;
	}

	public Rectangle(double x, double y, double width, double height) throws Exception
	{
		this.x = x;
		this.y = y;

		if (width < 0 || height < 0)
		{
			throw new Exception("Width or Height is negative");
		}

		this.width = width;
		this.height = height;
	}

	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setWidth(double width) throws Exception 
	{
		if (width < 0)
		{
			throw new Exception("Width is negative");
		}
		
		this.width = width;
	}

	public void setHeight(double height) throws Exception
	{
		if (height < 0)
		{
			throw new Exception("Height is negative");
		}

		this.height = height;
	}

	public void move(double toX, double toY)
	{
		this.x = toX;
		this.y = toY;
	}

	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}

	public double area()
	{
		return width * height;
	}

	public double perimeter()
	{
		return width + height;
	}

	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}

	public boolean isInRectangle(double x, double y)
	{
		if (this.x <= x && this.y <= y)
		{
			if (this.x + this.width >= x && this.y + this.height >= y)
			{
				return true;
			}
		}

		return false;
	}
	
	public double getDiagonal()
	{
		return Math.sqrt(width * width + height * height);
	}

	public String toString()
	{
		String s = "";
		String beg = ""; 

		for (int i = 0; i < y; i++)
		{
			s += "\n";
		}

		for (int i = 0; i < x; i++)
		{
			beg += " ";
		}

		s += beg;

		if (width > 0 || height > 0)
		{
			s += "+";
		}
		
		for (int i = 0; i < width - 2; i++)
		{
			s += "-";
		}

		if (width > 1)
		{
			s += "+";
		}

		s += "\n";

		String tmp = "";
			
		for (int j = 0; j < width - 2; j++)
		{
			tmp += " ";
		}
		
		for (int i = 0; i < height - 2; i++)
		{
			s += beg;

			if (height > 1)
			{
				s += "|";
			}

			s += tmp;

			if (width > 1)
			{
				s += "|";
			}

			s += "\n";
		}
		
		s += beg;

		if (height > 1)
		{	
			s += "+";
		}
	
		if (height > 1)
		{
			for (int i = 0; i < width - 2; i++)
			{
				s += "-";
			}

			if (width > 1)
			{
				s += "+";
			}
		}

		s += "\n";

		return s;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof Rectangle)
		{
			Rectangle other = (Rectangle)(obj);

			return x == other.x && y == other.y && width == other.width && height == other.height;
		}

		return false;
	}

	public int hashCode()
	{
		int hash = 1;

		hash = hash * 31 + (int)x;
		hash = hash * 31 + (int)y;

		hash = hash * 31 + (int)width;
		hash = hash * 31 + (int)height;

		return hash;
	}
}
