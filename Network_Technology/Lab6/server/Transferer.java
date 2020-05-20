import java.net.*;
import java.io.*;

public class Transferer extends Thread
{
	private Socket toSocket;
	private OutputStream outTrans;
	private InputStream inTrans;

	private String ip;
	private int port;

	private String request;
	private String currentDir;

	private boolean transferring = true;

	public Transferer(String request, String currentDir, String ip, int port)
	{
		this.request = request;
		this.currentDir = currentDir;
		this.ip = ip;
		this.port = port;
	}
	
	private void doGet()
	{
		String splited[] = request.split(" ");
		String filePath = splited[1];
		
		File file = null;

		if (filePath.charAt(0) != '/')
		{
			file = new File(currentDir + filePath);
		}
		else
		{
			file = new File(filePath);
		}
	
		try
		{
			FileInputStream fileStream = new FileInputStream(file);
			
			byte[] data = new byte[(int) file.length()];
			
			fileStream.read(data);

			outTrans.write(data);

			fileStream.close();
			close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try
		{
			toSocket = new Socket(ip, port);

			outTrans = toSocket.getOutputStream();
			inTrans = toSocket.getInputStream();

			String splited[] = request.split(" ");
			String command = splited[0];

			switch (command)
			{
				case "get":
				{
					doGet();
					break;
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		close();
	}

	public boolean isTransferring()
	{
		return transferring;
	}

	public void close()
	{
		try
		{
			outTrans.close();
			inTrans.close();
			toSocket.close();

			transferring = false;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
