package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

	//Comment
	/*Multiline comment      */
	/**
	 * This class consists of generic methods to read data from property file
	 * @author hnraj
	 */

public class PropertyFileUtility 
{
	
	/**
	 * This method reads data from Property file and returns value to the caller
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
		
	}
}
