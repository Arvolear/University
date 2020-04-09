public class Main
{
	public static AdderWrapper wrapper;

	public static int primes(final int amount)
	{
		return wrapper.count(new PrimeSequence(amount));
	}
	
	public static int evens(final int amount)
	{
		return wrapper.count(new Iterator()
							{
								int numberOfElements = amount; // benefit
								int numberPastElements = 0;
								int current = -2;

								@Override
								public int next()
								{
									current += 2;
									numberPastElements++;
		
									System.out.print(current + " ");
									return current;
								}

								@Override
								public boolean hasNext()
								{
									if (numberPastElements + 1 > numberOfElements)
									{
										return false;
									}

									return true;
								}
							});
	}
	
	public static int odds(final int amount)
	{
		class OddSequence implements Iterator
		{
			int numberOfElements = amount; // benefit
			int numberPastElements = 0;
			int current = -1;

			@Override
			public int next()
			{
				current += 2;
				numberPastElements++;

				System.out.print(current + " ");
				return current;
			}

			@Override
			public boolean hasNext()
			{
				if (numberPastElements + 1 > numberOfElements)
				{
					return false;
				}

				return true;
			}
		}

		return wrapper.count(new OddSequence());
	}

	public static int fibos(int amount)
	{
		return wrapper.count(new AdderWrapper().new FiboSequence(amount));
	}

	public static void main(String args[])
	{	
		wrapper = new AdderWrapper();
		
		System.out.println("\nAdding first 10 prime numbers...");
		System.out.println("\nResult = " + primes(10));

		System.out.println("\nAdding first 10 even numbers...");
		System.out.println("\nResult = " + evens(10));
		
		System.out.println("\nAdding first 10 odd numbers...");
		System.out.println("\nResult = " + odds(10));
		
		System.out.println("\nAdding first 10 Fibonacci numbers...");
		System.out.println("\nResult = " + fibos(10) + "\n");
	}
}
