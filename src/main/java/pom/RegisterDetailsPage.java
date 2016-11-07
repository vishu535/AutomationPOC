package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegisterDetailsPage {
	WebDriver driver;
	ExtentTest logger;
	
	public RegisterDetailsPage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
	
	public void enterRegistrationDetails() throws InterruptedException{
		Thread.sleep(2000);
		Select select = new Select(driver.findElement(By.cssSelector("#myPrefix")));
		select.selectByVisibleText("Mr.");
		logger.log(LogStatus.INFO, "Prefix set to ");
		driver.findElement(By.name("firstName")).sendKeys("hello");
		logger.log(LogStatus.INFO, "FirstName set to ");
		driver.findElement(By.name("lastName")).sendKeys("first name");
		logger.log(LogStatus.INFO, "LastName set to ");
		driver.findElement(By.name("primaryEmail")).sendKeys("firmanelasname");
		logger.log(LogStatus.INFO, "Primary email set to ");
		driver.findElement(By.name("password")).sendKeys("this is automation test");
		logger.log(LogStatus.INFO, "Password set to ");
		driver.findElement(By.name("passwordConfirm")).sendKeys("this is automation test");
	}
	
	public void signUpNewUser(){
		driver.findElement(By.cssSelector("#registrationForm .mySigninButton")).click();
	}

}
