public class Main
{
	public static void main(String args[])
	{
		NVector vec = null;
		
		try
		{
			vec = new NVector(5, 1, 2, 3, 4, 5);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}

		System.out.println(vec);
	}
}
