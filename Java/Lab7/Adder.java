public class Adder
{
	private Iterator iter; 

	public Adder(Iterator iter)
	{
		this.iter = iter;
	}

	public int count()
	{
		int res = 0;

		while (iter.hasNext())
		{
			res += iter.next();	
		}

		return res;
	}
}
