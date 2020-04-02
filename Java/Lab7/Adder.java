public class Adder
{
	public int count(Iterator iter)
	{
		int res = 0;

		while (iter.hasNext())
		{
			res += iter.next();	
		}

		return res;
	}
}
