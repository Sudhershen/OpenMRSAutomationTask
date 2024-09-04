package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static WebDriver chromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	// public static WebDriver browserLaunch(String bname) {
	//
	// if(bname.equalsIgnoreCase("chrome")) {
	// WebDriverManager.chromedriver().setup();
	// driver=new ChromeDriver();
	// }
	// else if(bname.equalsIgnoreCase("edge")) {
	// WebDriverManager.edgedriver().setup();
	// driver=new EdgeDriver();
	// }
	// return driver;
	//
	// }

	public static WebDriver browserLaunch(String bname) {

		switch (bname) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
		}
		return driver;

	}

	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicitwait(int secs) {
		driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
	}

	public static void sendKeys(WebElement element, String value) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(element));
		try {
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			jsSendKeys(element, value);
		}

	}

	public static void click(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.elementToBeClickable(element));
		try {
			element.click();
		} catch (Exception e) {
			jsClick(element);
		}

	}
	public static void jsSendKeys(WebElement element,String input) {
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+input+"')", element);
	}
	
	public static void jsClick(WebElement element) {
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);

	}
	public static void scrollDown(WebElement element) {
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
	public static String currentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static void quit() {
		driver.quit();
	}

	public static String getText(WebElement e) {
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(e));
		String text = e.getText();
		return text;

	}

	public static String getAttribute(WebElement e, String name) {
		String attribute = e.getAttribute(name);
		return attribute;
	}

	public static void moveToElement(WebElement e) {
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();
	}

	public static void draganddrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();

	}

	public static void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}
	public static void selectByText(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByVisibleText(value);
	}

	public static void accept() {
		Alert a = driver.switchTo().alert();
		a.accept();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void frames(int index) {
		driver.switchTo().frame(index);

	}

	public static void frames(String id) {
		driver.switchTo().frame(id);
	}

	public static void getScreenshot(String name) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir") + "//src//test//resources//Screenshot//" + name + ".png");
		FileUtils.copyFile(src, des);

	}

	public static String getValueFromPropertyFile(String key) {
		Properties p = new Properties();
		try {
			p.load(new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\TestDatas\\data.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.get(key).toString();

	}



	// public static WebElement txtUsername(String id) {
	// WebElement txtUsername = driver.findElement(By.id(id));
	// return txtUsername;
	// }

	// public static String getExcel(String excelname,String sheet,int rowno,int
	// cellno) throws IOException {
	// File f = new File(
	// "C:\\Users\\abdul\\eclipse-workspace\\MavenProjectJan6.30PmBatch24\\src\\test\\resources\\"+excelname+".xlsx");
	//
	// FileInputStream fi = new FileInputStream(f);
	//
	// Workbook w = new XSSFWorkbook(fi);
	//
	// Sheet s = w.getSheet(sheet);
	//
	// Row r = s.getRow(rowno);
	//
	// Cell c = r.getCell(cellno);
	//
	// int cellType = c.getCellType();
	//
	// String value=null;
	// if (cellType == 1) {
	// value = c.getStringCellValue();
	// } else if (DateUtil.isCellDateFormatted(c)) {
	// Date d = c.getDateCellValue();
	// SimpleDateFormat sd = new SimpleDateFormat("DD-MM-YYYY");
	// value = sd.format(d);
	// } else {
	// double d1 = c.getNumericCellValue();
	// long l = (long) d1;
	// value = String.valueOf(l);
	// }
	// return value;
	//
	// }

}
