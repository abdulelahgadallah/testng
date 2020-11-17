import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RWExcelData {
	
	 public void readExcel(String filePath,String fileName,String sheetName) throws IOException{
		    //Create an object of File class to open xlsx file
		    File file =    new File(filePath+"\\"+fileName);
		    //Create an object of FileInputStream class to read excel file
		    FileInputStream inputStream = new FileInputStream(file);
		    Workbook clientData = null;
		    //Find the file extension by splitting file name in substring  and getting only extension name
		    String fileExtensionName = fileName.substring(fileName.indexOf("."));
		    //Check condition if the file is xlsx file
		    if(fileExtensionName.equals(".xlsx")){
		    //If it is xlsx file then create object of XSSFWorkbook class
		    	clientData = new XSSFWorkbook(inputStream);
		    }
		    //Check condition if the file is xls file
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
		        //Create a loop to print cell values in a row
		        for (int j = 0; j < row.getLastCellNum(); j++) {
		            //Print Excel data in console
		        	DataFormatter formatter = new DataFormatter();
		            System.out.print(formatter.formatCellValue(row.getCell(j))+"|| ");
		        }
		        System.out.println();
		    } 
	 }  
	 //write data to excel function
	 public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{
	        //Create an object of File class to open xlsx file
	        File file =    new File(filePath+"\\"+fileName);
	        //Create an object of FileInputStream class to read excel file
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook clientData = null;
	        //Find the file extension by splitting  file name in substring and getting only extension name
	        String fileExtensionName = fileName.substring(fileName.indexOf("."));
	        //Check condition if the file is xlsx file
	        if(fileExtensionName.equals(".xlsx")){
	        //If it is xlsx file then create object of XSSFWorkbook class
	        	clientData = new XSSFWorkbook(inputStream);
	        }
	        //Check condition if the file is xls file
	        else if(fileExtensionName.equals(".xls")){
	            //If it is xls file then create object of XSSFWorkbook class
	        	clientData = new HSSFWorkbook(inputStream);
	        }    
	    //Read excel sheet by sheet name    
	    Sheet sheet = clientData.getSheet(sheetName);
	    //Get the current count of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Get the first row from the sheet
	  /*  Row row = sheet.getRow(0);
	    //Create a new row and append it at last of sheet
	    Row newRow = sheet.createRow(rowCount+1);
	    //Create a loop over the cell of newly created Row
	    for(int j = 0; j < row.getLastCellNum(); j++){

	        //Fill data in row
	        Cell cell = newRow.createCell(j);
	        cell.setCellValue(dataToWrite[j]);
	    }*/
	    for (int i = 1; i < rowCount+1; i++) {
	    	Cell cell = null;
	        Row row = sheet.getRow(i);
	        //Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            //Print Excel data in console
	        	cell = sheet.getRow(i).getCell(2);
	    		cell.setCellValue("Passed");
	        }
	        System.out.println();
	    } 
	    //Close input stream
	    inputStream.close();
	    //Create an object of FileOutputStream class to create write data in excel file
	    FileOutputStream outputStream = new FileOutputStream(file);
	    //write data in the excel file
	    clientData.write(outputStream);
	    //close output stream
	    outputStream.close();
		
	    }
		    //Main function is calling readExcel function to read data from excel file

		    public static void main(String...strings) throws IOException{

		    //Create an object of ReadGuru99ExcelFile class

		    	RWExcelData objExcelFile = new RWExcelData();

		    //Prepare the path of excel file	
		    String filePath = "D:\\Added Libraries";
		    //Call read file method of the class to read data
		    objExcelFile.readExcel(filePath,"clients.xlsx","ClientsInfo");
		    //Call read file method of the class to read data
		   // String[] valueToWrite = {"Mr. E","Noida"};
		   // objExcelFile.writeExcel(filePath,"clients.xlsx","ClientsInfo",valueToWrite);

		    }
}