package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelFile 
{

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		//Step1: Create the document in Java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\ResultData.xlsx"); 
		//Step 2: Create a Workbook 
		Workbook wb = WorkbookFactory.create(fis);
		//Step3: Navigate to required sheet
		Sheet sh = wb.getSheet("Data");
		//Step4: Navigate to required row
		Row rw = sh.createRow(1);
		//Step5: Navigate to required cell
		Cell cl = rw.createCell(1);
		cl.setCellValue("Raj---(From test data)");
		//Step1: Create the document in Java readable format
		FileOutputStream fos = new FileOutputStream(".\\\\src\\\\test\\\\resources\\\\ResultData.xlsx");
		wb.write(fos);
		

	}

}
