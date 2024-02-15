package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario6WithDDT 
{
	public static void main(String[]args) throws IOException, InterruptedException
	
	{
		//Read all data from property file
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD+" \n" + USERNAME);
		
		//Read all data from Excel file
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String ProdName = wb.getSheet("Products").getRow(1).getCell(2).getStringCellValue();

		System.out.println(ProdName);
		
		//Launch Browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser launched successfully");
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser launched successfully");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser launched successfully");
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Login to the application
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on products tab
		driver.findElement(By.xpath("//a[.='Products']")).click();
		
		//Click on create new product look up icon
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();

		//Create new product with mandatory fields
		driver.findElement(By.name("productname")).sendKeys("Sample_Product_02");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(3000);
		
		//Validate Product
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
		System.out.println("Logged out succesfully");

		//Close the application
		driver.close();		
		
	}
	

}
