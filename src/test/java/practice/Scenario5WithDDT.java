package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
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

public class Scenario5WithDDT 
{
	public static void main(String[]args) throws EncryptedDocumentException, IOException, Throwable
	{
		//Read  all data from Property file
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//Read  all data from Excel file
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String LASTNAME = wb.getSheet("Contacts").getRow(7).getCell(2).getStringCellValue();
		String ORGANIZATION = wb.getSheet("Contacts").getRow(7).getCell(3).getStringCellValue();
		System.out.println(ORGANIZATION);
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else if (BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else if (BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		Set<String> child = driver.getWindowHandles();
		String parent = driver.getWindowHandle();
		for(String d:child)
		{
			driver.switchTo().window(d);
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='"+ORGANIZATION+"']")).click();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
		String contactHeader = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']")).getText();
		if (contactHeader.contains(LASTNAME))
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
