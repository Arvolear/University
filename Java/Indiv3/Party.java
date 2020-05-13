import java.util.ArrayList;

public class Party
{
	int id;

	private ArrayList < Client > clients;

	public Party(int id)
	{
		this.id = id;
		clients = new ArrayList<>();
	}

	public void addClient(Client client)
	{
		clients.add(client);
	}

	public void update()
	{
		// ...
	}

	@Override
	public String toString()
	{
		return "party " + id;
	}
}
