public class PrimeSequence implements Iterator
{
	int numberOfElements;
	int numberPastElements;

	int current;

	PrimeSequence(int numberOfElements)
	{
		this.numberOfElements = numberOfElements;
		this.numberPastElements = 0;
		
		this.current = 1;
	}

	@Override
	public int next()
	{
		if (!hasNext())
		{
			throw new RuntimeException("no next");
		}

		boolean isPrime = false;

		while (!isPrime)
		{
			current++;
			isPrime = true;

			for (int i = 2; i <= Math.round(Math.sqrt(current)); i++)
			{
				if (current % i == 0)
				{
					isPrime = false;	
				}
			}
		}

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
