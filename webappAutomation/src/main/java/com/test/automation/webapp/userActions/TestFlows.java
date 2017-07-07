/**
 * 
 */
package com.test.automation.webapp.userActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.automation.webapp.testBase.TestBase;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class TestFlows extends TestBase {
		
	WebDriver driver;
	public static final Logger log = Logger.getLogger(TestFlows.class.getName());
	
	public MyAccountPage myAccountPage;
			
	public TestFlows(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public MyAccountPage flowToMyAccountPage(HomePage homepage, String emailAddress, String password) {
		System.out.println("homepage: "+homepage);
		myAccountPage = homepage.loginToApplication(emailAddress, password);
		getScreenshot("loginToApplication_MyAccountPage");
		Assert.assertEquals(myAccountPage.getInfoAccountText(), "Welcome to your account. Here you can manage all of your personal information and orders.");
		userLog("success login and info-account is displayed: "+myAccountPage.getInfoAccountText());
		Assert.assertEquals(myAccountPage.getAccountLinkList().size(), 5);
		userLog("number of account links equals 5");
		return new MyAccountPage(driver);
	}

}
