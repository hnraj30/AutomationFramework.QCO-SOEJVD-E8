package practice;

import java.io.IOException;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice 
{
	public static void main(String[]args) throws IOException
	{
		//Reading data from property file
		PropertyFileUtility pUtility = new PropertyFileUtility();
		String value = pUtility.readDataFromPropertyFile("username");
		System.out.println(value);
		
		//Reading data from Excel file
		ExcelFileUtility eUtility= new ExcelFileUtility();
		String value2 = eUtility.readDataFromExcelFile("Organization", 7, 2);
		System.out.println(value2);
		
		//Generating random number
		JavaUtility jUtility = new JavaUtility();
		int randomNum = jUtility.getRandomNumber();
		System.out.println(randomNum);
		
		//Generating System date along with time
		String date = jUtility.getSystemDate();
		System.out.println(date);


	}

}
