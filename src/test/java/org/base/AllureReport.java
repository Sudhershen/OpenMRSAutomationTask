package org.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AllureReport extends BaseClass {
	public static ExtentReports report;
	public static void startReport() {
		// File location where want to store report
		ExtentHtmlReporter html = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Reports\\ExtentReport\\extent_report.html");
		html.config().setDocumentTitle("Extent Report");
		html.config().setReportName(driver.getTitle());
		html.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("BrowserName", getValueFromPropertyFile("browser"));
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Environment", "QA");
	}
	public static void createLog(ITestResult sc) {
		String name = sc.getName();
		int status = sc.getStatus();
		TakesScreenshot tk= (TakesScreenshot)driver;
		String scr = tk.getScreenshotAs(OutputType.BASE64);
		
		switch (status) {
		case 1:
			report.createTest(name).pass("Test Case Passed").addScreenCaptureFromBase64String(scr);
			break;
		case 2:
			report.createTest(name).fail("Test Case Failed").addScreenCaptureFromBase64String(scr);
			break;

		default:
			report.createTest(name).skip("Test Case Skipped").addScreenCaptureFromBase64String(scr);
			break;
		}	}
	public static void endReport() {
		report.flush();
	}

}
