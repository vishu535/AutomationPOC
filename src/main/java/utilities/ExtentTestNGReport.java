package utilities;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.utils.DateTimeUtil;

public class ExtentTestNGReport extends DateTimeUtil{

	public static WebDriver driver;
	public static WebElement element;
	public static ExtentReports reporter;
	ExtentTest logger; 

	public static ExtentReports Instance()
	{
		try{

		if (reporter == null) {
			// you can get the file name and other parameters here from a
			// config file or global variables
			String Path = System.getProperty("user.dir") + "/Reports/Producer_Pro.html";
			reporter = new ExtentReports(Path, false, DisplayOrder.OLDEST_FIRST);
			//reporter.loadConfig(new File(System.getProperty("user.dir") +"/calliduscloudlogo/extent-config.xml"));
		}
	
		}catch(Exception e){
			
		}
		return reporter;
	}
	public static String CaptureScreen(WebDriver driver, String ImagesPath)
	{
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath+".jpg");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {System.out.println(e.getMessage());}
		return ImagesPath+".jpg";
	}

	public void Verify_Save(String entityName,boolean b,ITestResult result)
	{
		if(b)
		{
			logger.log(LogStatus.PASS, "Created"+entityName);
		}
		else
		{
			ExtentTestNGReport extentReport=new ExtentTestNGReport();
			extentReport.TakeScreenShot(result);
		}
	}

	public void TakeScreenShot(ITestResult result)
	{

	/*	String screenshot_path=seleniumCommands.captureScreenshot(driver,System.getProperty("user.dir")+"\\Reports\\"+result.getName());
		String image= logger.addScreenCapture(screenshot_path);
		logger.log(LogStatus.FAIL, "Create Producer", image);
	*/}

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if(files!=null) { //some JVMs return null for empty dirs
			for(File f: files) {
				if(f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}

	}

}







