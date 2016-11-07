package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserSetup 
{

	/*
	 * Browser Types: Firefox, IE9, IE10, IE11, Chrome (Drivers for these
	 * browsers are machine specific and path to them can be set in this file.)
	 */
	public enum BrowserType 
	{
		FIREFOX, IE9, IE10, IE11, CHROME, PHANTOM
	}

	private static String chromeDriverLocation = TestPropertiesReader.getChromeDriverLocation();
	private static String remoteMachineURL = TestPropertiesReader.getRemoteMachineURL();

	/**
	 * Gets the browser from the configuration file
	 * 
	 * @return
	 */
	private static BrowserType getBrowser() 
	{
		BrowserType browserType = null;
		browserType = BrowserType.valueOf(TestPropertiesReader
				.getBrowserTypeProperty());
		//browserType = BrowserType.valueOf("PHANTOM");
		return browserType;
	}

	/**
	 * Chrome Driver: Get the chrome driver with desired capabilities.
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	protected static WebDriver getChromeDriver() throws MalformedURLException 
	{
		System.out.println("Entered in to Chrome Driver"+chromeDriverLocation);
		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);	
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
	//	chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities capabilities=DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
	//	capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

//		WebDriver driver = new RemoteWebDriver(new URL(remoteMachineURL),capabilities);
		WebDriver driver = new ChromeDriver();
		String url=TestPropertiesReader.getTestEnvironmentPath();
		System.out.println("Url is"+url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * Firefox Driver (Set all the Firefox capabilities here for Firefox)
	 * 
	 * @return
	 * @throws MalformedURLException
	 **/
	protected static WebDriver getFirefoxDriver() throws MalformedURLException 
	{
		FirefoxProfile profile = new FirefoxProfile();
		// Capabilities to set the firefox download preferences
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting",
				false);
		profile.setPreference(
				"browser.helperApps.neverAsk.openFile",
				"application/octet-stream, text/html, application/xhtml+xml, application/xml, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		profile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream, text/html, application/xhtml+xml, application/xml, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
		desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		WebDriver driver = new RemoteWebDriver(new URL(remoteMachineURL),desiredCapabilities);
		String url=TestPropertiesReader.getTestEnvironmentPath();
		driver.get(url);
		System.out.println("Url is"+url);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * IE Driver (Set all the capabilities here for IE)
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
/*
	protected static WebDriver getIEDriver() throws MalformedURLException
	{
		System.setProperty("webdriver.ie.driver", ieDriverLocation);

		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		ieCapabilities.setCapability("ensureCleanSession", true);
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setCapability("enablePersistentHover", false);
		WebDriver driver = new RemoteWebDriver(new URL(remoteMachineURL),ieCapabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		return driver;
	}
*/
	/**
	 * Gets the type of webdriver from the enum list based on the configuration
	 * file
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static WebDriver getWebDriver() throws MalformedURLException 
	{

		//FileUtils.cleanDirectory("C:\\Users\\sdhava\\git\\producer-management\\producer_management_ui_automation\\Reports"); 
		WebDriver driver;
		BrowserType browserType = BrowserSetup.getBrowser();
		// TODO kill browser threads -- before getting a new one

		switch (browserType)
		{
		case FIREFOX:
			driver = getFirefoxDriver();
			break;
		case CHROME:
			driver = getChromeDriver();
			break;
		default:
			driver = getFirefoxDriver();
			break;
		}
		return driver;
	}
}
