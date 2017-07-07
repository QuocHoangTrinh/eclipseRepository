/**
 * 
 */
package com.test.automation.webapp.userActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.webapp.testBase.TestBase;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class MyAccount_OrderHistoryPage extends TestBase {
	
	WebDriver driver;
	public static final Logger log = Logger.getLogger(MyAccount_OrderHistoryPage.class.getName());
	
	@FindBy(xpath="//*[@id='center_column']/p")
	WebElement infoTitle;

	@FindBy(xpath="//*[@id='center_column']/ul/li[1]/a/span")
	WebElement backToYourAccount;

	@FindBy(xpath="//*[@id='center_column']/ul/li[2]/a/span")
	WebElement linkHome;

	public MyAccount_OrderHistoryPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getInfoTitle() {
		return infoTitle.getText().trim();
	}

	public MyAccountPage clickOnLinkBackToYourAccount() {
		backToYourAccount.click();
		log.info("click on link BackToYourAccount and object is: "+backToYourAccount.toString());
		return new MyAccountPage(driver);
	}
	
	public HomePage clickOnLinkHome() {
		linkHome.click();
		log.info("click on linkHome and object is: "+linkHome.toString());
		return new HomePage(driver);
	}
	
	
	
}
