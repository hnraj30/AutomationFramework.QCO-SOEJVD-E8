package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile 
{
	public static void main(String []args) throws IOException
	{
		//Step1: Open the document in Java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Step2: Create object of properties class
		Properties p = new Properties();
		
		//Step3: Load the file input stream into properties
		p.load(fis);
		
		//Step3: Provide the key and read the value
		System.out.println(p.getProperty("browser"));
		System.out.println(p.getProperty("url"));
		System.out.println(p.getProperty("username"));
		System.out.println(p.getProperty("password"));
		System.out.println(p.getProperty("asdf"));
		
		
		
		
	}

}
