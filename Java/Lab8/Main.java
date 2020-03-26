import java.lang.reflect.*;

public class Main
{
	public static void main(String args[])
	{
		/* 1 way */
		Class<?> s1 = "getClass".getClass();
		
		/* modifiers */
		int modifiers = s1.getModifiers();
		System.out.println("String modifiers:\n" + Modifier.toString(modifiers) + "\n");

		/* constructors */
		Constructor<?> constructors[] = s1.getConstructors();

		System.out.println("String constructors:");

		for (int i = 0; i < constructors.length; i++)
		{
			System.out.println(i + ") " + constructors[i].toString());
		}
		System.out.println();

		/* 2 way */
		Class<?> s2 = String.class;
		
		/* fields */
		Field fields[] = s2.getFields();

		System.out.println("String fields:");

		for (int i = 0; i < fields.length; i++)
		{
			System.out.println(i + ") " + fields[i].toString());
		}
		System.out.println();

		/* 3 way */
		Class<?> s3 = null;
		
		try
		{
			s3 = Class.forName("java.lang.String");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		/* methods */
		Method methods[] = s3.getMethods();

		System.out.println("String methods:");

		for (int i = 0; i < methods.length; i++)
		{
			System.out.println(i + ") " + methods[i].toString());
		}
		System.out.println();

		System.out.println("Calling substring(5) method on \"1234567890\":");
		String res = null;
			
		try
		{
			res = (String)methods[45].invoke("1234567890", 5);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Result: " + res + "\n");
	}
}
