package productTests;

import java.io.IOException;

import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CreateNewProductPage;
import objectRepository.HomePage;
import objectRepository.ProductInformationPage;
import objectRepository.ProductsPage;

public class CreateNewProductWithCategoryTest extends BaseClass
{
	@Test(groups = "RegressionSuite")
	public void createNewProductWithCategory() throws IOException, InterruptedException
	{
		String PRODUCTNAME = eUtil.readDataFromExcelFile("Products", 4, 2);
		String CATEGORY = eUtil.readDataFromExcelFile("Products", 4, 3);
		HomePage hp = new HomePage(driver);
		hp.clickOnProductsTab();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.clickOnCreateProdImg();
		
		CreateNewProductPage cnp = new CreateNewProductPage(driver);
		cnp.createNewProduct(PRODUCTNAME, CATEGORY);
		
		ProductInformationPage pip = new ProductInformationPage(driver);
		String productHeader = pip.getProductHeaderText();
		
		//Validation
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
