/**
 * 
 */
package com.test.automation.webapp.customerListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.automation.webapp.testBase.TestBase;

/**
 * @author Quoc-Hoang Trinh
 *
 */
public class CustomerListener extends TestBase implements ITestListener {
	
	public void onFinish(ITestContext result) {
		Reporter.log("Test is finished: "+ result.getName());
	}

	public void onStart(ITestContext result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
		if( !(result.isSuccess()) ) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");			
			String methodName = result.getName();			
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDir = new File(System.getProperty("user.dir")).getAbsolutePath() + "/test-output/";
				File destFile = new File((String) reportDir + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(sourceFile, destFile);
				Reporter.log("***** Test failed: "+ result.getMethod().getMethodName() +"\n");
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='150' width='200'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test is skipped: "+ result.getName());
	}

	public void onTestStart(ITestResult result) {
		Reporter.log("***** Test starts running ...: "+ result.getMethod().getMethodName() + "\n");
	}

	public void onTestSuccess(ITestResult result) {
		if( (result.isSuccess()) ) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");			
			String methodName = result.getName();			
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDir = new File(System.getProperty("user.dir")).getAbsolutePath() + "/test-output/";
				File destFile = new File((String) reportDir + "/success_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");				
				FileUtils.copyFile(sourceFile, destFile);
				Reporter.log("***** Test is finished successfully: "+ result.getMethod().getMethodName() +"\n");
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='150' width='200'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
	}

}
