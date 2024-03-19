package practice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

public class AssertionPractice2 extends BaseClass
{
	@Test
	public void createContactTest() throws IOException
	{
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 1, 2);
		//click on contacts tab
		HomePage hp =new HomePage(driver);
		hp.clickOnContactsLink();
		
		//click on create new contact image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//create contact with last name
		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.createNewContact(LASTNAME);
		
		//Validation using assertion
		ContactInformationPage cip = new ContactInformationPage(driver);
		String headerText = cip.getContactHeaderText();
		
		Assert.assertTrue(headerText.contains(LASTNAME));
		System.out.println(headerText);
		
		
		
		
		
	}

}
