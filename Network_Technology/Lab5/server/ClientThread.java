import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
	private Socket clientSocket;

	private OutputStream out;
	private BufferedReader in;

	private Provider provider; 

	private boolean running;

	public ClientThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;	
		this.provider = new Provider();		
		this.running = true;

		try
		{
			out = clientSocket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch (Exception ex)
		{
		}
	}

	@Override
	public void run()
	{
		try 
		{
			String request = in.readLine();

			byte[] response = provider.getFullResponse(request);
			out.write(response);
			
			System.out.println(request);
			System.out.println(response);
		
			running = false;
		}
		catch (Exception ex)
		{
		}
	}

	public boolean isRunning()
	{
		return running;
	}

	public void close() throws IOException
	{
		this.running = false;

		out.close();
		in.close();
		clientSocket.close();
	}
}
