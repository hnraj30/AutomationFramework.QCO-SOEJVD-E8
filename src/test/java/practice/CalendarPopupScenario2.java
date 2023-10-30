package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopupScenario2 
{
	/**
	 * This program selects the future date in calendar popup
	 * @param args
	 * @throws InterruptedException
	 */
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
		
		while(true)//for(;;)
		{
			try
			{
				//click if visible handle exception if not visible
				driver.findElement(By.xpath("//div[@aria-label='Fri Dec 15 2023']")).click();
				break;
			}
			catch(Exception e)
			{
				//click on next month
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				Thread.sleep(3000);
			}
		}
		
		Thread.sleep(2000);
		driver.close();
		
	}

}
