
import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.After;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
@FixMethodOrder(MethodSorters.DEFAULT)
public class AppHomeTest {
	WebDriver driver;
	@Before
	public void loadWebsiet(){
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}
	@Test
	public void checkHomePage(){
		
	    driver.get("http://localhost/healthcare/");
	    // click about link
		WebElement homeLink= driver.findElement(By.id("main_parag"));
		if(homeLink.isDisplayed()){
			System.out.println("Home page found successfully");
		}
		else{
			System.out.println("Home page not found !!");
		}
		//check the title is 'Patient Health Care'
		String title= driver.getTitle();
		assertTrue(title.equals("Patient Health Care"));
	}
	@Test 
	public void checkAboutPage(){
		driver.get("http://localhost/healthcare/");
	    // click about link
		WebElement aboutLink= driver.findElement(By.id("about_hlth"));
		if(aboutLink.isDisplayed()){	
			aboutLink.click();
		}
		else{
			System.out.println("About page not found !!");
		}
		//check the label of about section
		WebElement aboutLabel= driver.findElement(By.id("about_label"));
		if(aboutLabel.isDisplayed()){
			System.out.println("About page found successfully");
		}
		else{
			System.out.println("About page not found !!");
		}
		// assert 
		assertTrue(aboutLabel.getText().equals("ABOUT"));	
	}

	@After
	public void endTest(){
		//close the driver
		driver.close();
	}
	
}
