package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class TestPropertiesReader
{

	private static String configFilePath = new String("ApplicationDetails.properties");
	private static String seqFilePath = new String("seq.properties");

	//static Properties properties = new Properties();
	//static InputStream input = null;

	public static String propertiesWriter(String filePath, String num) throws IOException{
		Properties properties = new Properties();
		InputStream input = new FileInputStream(filePath); 
		OutputStream os = new FileOutputStream(filePath);
		properties.load(input);
		
	//	String num = sequence("sequence");
		input.close();
		System.out.println("seq number"+num);
		int va = Integer.parseInt(num)+1;
		String value = Integer.toString(va);
		System.out.println("current seq num "+value);
		properties.setProperty("sequence", value);
		properties.store(os, null);
	    os.close();	
	    
		return value;
	}
	
	public static String propertiesReader(String propertyKey, String filePath)
	{
		Properties properties = new Properties();
		InputStream input = null;
		String propertyValue = new String();

		try
		{
			input = new FileInputStream(filePath);
			properties.load(input);

			propertyValue = properties.getProperty(propertyKey);

		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) {
				try
				{
					input.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return propertyValue;
	}

	

	public static String getTestEnvironmentPath() {
		return new String(propertiesReader("testEnvironmentPath", configFilePath));
	}

	public static String getBrowserTypeProperty() {
		return new String(propertiesReader("Browser", configFilePath));
	}
	public static String typeofExectuion() {
		return new String(propertiesReader("environment", configFilePath));
	}
	
	public static String getChromeDriverLocation() {
		return new String(propertiesReader("chromeDriverLocation", configFilePath));
	}

	public static String getRemoteMachineURL() {
		return new String(propertiesReader("remoteMachineURL", configFilePath));
	}

	public static String getFireBugPath() {
		return new String(propertiesReader("firebugFirefoxPath", configFilePath));
	}

	public static String getAdminUser() {
		return new String(propertiesReader("adminUserName", configFilePath));
	}
	
	public static String producerDataPostingFile() {
		return new String(propertiesReader("path", configFilePath));
	}	

	public static String getAdminUserPass() {
		return new String(propertiesReader("adminUserPass", configFilePath));
	}
}