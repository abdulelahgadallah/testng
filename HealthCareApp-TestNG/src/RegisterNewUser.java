import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
@FixMethodOrder(MethodSorters.DEFAULT)
public class RegisterNewUser {
	Random rand = new Random();
	String loginEmail=null,loginPassword=null;
	WebDriver driver;
	@Before
	public void loadWebsiet(){
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}
	@Test
	public void registerUser(){
		
	    driver.get("http://localhost/healthcare/");
	    //click register link
		WebElement registerlink = driver.findElement(By.xpath("//a[@id='registerUser']"));
		WebElement registerLabel = null,supName=null,supMobile=null,supEmail=null, supPassword=null,
				   patName=null, healthStatus=null;
		if(registerlink.isDisplayed()){
			System.out.println("register page found successfully");
			registerlink.click();
		    registerLabel= driver.findElement(By.name("regisertLabel"));
		}
		else{
			System.out.println("register page not found !!");
		}
		//check the register title is displayed
		assertTrue(registerLabel.getText().equals("REGISTER WITH US"));
		// fill registration form
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		supName=driver.findElement(By.name("sup_name"));
		supName.sendKeys(generateData("name"));	
		
		supMobile=driver.findElement(By.name("mobile"));
		supMobile.sendKeys(generateData("mobile"));
	
		supEmail=driver.findElement(By.name("email"));	
		supEmail.sendKeys(generateData("email"));
		
		supPassword=driver.findElement(By.name("password"));
		supPassword.sendKeys(generateData("password"));
		
		patName=driver.findElement(By.cssSelector("input[name=patient_name]"));
		patName.sendKeys(generateData("name"));
		
		Select patSelect = new Select(driver.findElement(By.name("patient_gender")));
		patSelect.selectByIndex(1);
		
		Select patBirth = new Select(driver.findElement(By.name("birth_date")));
		patBirth.selectByIndex(rand.nextInt(105));
		
		healthStatus=driver.findElement(By.id("patient_status"));
		healthStatus.sendKeys(generateData("text"));
		
		healthStatus=driver.findElement(By.id("submit"));
		healthStatus.submit();
		
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	}
	
	public  String generateData(String textType){
		if(textType=="name"){
			String fullName="";
			List listSource = new List();
			listSource.add("Ahmed");
			listSource.add("Hassan");
			listSource.add("Ali");
			listSource.add("Kalid");
			listSource.add("Malik");
			for(int i=0; i<2;i++){
			int item = rand.nextInt(5);
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
		    loginEmail = randName+"@gmail.com";
				return randName+"@gmail.com";
		}
		else if(textType=="password"){
		    int length = 5;
		    boolean useLetters = false;
		    boolean useNumbers = true;
		    String randPass = RandomStringUtils.random(length, useLetters, useNumbers);
		    loginPassword= randPass;
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
	@After
	public void endTest(){
		//close the driver
		driver.close();
	}
	
}
