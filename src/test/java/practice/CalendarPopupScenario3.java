package practice;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopupScenario3 
{
	public static void main(String[]args) throws InterruptedException
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.makemytrip.com/");
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		a.moveByOffset(10, 10).click().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		Thread.sleep(2000);
		
		Date d = new Date();
		String[] dArr = d.toString().split(" ");
		String currentDate = dArr[0]+" "+dArr[1]+" "+dArr[2]+" "+dArr[5];
		System.out.println(currentDate);
		
		// navigate to desired date in calender
		Thread.sleep(1000);                       //dynamic xpath
		driver.findElement(By.xpath("//div[@aria-label='"+currentDate+"']")).click();
				                     //div[@aria-label='Sat Jul 08 2023']
				                     //div[@aria-label='Sat Jul 09 2023']
				                     //div[@aria-label='Sat Jul 18 2023']
		
		driver.close();
		
	}


}
