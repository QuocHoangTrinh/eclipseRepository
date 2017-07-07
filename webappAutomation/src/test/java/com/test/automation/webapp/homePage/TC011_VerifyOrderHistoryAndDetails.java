/**
 * 
 */
package com.test.automation.webapp.homePage;

import org.testng.annotations.Test;

import com.test.automation.webapp.testBase.TestBase;
import com.test.automation.webapp.userActions.HomePage;
import com.test.automation.webapp.userActions.MyAccountPage;
import com.test.automation.webapp.userActions.MyAccount_OrderHistoryPage;
import com.test.automation.webapp.userActions.TestFlows;

import org.testng.Assert;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class TC011_VerifyOrderHistoryAndDetails extends TestBase {
	
	public TestFlows testFlows;
	public HomePage homepage;
	public MyAccountPage myAccountPage;
	public MyAccount_OrderHistoryPage myAccount_OrderHistoryPage;
	
	@BeforeTest
	public void setUp() throws IOException {
		init();
	}


	@Test
	public void verifyOrderHistoryAndDetails() {
		log.info("********** Starting verifyOrderHistoryAndDetails Test **********");
		homepage = new HomePage(driver);
		testFlows = new TestFlows(driver);
		String emailAddess = properties.getProperty("login.email.address");
		String password = properties.getProperty("login.password");
		
		myAccountPage = testFlows.flowToMyAccountPage(homepage,emailAddess, password);
		myAccountPage.clickOnAccountLink(myAccountPage.orderHistoryAndDetails);
		
		getScreenshot("clickOnAccountLink_OrderHistoryAndDetails");
		myAccount_OrderHistoryPage = new MyAccount_OrderHistoryPage(driver);
		Assert.assertEquals(myAccount_OrderHistoryPage.getInfoTitle(), "Here are the orders you've placed since your account was created.","Forwarding to order histoty failed!");
		userLog("success forwarding to order history and info title is displayed: "+myAccount_OrderHistoryPage.getInfoTitle());

		homepage.clickOnSignOut();
		log.info("********** Finished verifyOrderHistoryAndDetails Test **********");
	}
	
	@AfterTest
	public void endTest() {
		closeBrowser();
	}
	
}
