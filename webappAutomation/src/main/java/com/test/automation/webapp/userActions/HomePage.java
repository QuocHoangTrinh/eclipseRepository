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
public class HomePage extends TestBase {
	
	WebDriver driver;
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
		
	@FindBy(xpath="//*[@class='login']")
	WebElement signIn;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement loginEmailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement loginPassword;
	
	@FindBy(id="SubmitLogin")
	WebElement submitButton;

	@FindBy(xpath="//*[@class='logout']")
	WebElement signOut;
		
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public MyAccountPage loginToApplication(String emailAddress, String password){
		signIn.click();
		log.info("clicked on sign in and object is "+signIn.toString());
		loginEmailAddress.sendKeys(emailAddress);
		log.info("entered email address "+emailAddress+" and object is "+loginEmailAddress.toString());
		loginPassword.sendKeys(password);
		log.info("entered password "+password+" and object is "+loginPassword.toString());
		submitButton.click();
		log.info("clicked on submit button and object is "+submitButton.toString());
		return new MyAccountPage(driver);
	}
	
	public void clickOnSignOut() {
		signOut.click();
		log.info("clicked on sign out and object is "+signOut.toString());
	}
}
