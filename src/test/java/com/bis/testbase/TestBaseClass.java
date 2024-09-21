	package com.bis.testbase;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.time.LocalDateTime;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
		
	public class TestBaseClass {
		public static WebDriver driver;
	
		public static void browserLauch(String browser) {
			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();}
				else if (browser.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
			} else {
				System.out.println("not defined");
			}
			driver.manage().window().maximize();
			LocalDateTime.now();
		}
	
		public static void loadUrl(String url) {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
		public static void closeBrowser() {
			driver.quit();
			LocalDateTime.now();
		}
	
		public static void explictWait() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element-id")));
	
		}
	
		public static WebElement searchElementByXpath(String xpath) {
			return driver.findElement(By.xpath(xpath));
		}
	
		public static String getProperty(String key) throws IOException {
			File file = new File(
					"C:\\Users\\G O D\\eclipse-workspace\\TestNGFramework\\src\\test\\resources\\Data\\Data.properties");
			FileInputStream fis = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fis);
	
			return prop.getProperty(key);
		}
	
		public static void selectValueDropDown(WebElement element, String value) {
			Select select = new Select(element);
			select.selectByValue(value);
		}
	
		public static void elementRightClick(WebElement element) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
			actions.contextClick().perform();
		}
	}
