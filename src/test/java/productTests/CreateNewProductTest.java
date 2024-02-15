package productTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewProductPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductInformationPage;
import objectRepository.ProductsPage;

public class CreateNewProductTest extends BaseClass
{
	@Test(groups = {"SmokeSuite","RegressionSuite"})
	
	public void createNewProduct() throws IOException, InterruptedException
	{
		String PRODUCTNAME = eUtil.readDataFromExcelFile("Products", 1, 2)+jUtil.getRandomNumber();
		
		//click on products 
		HomePage hp = new HomePage(driver);
		hp.clickOnProductsTab();
		
		//click on create new product button
		ProductsPage pp = new ProductsPage(driver);
		pp.clickOnCreateProdImg();
		
		//create new product
		CreateNewProductPage cnp = new CreateNewProductPage(driver);
		cnp.createNewProduct(PRODUCTNAME);
		
		Thread.sleep(3000);
		//Get text from product header
		ProductInformationPage pip = new ProductInformationPage(driver);
		String productHeader = pip.getProductHeaderText();
		System.out.println(productHeader);
		
		if (productHeader.contains(PRODUCTNAME))
		{
			System.out.println("Product created successfully");
		}
		else
		{
			System.out.println("Product not created");

		}
		

	}

}
