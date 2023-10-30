package practice;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario4WithDDT 
{
	public static void main(String[] args) throws Throwable 
	{
		/*Read all necessary data*/ 
		/*read data from property file*/
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String BROWSER = p.getProperty("browser");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		/*read data from excel file*/
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String ORGANIZATION = wb.getSheet("Organization").getRow(7).getCell(2).getStringCellValue();
		String INDUSTRY = wb.getSheet("Organization").getRow(7).getCell(3).getStringCellValue();
		String TYPE = wb.getSheet("Organization").getRow(7).getCell(4).getStringCellValue();

		
		WebDriver driver = null;
		//Launch Browser // Runtime Polymorphism - driver
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+ " launched successfully");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER+ "launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER+ "launched");
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGANIZATION);
		WebElement industryDD = driver.findElement(By.xpath("//select[@name='industry']"));	
		Select s1 = new Select(industryDD);
		s1.selectByVisibleText(INDUSTRY);
		WebElement typeDD = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select s2 = new Select(typeDD);
		s2.selectByVisibleText(TYPE);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
		String contactHeader = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		if (contactHeader.contains(ORGANIZATION))
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Signed out successfully");
		Thread.sleep(2000);
		driver.close();	

	}

}

