import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class Tester
{
	private ArrayList< Party > parties;
	private TreeMap < Client, Party > games;

	private File clientsInfo;
	private Scanner clientsScanner;

	private BufferedWriter writer;

	public Tester()
	{
		parties = new ArrayList<>();
		games = new TreeMap<>();
		
		clientsInfo = new File("./clients_info.txt");

		try
		{
			writer = new BufferedWriter(new FileWriter("./output.txt"));
		
			clientsScanner = new Scanner(clientsInfo);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		clientsScanner.useDelimiter("\n");

		initParties();
		initClients();
	}

	private void initParties()
	{
		// ArrayList < Client > clients = Server.getClientsWhoHaveMessages();

		for (int i = 0; i < 10; i++)
		{
			parties.add(new Party(i));
		}
	}

	private void initClients()
	{
		/*
			party index
			client id
			client ip
			client port
		*/

		while (clientsScanner.hasNextLine())
		{
			int partyInd = clientsScanner.nextInt();
			clientsScanner.nextLine();
		
			Client client = new Client();
			client.read(clientsScanner);

			games.put(client, parties.get(partyInd));
		}
	}

	public void test()
	{
		for (Map.Entry<Client, Party> entry : games.entrySet()) 
		{
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}

		Scanner input = new Scanner(System.in);
		input.useDelimiter("\n");

		// ENTER FIRST CLIENT

		System.out.println("\nPlease enter the client info to be searched:");
		
		int id = input.nextInt();
		input.nextLine();
		String ip = input.nextLine();
		int port = input.nextInt();

		Client toSearchClient = new Client(id, ip, port);
		if (games.containsKey(toSearchClient))
		{
			System.out.println("\nKey was found!");

			System.out.println(games.get(toSearchClient));
		}
		else
		{
			System.out.println("\nKey was not found!");
		}

		System.out.println("\nHere are all the keys:");
		
		write("After search:");

		for (Client c : games.keySet())
		{
			System.out.println(c);
			write(c.toString());
		}

		// ENTER SECOND CLIENT
		
		System.out.println("\nPlease enter the client info to be deleted:");

		id = input.nextInt();
		input.nextLine();
		ip = input.nextLine();
		port = input.nextInt();		
		
		toSearchClient = new Client(id, ip, port);
		
		if (games.containsKey(toSearchClient))
		{
			System.out.println("\nKey was found!");

			games.remove(toSearchClient);
		}
		else
		{
			System.out.println("\nKey was not found!");
		}

		System.out.println("\nHere are all the keys:");
	
		write("\nAfter deletion:");

		for (Client c : games.keySet())
		{
			System.out.println(c);
			write(c.toString());
		}

		try
		{
			writer.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void write(String what)
	{
		try
		{
			writer.write(what);
			writer.write("\n");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
