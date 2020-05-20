import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Client
{
	private Socket socket;
	private PrintWriter outInfo;
	private BufferedReader inInfo;

	private Scanner scanner;

	private Transferer transferer = null;

	public Client()
	{
		scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
	}

	public void connect(String ip, int port)
	{
		try
		{
			socket = new Socket(ip, port);

			outInfo = new PrintWriter(socket.getOutputStream(), true);
			inInfo = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			printResponse("Connected to " + ip + ":" + port, true);
		}
		catch (Exception ex)
		{
		}

		go();
	}

	private String readRequest()
	{	
		return scanner.nextLine();
	}

	private String readResponse()
	{
		String wasRead = null;

		try
		{
			wasRead = inInfo.readLine();
		}
		catch (Exception ex)
		{
		}
				
		return wasRead;
	}

	private void printResponse(String response, boolean newLine)
	{
		System.out.println("ftp> " + response);

		if (newLine)
		{
			System.out.println("ftp> ");
		}
		else
		{
			System.out.print("ftp> ");
		}
	}

	private boolean checkTransfer(String request)
	{
		String splited[] = request.split(" ");
		String command = splited[0];

		switch (command)
		{
			case "put":
			{
				break;	
			}
			case "get":
			{
				File file = new File(splited[2]);
				String parentPath = file.getParent();

				File parent = new File(parentPath);

				if (!parent.exists() || !parent.isDirectory())
				{
					printResponse("Parent directory does not exist", false);

					return true;
				}

				transferer = new Transferer(request, 0);
				transferer.start();
				
				request += " port " + transferer.getPort();
				outInfo.println(request);

				waitTransfer();

				return true;
			}
		}

		return false;
	}

	private void waitTransfer()
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

			String response = readResponse();

			if (response.equals("Invalid request") || 
				response.equals("File does not exist") ||
				response.equals("File is a directory"))
			{	
				transferer.close();
			}

			if (response.equals("Transferring..."))
			{
				printResponse(response, true);
			}
		}
		
		transferer = null;
	}

	private void go()
	{
		while (true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (Exception ex)
			{
			}

			String response = readResponse();

			if (response == null)
			{
				return;
			}

			printResponse(response, false);

			if (response.equals("Invalid login") || response.equals("Invalid password"))
			{
				return;
			}

			String request = readRequest();

			if (!checkTransfer(request))
			{	
				outInfo.println(request);
			}
		}
	}

	public void close() throws IOException
	{
		if (socket != null)
		{
			outInfo.close();
			inInfo.close();
			socket.close();
		}
		else 
		{	
			printResponse("Failed to connect", true);
		}

		if (transferer != null)
		{
			transferer.close();
		}
	}
}
