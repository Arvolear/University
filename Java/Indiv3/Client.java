import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Client implements Comparable < Client >
{
	private int id;
	private String ip;
	private int port;

	private ArrayList < String > messages;

	public Client()
	{
		id = 0;
		ip = "";
		port = 0;
	}
	
	public Client(int id, String ip, int port)
	{
		this.id = id;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public int compareTo(Client other)
	{
		return id - other.id;
	}

	public void read(Scanner scanner)
	{
		id = scanner.nextInt();
		scanner.nextLine();
		ip = scanner.nextLine();
		port = scanner.nextInt();
		scanner.nextLine();
	}

	@Override
	public String toString()
	{
		String ans = id + ":" + ip + ":" + port;

		return ans;
	}
}
