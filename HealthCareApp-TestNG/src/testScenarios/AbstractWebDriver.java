package testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.HomePage;

public class AbstractWebDriver {
	
	WebDriver driver,webdriver;
	String driverType= "chrome";
	protected HomePage onHomePage;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite(){
		
		extent = new ExtentReports(System.getProperty("user.dir") +"\\test-output\\extent.html");	
	}
	
	@BeforeMethod
	public void loadWebsite(){
	
		if(driverType.equalsIgnoreCase("chrome")){
  	System.setProperty("webdriver.chrome.driver", "D:\\Added Libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if(driverType.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\Added Libraries\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(driverType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", "D:\\Added Libraries\\IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			
			driver = new InternetExplorerDriver(options);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		else if(driverType.equalsIgnoreCase("hedless")){
			driver = new HtmlUnitDriver();
		}
	    driver.manage().window().maximize();
	    onHomePage =new HomePage(driver);
	    // extent report		
	}
	
	@AfterMethod
	public void endTest(ITestResult result) throws IOException{
		//close the driver
		if(result.getStatus()==ITestResult.SUCCESS){
			logger.log(LogStatus.PASS,"Test passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL,logger.addScreenCapture(capture(driver)),"Test failed");
			
		}

		driver.close();
		extent.flush();
	}
	
	public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite, int rowId) 
			 throws IOException{
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
	    Cell cell = null;
	    cell = sheet.getRow(rowId).getCell(7,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	    cell.setCellValue(dataToWrite[0]);
	    //Close input stream
	    inputStream.close();
	    //Create an object of FileOutputStream class to create write data in excel file
	    FileOutputStream outputStream = new FileOutputStream(file);
	    //write data in the excel file
	    clientData.write(outputStream);
	    //close output stream
	    outputStream.close();
	 }
  
  public void takeScreenshot(String name) throws IOException{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File("D:\\Added Libraries\\failed TCs\\"+name+".png"));
	}
  
  public static String capture(WebDriver driver) throws IOException {
	  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
	  + ".png");
	  String errflpath = Dest.getAbsolutePath();
	  FileUtils.copyFile(scrFile, Dest);
	  return errflpath;
	  }
}
