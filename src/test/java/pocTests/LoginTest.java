package pocTests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BrowserSetup;
import utilities.ExtentTestNGReport;
import pom.HomePage;
import pom.RegisterDetailsPage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginTest {
	public static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports report;
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		report = ExtentTestNGReport.Instance();
		driver = BrowserSetup.getWebDriver();
	}
	
	@BeforeMethod
	public void loginToApplication(){
	}
	
	@Test
	public void existingCustomerLogin() throws InterruptedException{
		logger = report.startTest("Customer login");
		HomePage homepage = new HomePage(driver, logger);
		}
	
	@AfterMethod
	public void logOut(){
	}
	
	@AfterTest
	public void TearDown(){
		report.endTest(logger);
		report.flush();
		driver.quit();
	}

}
