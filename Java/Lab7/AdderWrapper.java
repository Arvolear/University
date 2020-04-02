public class AdderWrapper
{
	private Adder adder;

	public AdderWrapper()
	{
		adder = new Adder();
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

	public int count(Iterator inter)
	{
		return adder.count(inter);
	}
}
