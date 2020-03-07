public class BankAccount
{
	public static double totalBalance = 0;
	public static double percentage = 5;

	private double balance;

	public BankAccount(double balance) throws Exception
	{
		if (balance < 0)
		{
			throw new Exception("Negative balance!");
		}

		this.balance = balance;
	}

	public BankAccount()
	{
		balance = 0.0;
	}

	public void replenish(double amount)
	{
		if (amount < 0)
		{
			return;
		}

		balance += amount;
		totalBalance += amount;
	}

	public void withdraw(double amount) throws Exception
	{
		if (amount < 0)
		{
			throw new Exception("Negative withdraw!");	
		}

		if (balance - amount < 0)
		{
			throw new Exception("Not enough money!");
		}

		balance -= amount;
		totalBalance -= amount;
	}

	public void incWage()
	{
		double inc = balance * (percentage / 100.0);

		replenish(inc);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof BankAccount)
		{
			BankAccount other = (BankAccount)obj;

			return balance == other.balance;
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return Double.hashCode(balance);	
	}

	@Override
	public String toString()
	{
		return "Balance:\n" + balance;
	}
}
