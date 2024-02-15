package practice;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario6 
{
	public static void main(String[]args) throws InterruptedException
	{
		//Launch Browser
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Enter URL
		driver.get("http://localhost:8888/");
		//Login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Click on Products tab
		driver.findElement(By.xpath("//a[.='Products']")).click();
		
		//Click on create new product lookup icon
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		
		//Create product with mandatory fields
		driver.findElement(By.name("productname")).sendKeys("Sample_Product_02");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(3000);
		//validate product
		String productHeader = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if(productHeader.contains("Sample_Product_02"))
		{
			System.out.println("Product created successfullty");
		}
		else
		{
			System.out.println("Product creation failed");
		}
		//Logout
		WebElement adminIcon = driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//Close the application
		driver.close();
		
		
		
		
	
	}
	
	

}
