public class AdderWrapper
{
	private Adder adder;

	public int primes(final int amount)
	{
		adder = new Adder(new PrimeSequence(amount));

		return adder.count();
	}

	public int evens(final int amount)
	{
		adder = new Adder(new Iterator()
							{
								int numberOfElements = amount;
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

		return adder.count();
	}

	public int odds(final int amount)
	{
		class OddSequence implements Iterator
		{
			int numberOfElements = amount;
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

		adder = new Adder(new OddSequence());

		return adder.count();
	}

	class FiboSequence implements Iterator
	{
		int numberOfElements;
		int numberPastElements;
		int prev;
		int current;

		FiboSequence(int amount)
		{
			numberOfElements = amount;
			numberPastElements = 0;
			prev = 0;
			current = 1;
		}

		@Override
		public int next()
		{
			numberPastElements++;

			if (numberPastElements == 1)
			{
				System.out.print(prev + " ");
				return prev;
			}

			int tmp = current;
			current = prev;
			prev = tmp;

			current = current + prev;
			
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

	public int fibos(int amount)
	{
		adder = new Adder(new FiboSequence(amount));

		return adder.count();
	}
}
