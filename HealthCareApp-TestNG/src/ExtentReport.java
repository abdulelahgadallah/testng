

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {
	//ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setUp(){
		//htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\test-output\\extent.html");
		extent = new ExtentReports(System.getProperty("user.dir") +"\\test-output\\extent.html");
		//extent.attachReporter(htmlReporter);
	}

	@Test
	public void test1() throws IOException{   
		test = extent.startTest("MyFirstTest", "Sample description");
		assertTrue("1".equals("1"));
		test.log(LogStatus.INFO, "This step shows usage of log(status, details)");
		//test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") +"\\Reports\\screenshot.png").build());
		//test.addScreenCaptureFromPath(System.getProperty("user.dir") +"\\Reports\\screenshot.png");
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
	}

}
