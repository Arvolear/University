import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server
{
	private ServerSocket serverSocket;

	private ArrayList<ClientThread> clients;

	private boolean running;

	public Server()
	{
		clients = new ArrayList<>();
		running = true;
	}

	public void start(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (Exception ex)
		{
		}

		checkOldConnections();
		checkNewConnections();
	}

	private void checkNewConnections()
	{
		while (running)
		{
			try 
			{
				Socket clientSocket = serverSocket.accept();

				int found = -1;

				for (int i = 0; i < clients.size(); i++)
				{
					if (clients.get(i) == null)
					{
						found = i;

						synchronized (this)
						{
							clients.set(i, new ClientThread(clientSocket));
						}
					}
				}

				if (found == -1)
				{
					found = clients.size();

					synchronized (this)
					{
						clients.add(new ClientThread(clientSocket));
					}
				}

				clients.get(found).start();
			}
			catch (Exception ex)
			{
			}
		}
	}

	private void checkOldConnections()
	{
		Thread oldConnections = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (running)
				{
					try
					{
						Thread.sleep(100);
					}
					catch (Exception ex)
					{
					}

					synchronized (Server.this)
					{
						for (int i = 0; i < clients.size(); i++)
						{
							if (clients.get(i) != null && !clients.get(i).isRunning())
							{
								try
								{
									clients.get(i).close();
								}
								catch (Exception ex)
								{
									ex.printStackTrace();
								}

								clients.set(i, null);
							}
						}
					}
				}
			}
		});

		oldConnections.start();
	}

	public void close() throws IOException
	{
		running = false;

		synchronized (this)
		{
			for (int i = 0; i < clients.size(); i++)
			{
				if (clients.get(i) != null)
				{
					clients.get(i).close();
				}
			}
		}

		serverSocket.close();
	}
}
