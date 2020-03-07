public class Main
{
	public static void main(String args[])
	{
		BankAccount BA = new BankAccount();

		System.out.println("Total Balance:\n" + BankAccount.totalBalance);

		BA.replenish(10);	
		System.out.println(BA);

		BA.incWage();
		
		System.out.println("\nTotal Balance:\n" + BankAccount.totalBalance);
		System.out.println(BA);

		try
		{
			BA.withdraw(6.0);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		System.out.println("\nTotal Balance:\n" + BankAccount.totalBalance);
		System.out.println(BA);
	}
}
