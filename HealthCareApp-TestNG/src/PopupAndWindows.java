import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class PopupAndWindows {
	WebDriver driver;
	@Before
	public void laodTest(){
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		 driver = new ChromeDriver();
   	   // open the site
	    driver.manage().window().maximize();
	    driver.get("http://localhost/healthcare/");
	}
	@Test
	public void testPopup(){
		WebElement aboutLink= driver.findElement(By.id("about_hlth"));
		aboutLink.click();
		
		Alert alert= driver.switchTo().alert();
		System.out.println(alert);
	}
	
	@Test
	public void testWindow(){
		String parentWindow= driver.getWindowHandle();
		WebElement aboutLink= driver.findElement(By.id("about_hlth"));
		aboutLink.click();
		
		for(String currentWindow : driver.getWindowHandles())
		{
			driver.switchTo().window(currentWindow);
		}
		System.out.println(driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(parentWindow);
		System.out.println(driver.getCurrentUrl());
	}
	
	@After
	public void closeTest(){
		driver.close();
	}
	

}
