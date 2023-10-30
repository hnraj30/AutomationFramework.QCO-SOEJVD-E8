package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of generic methods related to java
 * @author hnraj
 *
 */
public class JavaUtility 

{
	/**
	 * This method will generate random number and return it to the caller for every run
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran = new Random();
		int r = ran.nextInt(10000);
		return r;
	}
	
	/**
	 * This method capture the current date in required format
	 * @return
	 */
	public String getSystemDate()
	{
		Date d = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy_hh-mm-ss");
		String date = formatter.format(d);
		return date;
	}
	
	

}
