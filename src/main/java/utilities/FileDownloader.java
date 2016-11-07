package utilities;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader 
{
	final static int size = 1024;

	public static void fileUrl(String fAddress, String localFileName,
			String destinationDir)
	{
		OutputStream outStream = null;
		URLConnection uCon = null;
        InputStream is = null;
		try
		{
			URL Url;
			byte[] buf;
			int ByteRead, ByteWritten = 0;
			Url = new URL(fAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "\\" + localFileName));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[size];

			while ((ByteRead = is.read(buf)) != -1)
			{
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}

			System.out.println("Downloaded Successfully.");

			System.out.println("File name:\"" + localFileName+ "\"\nNo of bytes :" + ByteWritten);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				is.close();
				outStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void fileDownload(String fAddress, String destinationDir) 
	{
		int slashIndex = fAddress.lastIndexOf('/');
		int periodIndex = fAddress.lastIndexOf('.');

		String fileName = fAddress.substring(slashIndex + 1);

		if (periodIndex >= 1 && slashIndex >= 0
				&& slashIndex < fAddress.length() - 1) 
		{
			fileUrl(fAddress, fileName, destinationDir);
		}
		else
		{
			System.err.println("path or file name.");
		}
	}
}
