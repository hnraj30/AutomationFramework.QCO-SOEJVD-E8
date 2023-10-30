package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		//Step1: Open the document in Java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		//Step2: Create a Workbook
		Workbook wb = WorkbookFactory.create(fis);
		//Step3: Navigate to required sheet
		Sheet sh = wb.getSheet("Contacts");
		//Step4: Navigate to required row
		Row rw = sh.getRow(1);
		//Step5: Navigate to required cell
		Cell cl = rw.getCell(2);
		//Step6: Capture the value and print
		String value = cl.getStringCellValue();
		System.out.println(value);
		
	}

}
