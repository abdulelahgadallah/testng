import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class SnapshotResult {
	WebDriver driver;
	@Test
	public void testScreenshot() throws IOException{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost/healthcare/");
		driver.manage().window().maximize();
		takeScreenshot("home");
		driver.close();
		
	}
	public void takeScreenshot(String name) throws IOException{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File("D:\\Added Libraries\\"+name+".png"));
	}

}
