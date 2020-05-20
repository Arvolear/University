import java.net.*;
import java.io.*;

public class Transferer extends Thread
{
	private ServerSocket socket;

	private Socket fromSocket;
	private OutputStream outTrans;
	private InputStream inTrans;

	private String request;

	private int port;

	private boolean transferring = true;

	public Transferer(String request, int port)
	{
		this.request = request;

		this.port = port;
	
		try
		{
			socket = new ServerSocket(port);
		}
		catch (Exception ex)
		{
		}
	}
	
	private void doGet()
	{
		String splited[] = request.split(" ");
		String filePath = splited[2];
	
		File file = new File(filePath);

		if (file.isDirectory())
		{
			File actualFile = new File(splited[1]);
			String actualName = actualFile.getName();

			file = new File(filePath + "/" + actualName);
		}

		try
		{
			byte info[] = new byte[1024];
			int actuallyRead = inTrans.read(info);
			
			if (actuallyRead == -1)
			{
				return;	
			}

			file.createNewFile();
			FileOutputStream fileStream = new FileOutputStream(file);
			
			fileStream.write(info, 0, actuallyRead);

			while (true)
			{
				actuallyRead = inTrans.read(info);

				if (actuallyRead == -1)
				{
					fileStream.close();
					return;	
				}

				fileStream.write(info, 0, actuallyRead);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void doPut()
	{
	}

	@Override
	public void run()
	{
		try
		{
			fromSocket = socket.accept();

			outTrans = fromSocket.getOutputStream();
			inTrans = fromSocket.getInputStream();

			String splited[] = request.split(" ");
			String command = splited[0];

			switch (command)
			{
				case "get":
				{
					doGet();
					break;
				}
				case "put":
				{
					doPut();
					break;
				}
			}
		}
		catch (Exception ex)
		{
		}

		close();
	}

	public int getPort()
	{
		return socket.getLocalPort();
	}

	public boolean isTransferring()
	{
		return transferring;
	}

	public void close()
	{
		try
		{
			transferring = false;

			if (fromSocket != null)
			{
				outTrans.close();
				inTrans.close();
				fromSocket.close();
			}

			socket.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
