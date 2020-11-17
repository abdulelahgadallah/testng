package pageObjects;

import java.awt.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends AbstractPage {
	
	By supName = By.name("sup_name");
	By supMobile = By.name("mobile");
	By supEmail = By.name("email");
	By supPassword = By.name("password");
	By patName = By.cssSelector("input[name=patient_name]");
	By patGender = By.name("patient_gender");
	By patientBirth = By.name("birth_date");
	By healthStatus= By.id("patient_status");
	By submitButton = By.id("submit");
	By errorMessage = By.tagName("p");
	Random rand = new Random();
	
	public RegisterPage(WebDriver driver){
		
		super(driver);
	}
	
	public RegisterPage fillRegistrationForm(){
		
		driver.findElement(supName).sendKeys(generateData("name"));
		driver.findElement(supMobile).sendKeys(generateData("mobile"));
		driver.findElement(supEmail).sendKeys(generateData("email"));	
		driver.findElement(By.name("password")).sendKeys(generateData("password"));
		driver.findElement(patName).sendKeys(generateData("name"));
		Select patSelect = new Select(driver.findElement(patGender));
		patSelect.selectByIndex(1);
		Select patBirth = new Select(driver.findElement(patientBirth));
		patBirth.selectByIndex(rand.nextInt(105));
		driver.findElement(healthStatus).sendKeys(generateData("text"));
		return new RegisterPage(driver);
		
	}
	
	public void typeSupervisorName(){
		driver.findElement(supName).sendKeys(generateData("name"));
	}
	
	public void typeEmail(){
		driver.findElement(supEmail).sendKeys(generateData("email"));		
	}
	
	public void typeMobile(){
		driver.findElement(supMobile).sendKeys(generateData("mobile"));			
	}
	
	public void typePassword(){
		driver.findElement(By.name("password")).sendKeys(generateData("password"));
	}
	
	public void typePatientName(){
		driver.findElement(patName).sendKeys(generateData("name"));	
	}
	
	public void typeGender(){
		Select patSelect = new Select(driver.findElement(patGender));
		patSelect.selectByIndex(1);
	}
	
	public void typeBirthDate(){
		Select patBirth = new Select(driver.findElement(patientBirth));
		patBirth.selectByIndex(rand.nextInt(105));
	}
	
	public void typePatientStatus(){
		driver.findElement(healthStatus).sendKeys(generateData("text"));
	}
	
	public LoginPage submitForm(){		
		driver.findElement(submitButton).submit();
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}	
		return new LoginPage(driver);
	}
	// local function to generate the data
	public  String generateData(String textType){
		
		if(textType=="name"){
			String fullName="";
			List listSource = new List();
			listSource.add("Ahmed");
			listSource.add("Hassan");
			listSource.add("Ali");
			listSource.add("Kalid");
			listSource.add("Malik");
			listSource.add("Omer");
			listSource.add("Adam");
			for(int i=0; i<2;i++){
			int item = rand.nextInt(7);
			fullName+=listSource.getItem(item)+ " ";
			}
		return fullName;
		}
		else if(textType=="mobile"){
			String mobile;
			mobile =  "05"+Integer.toString(10000000 +new Random().nextInt(90000000));
			return mobile;
		}
		else if(textType=="email"){

		    int length = 10;
		    boolean useLetters = true;
		    boolean useNumbers = true;
		    String randName = RandomStringUtils.random(length, useLetters, useNumbers);
				return randName+"@gmail.com";
		}
		else if(textType=="password"){
		    int length = 5;
		    boolean useLetters = false;
		    boolean useNumbers = true;
		    String randPass = RandomStringUtils.random(length, useLetters, useNumbers);
				return randPass;
		}
		else if(textType=="text"){
			String text=null;
		    int length = 6;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    for(int i=0; i<10; i++){
		    String randWord = RandomStringUtils.random(length, useLetters, useNumbers);
		    text += randWord + " ";
		    }
				return text;
		}
		return null;
	}
	
	public RegisterPage fillForm(String name, String mobile, String email, String pass, String patient,
			 String status){
		
		driver.findElement(supName).sendKeys(name);
		driver.findElement(supMobile).sendKeys(mobile);
		driver.findElement(supEmail).sendKeys(email);	
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(patName).sendKeys(patient);
		Select patSelect = new Select(driver.findElement(patGender));
		patSelect.selectByIndex(1);
		Select patBirth = new Select(driver.findElement(patientBirth));
		patBirth.selectByIndex(rand.nextInt(105));
		driver.findElement(healthStatus).sendKeys(status);
		return new RegisterPage(driver);
		
	}
	
	public RegisterPage submitInvalidForm(){		
		
		driver.findElement(submitButton).submit();
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}	
		return new RegisterPage(driver);
		
	}
	
	public String confirmInvalidRegistration(){
		return driver.findElement(errorMessage).getText();
	}

}
