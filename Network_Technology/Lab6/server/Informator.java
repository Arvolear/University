import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Informator
{
	private File file;

	public Informator()
	{
		file = new File("/home/artem");
	}

	public String getCurrentDir()
	{
		try
		{
			return file.getCanonicalPath() + "/";
		}
		catch (Exception ex)
		{
		}

		return "";
	}

	private String getLs(String request)
	{
		String splited[] = request.split(" ");
		String second;

		if (splited.length < 2)
		{
			second = ".";
		}
		else
		{
			second = splited[1];
		}

		StringBuilder builder = new StringBuilder();

		File toLsFile = null;

		if (second.charAt(0) != '/')
		{
			toLsFile = new File(getCurrentDir() + second);
		}
		else
		{
			toLsFile = new File(second);
		}

		if (toLsFile.exists())
		{
			if (toLsFile.isDirectory())
			{
				File files[] = toLsFile.listFiles();

				try
				{
					builder.append(toLsFile.getCanonicalPath());
					builder.append(" : ");
				}
				catch (Exception ex)
				{
				}

				for (int i = 0; i < files.length; i++)
				{
					if (!files[i].isHidden())
					{
						builder.append(files[i].getName());
						builder.append(" ");
					}
				}

				return builder.toString();
			}
			else
			{
				return "File is not a directory";
			}
		}

		return "File does not exist";
	}

	private String getCd(String request)
	{
		String splited[] = request.split(" ");
		String second;

		if (splited.length < 2)
		{
			second = "/home/artem";
		}
		else
		{
			second = splited[1];
		}

		StringBuilder builder = new StringBuilder();

		File toCdFile = null;

		if (second.charAt(0) != '/')
		{
			toCdFile = new File(getCurrentDir() + second);
		}
		else
		{
			toCdFile = new File(second);
		}

		if (toCdFile.exists())
		{
			if (toCdFile.isDirectory())
			{
				try
				{
					file = new File(toCdFile.getCanonicalPath());

					return toCdFile.getCanonicalPath() + " : ";
				}
				catch (Exception ex)
				{
				}
			}
			else
			{
				return "File is not a directory";
			}
		}

		return "File does not exist";
	}

	private String getPwd()
	{
		try
		{
			return file.getCanonicalPath();
		}
		catch (Exception ex)
		{
		}

		return "";
	}

	private String getGet(String request)
	{
		String splited[] = request.split(" ");
		String second = splited[1];

		File fromFile = null;

		if (second.charAt(0) != '/')
		{
			fromFile = new File(getCurrentDir() + second);
		}
		else
		{
			fromFile = new File(second);
		}

		if (!fromFile.exists())
		{
			return "File does not exist";
		}

		if (fromFile.isDirectory())
		{
			return "File is a directory";
		}

		return "Trasferring...";
	}

	public String getFullResponse(String request)
	{
		String splited[] = request.split(" ");
		String command = splited[0];
		String response = "";

		switch (command)
		{
			case "ls":
				{
					response = getLs(request);
					break;
				}
			case "cd":
				{
					response = getCd(request);
					break;
				}
			case "pwd":
				{
					response = getPwd();
					break;
				}
			case "get":
				{
					response = getGet(request);
					break;
				}
		}

		return response;
	}
}
