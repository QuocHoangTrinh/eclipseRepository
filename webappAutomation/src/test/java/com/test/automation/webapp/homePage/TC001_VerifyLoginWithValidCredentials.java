/**
 * 
 */
package com.test.automation.webapp.homePage;

import org.testng.annotations.Test;

import com.test.automation.webapp.testBase.TestBase;
import com.test.automation.webapp.userActions.HomePage;
import com.test.automation.webapp.userActions.MyAccountPage;

import org.testng.Assert;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class TC001_VerifyLoginWithValidCredentials extends TestBase {
	
	public HomePage homepage;
	public MyAccountPage myAccountPage;
	
	@BeforeTest
	public void setUp() throws IOException {
		init();
	}


	@Test
	public void verifyLoginWithValidCredentials() {
		log.info("********** Starting verifyLoginWithValidCredentials Test **********");
		homepage = new HomePage(driver);
		myAccountPage = homepage.loginToApplication(properties.getProperty("login.email.address"), properties.getProperty("login.password"));
		getScreenshot("loginToApplication_MyAccountPage");
		
		Assert.assertEquals(myAccountPage.getInfoAccountText(), "Welcome to your account. Here you can manage all of your personal information and orders.","Login failed!");
		userLog("success login and info-account is displayed: "+myAccountPage.getInfoAccountText());
		Assert.assertEquals(myAccountPage.getAccountLinkList().size(), 5);
		userLog("number of account links equals 5");
		homepage.clickOnSignOut();
		log.info("********** Finished verifyLoginWithValidCredentials Test **********");
	}
	
	@AfterTest
	public void endTest() {
		closeBrowser();
	}
	
}
