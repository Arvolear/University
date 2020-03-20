import java.util.Arrays;

public class NVector
{
	private double vec[];

	public NVector()
	{
		vec = new double[2];
	}

	public NVector(int dim)
	{
		vec = new double[dim];
	}

	public NVector(int ... elems)
	{
		vec = new double[elems.length];
		
		for (int i = 0; i < elems.length; i++)
		{
			vec[i] = elems[i];
		}
	}

	public NVector(NVector other)
	{
		vec = new double[other.size()];

		for (int i = 0; i < other.size(); i++)
		{
			vec[i] = other.vec[i];
		}
	}

	public void set(int i, double val)
	{
		vec[i] = val;
	}

	public double get(int i)
	{
		return vec[i];
	}

	public int size()
	{
		return vec.length;
	}

	public double length()
	{
		double tmp = 0;

		for (int i = 0; i < vec.length; i++)
		{
			tmp += vec[i] * vec[i];
		}

		return Math.sqrt(tmp);
	}
	
	public NVector toOrt()
	{
		NVector tmp = new NVector(this);
		double len = length();

		for (int i = 0; i < tmp.size(); i++)
		{
			tmp.vec[i] /= len;
		}

		return tmp;
	}

	public double dot(NVector other) throws Exception
	{
		if (size() != other.size())
		{
			throw new Exception("LOL3");
		}

		double tmp = 0;

		for (int i = 0; i < size(); i++)
		{
			tmp += vec[i] * other.vec[i];
		}

		return tmp;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Size: " + size() + "\n");
		
		for (int i = 0; i < size(); i++)
		{
			sb.append(vec[i] + " ");
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof NVector)
		{
			NVector other = (NVector)obj;

			return Arrays.equals(vec, other.vec);
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size(); i++)
		{
			sb.append(vec[i]);
		}

		return sb.toString().hashCode();
	}
}
