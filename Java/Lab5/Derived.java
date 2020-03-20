public class Derived extends Base
{
	private int dummy;

	public Derived(int val)
	{
		super(val);
		this.dummy = 2;
	}

	public static void staticDummy()
	{
		System.out.println("Derived static dummy is called");
	}

	@Override
	public void function()
	{
		System.out.println("Derived function is called");
	}
	
	public int getDerivedDummy()
	{
		return dummy;
	}
}
