package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportPage extends AbstractPage{
	
	By reportTable = By.xpath("//table[@id='simple-table']/thead/tr[1]/th[1]");
	By homePageLink= By.className("go-home");
	
	public ReportPage(WebDriver driver){
		super(driver);
	}
	
	public String checkReportIsViewed(){
		
		return driver.findElement(reportTable).getText();
	}
	
	public HomePage returnToHomePage(){
		
		driver.findElement(homePageLink).click();
		return new HomePage(driver);
	}
	
	

}
