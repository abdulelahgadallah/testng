package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
	protected WebDriver driver; 
	
	public AbstractPage(WebDriver driver){
		this.driver=driver;
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	public HomePage navigateToWebApp(){
		driver.get("http://localhost/healthcare/");
		return new HomePage(driver);
	}
	
	public Boolean isDisplayed(By locator){
		try{
			return  driver.findElement(locator).isDisplayed();
		} catch(org.openqa.selenium.NoSuchElementException ex){
			
			return false;
		}
		
	}

}
