package testScenarios;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.ReportPage;

public class HappyEndToEndScenariosTest extends AbstractWebDriver{
	// select the browser

	Random rand = new Random();

	@Test(priority=1)
	public void registerUser(){
		logger=extent.startTest("Register New User");
		onHomePage = onHomePage.navigateToWebApp();		
		RegisterPage onRegisterPage=  onHomePage.clickRegister();
		//logger.info("Fill registration form");
		// fill registration form
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		onRegisterPage = onRegisterPage.fillRegistrationForm();
		LoginPage onLoginPage = onRegisterPage.submitForm();
		String successRegister =onLoginPage.confirmRegistration();
		System.out.println(successRegister);
		assertThat(successRegister,is("Login Form"));
	}

	@Test(priority=2)
	public void loginTest(){
		logger=extent.startTest("User login");
		onHomePage= onHomePage.navigateToWebApp();
		LoginPage onLoginPage= onHomePage.clickLogin();
		onLoginPage = onLoginPage.userLogin("a@yahoo.com", "1235");
		HomePage onHomePageAgain = onLoginPage.submitLoginForm();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println(onHomePageAgain.checkIsUserLogged());
		assertThat(onHomePageAgain.checkIsUserLogged(),is(notNullValue()));
	}

	@Test(priority=3)
	public void ViewReportTest(){
		logger=extent.startTest("View report");
		onHomePage= onHomePage.navigateToWebApp();
		LoginPage onLoginPage= onHomePage.clickLogin();
		onLoginPage = onLoginPage.userLogin("a@yahoo.com", "123");
		HomePage onHomePageAgain = onLoginPage.submitLoginForm();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String isLogged = onHomePageAgain.checkIsUserLogged();
		assertThat(isLogged,is(notNullValue()));
		System.out.println(isLogged);
		ReportPage onReportPage = onHomePageAgain.clickMyReports();
		String isCorrectReportPage = onReportPage.checkReportIsViewed();
		System.out.println(isCorrectReportPage);
		assertThat(isCorrectReportPage,is(notNullValue()));
		onHomePageAgain=onReportPage.returnToHomePage();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Test(priority=4)
	public void AboutUsSectionTest(){
		logger=extent.startTest("View about us section");
		onHomePage = onHomePage.navigateToWebApp();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(onHomePage.clickAboutUsSection());
	}

}
