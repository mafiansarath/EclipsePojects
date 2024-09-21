package com.bis.testexceution;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bis.listners.ListnersImp;
import com.bis.testbase.TestBaseClass;
import com.bis.testlocators.TestLoginLocators;

public class TestLoginExceution {
	@BeforeSuite (groups = {"smoke"})
	public void trriggerReporter() {
		System.out.println("Console Start recording all action- reporting start");
		
	}
	@AfterSuite (groups = {"smoke"})
	public void flushReporter() {
		System.out.println("Console Stop recorded all action- reporting start");
		
	}
	
	@BeforeTest (groups = {"smoke"})
	public void startTime(){
		System.out.println("Test Starts @"+LocalDateTime.now());
	}
	
	@AfterTest (groups = {"smoke"})
	public void endTIme() {
		System.out.println("Test Ends @"+LocalDateTime.now());
	}
	
	@Parameters("browser")
	@BeforeMethod (groups = {"smoke"})
	public void launch(String browser) throws IOException {
		TestBaseClass.browserLauch(browser);
		TestBaseClass.loadUrl(TestBaseClass.getProperty("siteurl"));
	}
	
	
	@AfterMethod 
	public void quit() throws InterruptedException {
		TestBaseClass.closeBrowser();

	}
	
	 @DataProvider(name = "data",parallel =false)
	    public static Object[][] data() throws IOException {

	        File file = new File("C:\\Users\\G O D\\eclipse-workspace\\TestNG\\src\\test\\resources\\Data\\Credentails.xlsx");
	        FileInputStream fis = new FileInputStream(file);
	        Workbook wk = new XSSFWorkbook(fis);
	        Sheet sheet = wk.getSheet("sheet1");
	        int rowscount = sheet.getPhysicalNumberOfRows();
	        int coloumncount = sheet.getRow(0).getPhysicalNumberOfCells();

	        Object[][] data = new Object[rowscount - 1][coloumncount];

	        for (int i = 1; i < rowscount; i++) {
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < coloumncount; j++) {  // Corrected loop variable
	                Cell cell = row.getCell(j);
	                data[i - 1][j] = getCellValue(cell);
	            }
	        }

	        // Close the workbook and file input stream outside the loop
	        wk.close();
	        fis.close();

	        return data;
	    }

	    private static Object getCellValue(Cell cell) {
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue();
	                } else {
	                    return cell.getNumericCellValue();
	                }
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return null;
	        }
	    }
//	Test:1 defaultLogin
	
	@Test 
	public void defaultLogin() throws IOException {
		TestLoginLocators t = new TestLoginLocators();
		t.username.sendKeys(TestBaseClass.getProperty("username"));
		t.password.sendKeys(TestBaseClass.getProperty("password"));
		t.login.click();

//		Validation
		Assert.assertTrue(t.msg.isDisplayed());
		}
	
//	Test:2 loginTestFunctionality
	
	@Test(dataProvider = "data" ,groups = {"smoke"},retryAnalyzer = ListnersImp.class)
	public void loginTestFunctionality(String user, String pass) throws IOException {
		TestLoginLocators t = new TestLoginLocators();
		t.username.sendKeys(user);
		t.password.sendKeys(pass);
		t.login.click();
		
//		Validation
		Assert.assertTrue(t.msg.isDisplayed());
		}
	

	
//	Test:3 messageFunctionality
@Test 
public void messageFunctionality() throws IOException {
	TestLoginLocators t = new TestLoginLocators();
	t.defaultLogin();
	t.msg.click();
	
//	Validation
	Assert.assertTrue(t.newmsg.isDisplayed());
	}

@Test(groups = {"smoke"})
public void printFunctionality() {
	System.out.println("Hi how are you");
	
}
	
	


}
