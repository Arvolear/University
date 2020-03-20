public class Main
{
	public static void main(String args[])
	{
		Base base = new Derived(88);
		Derived der = new Derived(99);

		System.out.println("\n --- BASE = DERIVED --- ");
		base.function();
		System.out.println("Val = " + base.getVal());
		System.out.println("Base Dummy = " + base.getBaseDummy());
		Base.staticFunction();
		Base.staticDummy();
		
		System.out.println("\n --- DERIVED = DERIVED --- ");
		der.function();
		System.out.println("Val = " + der.getVal());
		System.out.println("Base Dummy = " + der.getBaseDummy());
		System.out.println("Derived Dummy = " + der.getDerivedDummy());
		Derived.staticFunction();
		Derived.staticDummy();
	}
}
