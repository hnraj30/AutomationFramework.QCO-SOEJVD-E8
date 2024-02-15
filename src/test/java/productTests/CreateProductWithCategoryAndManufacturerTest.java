package productTests;

import java.io.IOException;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.CreateNewProductPage;
import objectRepository.HomePage;
import objectRepository.ProductInformationPage;
import objectRepository.ProductsPage;

public class CreateProductWithCategoryAndManufacturerTest extends BaseClass
{
	@Test(groups = "SmokeSuite")
	
	public void createProductWithCategoryAndManufacturer() throws IOException, InterruptedException
	{
		//Read all required data
		String PRODUCTNAME = eUtil.readDataFromExcelFile("Products", 7, 2)+jUtil.getRandomNumber();
		String CATEGORY = eUtil.readDataFromExcelFile("Products", 7, 3);
		String MANUFACTURER = eUtil.readDataFromExcelFile("Products", 7, 4);
		
		//click on Products tab
		HomePage hp = new HomePage(driver);
		hp.clickOnProductsTab();
		
		//click on create new product icon
		ProductsPage pp = new ProductsPage(driver);
		pp.clickOnCreateProdImg();
		
		//create new product with product category and manufacturer
		CreateNewProductPage cnp = new CreateNewProductPage(driver);
		cnp.createNewProduct(PRODUCTNAME, CATEGORY, MANUFACTURER);
		
		//validation
		ProductInformationPage pip = new ProductInformationPage(driver);
		String productHeader = pip.getProductHeaderText();
		if(productHeader.contains(PRODUCTNAME))
		{
			System.out.println("Product "+PRODUCTNAME+ " created successfully");
		}
		else
		{
			System.out.println("Product not created");
		}
		
		
	}

}
