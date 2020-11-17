package testScenarios;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class ValidationTestCases extends AbstractWebDriver {
	@Test
	public void registerInvalidUser() throws IOException{
		
		HomePage onHomePage =new HomePage(driver);
		onHomePage = onHomePage.navigateToWebApp();		
		RegisterPage onRegisterPage=  onHomePage.clickRegister();
		// fill registration form
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// read excel sheet 
		String filePath = "D:\\Added Libraries";
		//ValidationTestCases objExcelFile = new ValidationTestCases();
	    //objExcelFile.readExcel(filePath,"clients.xlsx","ClientsInfo");
	    String fileName= "clients.xlsx", sheetName= "ClientsInfo";
	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook clientData = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	clientData = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls")){
	        //If it is xls file then create object of HSSFWorkbook class
	    	clientData = new HSSFWorkbook(inputStream);
	    }
	    //Read sheet inside the workbook by its name
	    Sheet client = clientData.getSheet(sheetName);
	    //Find number of rows in excel file
	    int rowCount = client.getLastRowNum()-client.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 1; i < rowCount+1; i++) {
	        Row row = client.getRow(i);
	        String[] params = new String[8];
	       // System.out.println(row.getLastCellNum());
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	DataFormatter formatter = new DataFormatter();
	            //System.out.print(formatter.formatCellValue(row.getCell(j))+"|| ");
	            String val = formatter.formatCellValue(row.getCell(j));
	            params[j]=val.toString();
	        }
	        onRegisterPage = onRegisterPage.fillForm(params[0],params[1],params[2],params[3],params[4],params[5]);
	        onRegisterPage = onRegisterPage.submitInvalidForm();
			String failRegister =onRegisterPage.confirmInvalidRegistration();
			//assertTrue(failRegister.contains(params[6]));
			if(failRegister.contains(params[6])){
			String[] valueToWrite = {"Passed"};
			writeExcel(filePath,fileName,sheetName,valueToWrite, i);
			}
			else {
				String[] valueToWrite = {"Failed"};
				writeExcel(filePath,fileName,sheetName,valueToWrite, i);
				takeScreenshot("TC#"+(i));
			}
	    }
	}
	
	@Test
	public void InvalidLoginTest(){
		
		onHomePage= onHomePage.navigateToWebApp();
		LoginPage onLoginPage= onHomePage.clickLogin();
	    onLoginPage = onLoginPage.userLogin("a@yahoo.com", "1233");
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    String invalidLogin = onLoginPage.submitInvalidLoginInfo();
	    assertThat(invalidLogin,is(notNullValue()));	
		
	}

}
