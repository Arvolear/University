import java.util.HashMap;

public class Main
{
	public static void main(String args[])
	{
		HashMap<A, String> map = new HashMap<>();

		map.put(new A("A1"), "A1");
		map.put(new A("A1"), "A1");
		map.put(new A("A1"), "A1");
		map.put(new A("A2"), "A2");

		System.out.println("\n------- COLLISION MAP --------\n");

		for (HashMap.Entry<A, String> entry: map.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + "\nValue = " + entry.getValue());
		}
		
		System.out.println("\n------- FINE MAP --------\n");

		HashMap<B, String> map2 = new HashMap<>();

		map2.put(new B("B1"), "B1");
		map2.put(new B("B1"), "B1");
		map2.put(new B("B1"), "B1");
		map2.put(new B("B2"), "B2");
		
		for (HashMap.Entry<B, String> entry: map2.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + "\nValue = " + entry.getValue());
		}
	}
}

class A
{
	private String str;

	A(String str)
	{
		this.str = str;
	}

	@Override
	public int hashCode()
	{
		return (int)(Math.random() * 1000);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof A)
		{
			A other = (A)obj;
	
			return str.equals(other.str);
		}

		return false;
	}
}

class B
{
	private String str;

	B(String str)
	{
		this.str = str;
	}

	@Override
	public int hashCode()
	{
		return str.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof B)
		{
			B other = (B)obj;
	
			return str.equals(other.str);
		}

		return false;
	}
}
