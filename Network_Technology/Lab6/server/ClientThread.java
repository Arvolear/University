import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
	private static final String LOGIN = "admin";
	private static final String PASSWORD = "admin";

	private boolean loggedIn = false;

	private Socket infoSocket;

	private PrintWriter outInfo;
	private BufferedReader inInfo;

	private Informator informator; 

	private boolean running;

	Transferer transferer = null;

	public ClientThread(Socket infoSocket)
	{
		this.infoSocket = infoSocket;	
		this.informator = new Informator();		
		this.running = true;

		try
		{
			outInfo = new PrintWriter(infoSocket.getOutputStream(), true);
			inInfo = new BufferedReader(new InputStreamReader(infoSocket.getInputStream()));
		
			System.out.println("New client connected");
		}
		catch (Exception ex)
		{
		}
	}

	private boolean isValid(String request)
	{
		String splited[] = request.split(" ");
		String command = splited[0];

		switch (command)
		{
			case "ls":
			case "cd":
			{
				if (splited.length > 2)
				{
					return false;
				}

				break;
			}

			case "pwd":
			{
				if (splited.length != 1)
				{
					return false;
				}

				break;
			}

			case "mkdir":
			{
				if (splited.length != 2)
				{
					return false;
				}

				break;
			}
			
			case "get":
			{
				if (splited.length != 5)
				{
					return false;
				}

				break;
			}

			case "quit":
			{
				break;
			}

			default:
			{
				return false;
			}
		}

		return true;
	}

	private void waitTransferer()
	{
		while (transferer != null && transferer.isTransferring())
		{
			try
			{
				Thread.sleep(100);
			}
			catch (Exception ex)
			{
			}
		}

		outInfo.println("Completed");
		transferer = null;
	}

	private void proceed(String request)
	{
		String splited[] = request.split(" ");
		String command = splited[0];
		
		String response = informator.getFullResponse(request);

		switch (command)
		{
			case "quit":
			{
				close();
				return;
			}
			case "get":
			{
				if (response.equals("Transferring..."))
				{
					outInfo.println("Transferring...");
					
					transferer = new Transferer(request, informator.getCurrentDir(), infoSocket.getInetAddress().toString().replace("/", ""), Integer.parseInt(splited[4]));
					transferer.start();
		
					waitTransferer();
				}

				break;
			}
		}

		System.out.println(request);
		System.out.println(response);
	}

	private boolean login()
	{
		if (loggedIn)
		{
			return true;
		}

		try
		{
			outInfo.println("Login : ");
			String login = inInfo.readLine();

			if (!login.equals(LOGIN))
			{
				outInfo.println("Invalid login");
				return false;
			}

			outInfo.println("Password : ");
			String password = inInfo.readLine();

			if (!password.equals(PASSWORD))
			{
				outInfo.println("Invalid password");
				return false;
			}
		}
		catch (Exception ex)
		{
		}

		outInfo.println(informator.getCurrentDir());
		loggedIn = true;

		return true;
	}

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

			try
			{
				if (!login())
				{
					close();
					return;
				}

				String request = inInfo.readLine();

				if (request == null)
				{
					close();
					return;
				}

				if (isValid(request))
				{
					proceed(request);
				}
				else
				{
					outInfo.println("Invalid request");
					System.out.println("Invalid request");
				}
			}
			catch (Exception ex)
			{
				close();
				return;
			}
		}
	}

	public boolean isRunning()
	{
		return running;
	}

	public void close()
	{
		try
		{
			this.running = false;

			outInfo.close();
			inInfo.close();
			infoSocket.close();

			if (transferer != null)
			{
				transferer.close();
			}

			System.out.println("Client disconnected");
		}
		catch (Exception ex)
		{
		}
	}
}
