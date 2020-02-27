public class Main
{
	public static void main(String args[])
	{
		Rectangle rec = null;

		try
		{
			rec = new Rectangle(60, 20, 1, 3);
		}
		catch (Exception ex)
		{
			System.out.println(ex);	
		}

		System.out.println(rec);
	}
}
