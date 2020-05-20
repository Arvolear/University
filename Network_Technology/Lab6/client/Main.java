import java.io.*;

public class Main
{
	public static void main(String args[]) throws IOException
	{
		Client client = new Client();
		client.connect("127.0.0.1", 5041);
		client.close();
	}
}
