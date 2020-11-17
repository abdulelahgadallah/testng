import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
   	   // open the site
	    driver.manage().window().maximize();
	    driver.get("http://localhost/healthcare/");
	    // click about link
		WebElement aboutLink= driver.findElement(By.id("about_hlth"));
		aboutLink.click();
		//check the title is 'Patient Health Care'
		String title= driver.getTitle();
		if (title.equals("Patient Health Care")){
			System.out.println("Success");
			}
		//close the driver
		driver.close();
		
		
	}

}
