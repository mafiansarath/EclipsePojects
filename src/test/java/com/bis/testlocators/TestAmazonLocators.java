package com.bis.testlocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bis.testbase.TestBaseClass;

public class TestAmazonLocators extends TestBaseClass {
	
	public  TestAmazonLocators() {
	PageFactory.initElements(driver, this );	
	}

	@FindBy (id="twotabsearchtextbox")
	public WebElement search;
	
}
