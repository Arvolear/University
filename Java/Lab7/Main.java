public class Main
{
	public static void main(String args[])
	{	
		AdderWrapper adder = new AdderWrapper();
		
		System.out.println("\nAdding first 10 prime numbers...");
		System.out.println("\nResult = " + adder.primes(10));

		System.out.println("\nAdding first 10 even numbers...");
		System.out.println("\nResult = " + adder.evens(10));
		
		System.out.println("\nAdding first 10 odd numbers...");
		System.out.println("\nResult = " + adder.odds(10));
		
		System.out.println("\nAdding first 10 Fibonacci numbers...");
		System.out.println("\nResult = " + adder.fibos(10) + "\n");
	}
}
