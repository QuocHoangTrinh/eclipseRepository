/**
 * 
 */
package com.test.automation.webapp.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

/**
 * @author Quoc-Hoang Trinh
 *
 */

public class TestBase {
	
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public Properties properties = new Properties();
	
	public void loadProperties() throws IOException {
		File file = new File(System.getProperty("user.dir")+"/src/main/java/com/test/automation/webapp/config/config.properties");
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
	}
	
	public void selectBrowser(String browser) {
		System.out.println("Operating system is "+System.getProperty("os.name"));
		Map<String,Object> capMap = new HashMap<String,Object>();
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.out.println("Executing on Chrome");
			/*
			 * additional capability can be defined/added here
			 */	
			capMap.put("takesScreenShot", true);
			capMap.put("handlesAlert", true);
			capMap.put("cssSelectorsEnabled", true);
			capMap.put("javascriptEnabled", true);
			capMap.put("acceptSSLCerts", true);
			//capMap.put("webStorageEnabled", true); // this is a HTML5 feature
			capMap.put("browserName", "chrome");
			DesiredCapabilities cap = new DesiredCapabilities(capMap);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver(cap);
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.out.println("Executing on Firefox");
			/*
			 * additional capability can be defined/added here
			 */	
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("insecure_field_warning.contextual.enabled", false);
			profile.setPreference("security.insecure_field_warning.contextual.enabled", false);
			FirefoxOptions options = new FirefoxOptions();
	        options.setLogLevel(Level.ALL);
			driver = new FirefoxDriver(profile);			
		}else if(browser.equalsIgnoreCase("ie")) {
			System.out.println("Executing on InternetExplorer");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			  
			cap.setBrowserName("IE");
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(cap);
		}
	}
	
	public void getUrl(String url) {
		log.info("launching app " + url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("implicitlyWait.launching.app")), TimeUnit.SECONDS);
	}
	
	/*
	 * @call this method by setUp() in test class
	 */
	public void init() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		loadProperties();
		selectBrowser(properties.getProperty("browser"));
		getUrl(properties.getProperty("url"));
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void userLog(String logText) {
		log.info(logText);
		Reporter.log(logText);
	}
	
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	public void getScreenshot(String name) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");		
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDir = new File(System.getProperty("user.dir")).getAbsolutePath() + "/test-output/";
			File destFile = new File((String) reportDir + "/screenshots/" + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(sourceFile, destFile);	
			Reporter.log("getScreenshot_"+name+":\n");
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='150' width='200'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
