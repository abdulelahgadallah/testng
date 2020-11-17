package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LoginPage extends AbstractPage {
		
		By userEmail= By.name("username");
		By password = By.name("password");
		By homeLableText =By.tagName("h3");
		By loginButton = By.id("submit");
		By invlaidLoginMessage = By.id("xx");
		
		public LoginPage(WebDriver driver){
			super(driver);
		}
		
		public String confirmRegistration(){
			return driver.findElement(homeLableText).getText();
		}
		
		public LoginPage userLogin(String email, String pass){
			assertThat(isDisplayed(userEmail),is(true));
			driver.findElement(userEmail).sendKeys(email);
			driver.findElement(password).sendKeys(pass);
			return new LoginPage(driver);	
		}
		
		public HomePage submitLoginForm(){		
			
			driver.findElement(loginButton).submit();
			try {
			    Thread.sleep(5000);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}	
			return new HomePage(driver);	
		}
		
		public String submitInvalidLoginInfo(){		
			driver.findElement(loginButton).submit();
			System.out.println(driver.findElement(invlaidLoginMessage).getText());
			return driver.findElement(invlaidLoginMessage).getText();	
		}
		
		
		
}
