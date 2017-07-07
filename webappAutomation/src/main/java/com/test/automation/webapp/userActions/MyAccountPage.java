/**
 * 
 */
package com.test.automation.webapp.userActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.webapp.testBase.TestBase;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class MyAccountPage extends TestBase {
	
	WebDriver driver;
	public static final Logger log = Logger.getLogger(MyAccountPage.class.getName());
	
	public final String orderHistoryAndDetails = "Order history and details";
	public final String myCreditSlips = "My credit slips";
	public final String myAddresses = "My addresses";	
	public final String myPersonalInformation = "My personal information";
	public final String myWishlists = "My wishlists";


	@FindBy(xpath="//*[@class='info-account']")
	WebElement infoAccount;

	@FindBy(xpath="//*[@id='center_column']/div/div/ul/li/a")
	List<WebElement> myaccountLinks;
		
	public MyAccountPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getInfoAccountText() {
		return infoAccount.getText().trim();
	}

	public void clickOnAccountLink(String linkName) {
		driver.findElement(By.xpath(".//*[@id='center_column']/div/div/ul/li/a/span[contains(text(), '"+linkName+"')]")).click();
		log.info("click on account link named: "+linkName);
	}
	
	public List<WebElement> getAccountLinkList() {
		List<WebElement> list = myaccountLinks;
		return list;
	}
	
	
	
}
