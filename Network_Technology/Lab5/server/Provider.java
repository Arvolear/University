import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Provider
{
	private String extractMethod(String request)
	{
		String splited[] = request.split(" ");

		return splited[0];	
	}

	private String extractPath(String request)
	{
		String splited[] = request.split(" ");

		return ".." + splited[1];
	}

	private byte[] getHeader(int code)
	{
		StringBuilder builder = new StringBuilder();

		builder.append("HTTP/1.1 " + code);

		if (code == 200)
		{
			builder.append(" OK");
		}
		else if (code == 404)
		{
			builder.append(" Not Found");
		}
		else
		{
			builder.append(" Unknown Error");
		}
			
		builder.append("\nConnection: close");

		builder.append("\n\r\n");

		return builder.toString().getBytes();
	}

	private byte[] getFile(File file)
	{
		try
		{
			FileInputStream fis = new FileInputStream(file);
			
			byte[] data = new byte[(int) file.length()];
			
			fis.read(data);
			fis.close();

			return data;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return new byte[0];
	}

	public byte[] getFullResponse(String request)
	{
		ArrayList<Byte> bytes = new ArrayList<>();

		if (extractMethod(request).equals("GET"))
		{
			File file = new File(extractPath(request));

			byte[] tmp = null;

			if (file.exists())
			{
				tmp = getHeader(200);
				
				for (int i = 0; i < tmp.length; i++)
				{
					bytes.add(tmp[i]);
				}

				if (file.isFile())
				{
					tmp = getFile(file);
				}
				else
				{
					tmp = getFile(new File(file.getPath() + "/index.html"));
				}
			}
			else
			{
				tmp = getHeader(404);
					
				for (int i = 0; i < tmp.length; i++)
				{
					bytes.add(tmp[i]);
				}

				tmp = getFile(new File("../folder/404.html"));
			}
				
			for (int i = 0; i < tmp.length; i++)
			{
				bytes.add(tmp[i]);
			}
		}
		else
		{

		}

		byte[] ans = new byte[bytes.size()];

		for (int i = 0; i < bytes.size(); i++)
		{
			ans[i] = bytes.get(i);
		}

		return ans;
	}
}
