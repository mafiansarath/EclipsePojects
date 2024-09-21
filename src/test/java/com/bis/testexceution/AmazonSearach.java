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

import com.bis.testbase.TestBaseClass;
import com.bis.testlocators.TestAmazonLocators;

public class AmazonSearach {
	@BeforeSuite
	public void trriggerReporter() {
		System.out.println("Console Start recording all action- reporting start");

	}

	@AfterSuite
	public void flushReporter() {
		System.out.println("Console Stop recorded all action- reporting start");

	}

	@BeforeTest
	public void startTime() {
		System.out.println("Test Starts @" + LocalDateTime.now());
	}

	@AfterTest
	public void endTIme() {
		System.out.println("Test Ends @" + LocalDateTime.now());
	}

	@BeforeMethod
	public void launch() throws IOException {
		TestBaseClass.browserLauch("edge");
		TestBaseClass.loadUrl(TestBaseClass.getProperty("sitelink"));
	}
	
	@AfterMethod
	public void Quit() {
		TestBaseClass.closeBrowser();
	}

//	Test: 1
	
    
	@Test(dataProvider = "data")
	public void searchFuncationality(String values) {
		TestAmazonLocators t = new TestAmazonLocators();
		t.search.sendKeys(values);
		t.search.submit();

//		Validation
		Assert.assertTrue(true);
	}
	
	@DataProvider(name = "data")
    public static Object[][] data() throws IOException {

        File file = new File("C:\\Users\\G O D\\eclipse-workspace\\TestNG\\src\\test\\resources\\Data\\Credentails.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wk = new XSSFWorkbook(fis);
        Sheet sheet = wk.getSheet("sheet2");
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

}
