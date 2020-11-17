package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage{
	
	By registerLink=By.xpath("//a[@id='registerUser']");
	By loginLink= By.xpath("//a[@id='lgn']");
	By myReportLink= By.xpath("//a[@id='my_report']");
	By aboutUsLink= By.xpath("//a[@id='about_hlth']");
	By user = By.id("user1");
	By aboutUsLabel =By.id("about_label");
	By aboutUSImage =By.tagName("img");
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	public RegisterPage clickRegister(){
		
		driver.findElement(registerLink).click();
		return new RegisterPage(driver);
	}
	
	public LoginPage clickLogin(){
		
		driver.findElement(loginLink).click();
		return new LoginPage(driver);
	}
	
	public ReportPage clickMyReports(){
		
		driver.findElement(myReportLink).click();
		return new ReportPage(driver);
	}
	
	public String checkIsUserLogged(){
		
		return driver.findElement(user).getText();
	}

	public Boolean clickAboutUsSection(){
		driver.findElement(aboutUsLink).click();
		
		if(!driver.findElement(aboutUsLabel).getText().isEmpty()){
			return driver.findElement(aboutUsLabel).isDisplayed();
		}
		return false;
		
	}
}
