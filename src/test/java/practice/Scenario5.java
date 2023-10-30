package practice;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5 
{
		public static void main(String[] args) throws Throwable
		{
			//Launch Browser
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("http://localhost:8888");
			driver.findElement(By.name("user_name")).sendKeys("admin");
			driver.findElement(By.name("user_password")).sendKeys("admin");
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Rajendra");
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("NH");
			driver.findElement(By.xpath("//img[@title='Select']")).click();
			
			Set<String> child = driver.getWindowHandles();
			String parent = driver.getWindowHandle();
			for(String d:child)
			{
				driver.switchTo().window(d);
			}
			driver.findElement(By.id("1")).click();
			driver.switchTo().window(parent);
			driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
			String contactHeader = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']")).getText();
			if (contactHeader.contains("NH"))
			{
				System.out.println("Pass");
			 }
			 else
			{
				System.out.println("Fail");
			}
			Thread.sleep(3000);
			WebElement signOut = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
			Actions a = new Actions(driver);
			a.moveToElement(signOut).perform();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			System.out.println("Signed out successfully");
			Thread.sleep(2000);
			driver.close();
		}
}
