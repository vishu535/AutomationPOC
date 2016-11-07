package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {
	WebDriver driver;
	ExtentTest logger;
	
	By WelcomeSignup = By.cssSelector("#welcomeSignup #signUp");
	
	public HomePage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSignUp(){
		driver.findElement(WelcomeSignup).click();
		logger.log(LogStatus.INFO, "Clicked Welcome Signup");
		
	}
	
	

}
