public class Base
{
	protected int val;
	protected int dummy;

	public Base(int val)
	{
		this.val = val;
		this.dummy = 1;

		System.out.println("Base constructor");
		function();
	}

	public static void staticFunction()
	{
		System.out.println("Base static function is called");
	}

	public static void staticDummy()
	{
		System.out.println("Base static dummy is called");
	}

	public void function()
	{
		System.out.println("Base function is called");
	}

	public int getVal()
	{
		return val;
	}

	public int getBaseDummy()
	{
		return dummy;
	}
}
