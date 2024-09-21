package com.bis.testlocators;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bis.testbase.TestBaseClass;

public class TestLoginLocators extends TestBaseClass {
	public TestLoginLocators() {
		PageFactory.initElements(driver, this);

	}

	public void defaultLogin() throws IOException {
//		TestBaseClass.browserLauch("edge");
//		TestBaseClass.loadUrl("https://www.instagram.com/");
		TestLoginLocators t = new TestLoginLocators();
		t.username.sendKeys(TestBaseClass.getProperty("username"));
		t.password.sendKeys(TestBaseClass.getProperty("password"));
		t.login.click();
//		t.msg.click();
//		t.notify.click();

	}
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//div[text()='Log in']")
	public WebElement login;
	
	@FindBy(xpath="//span[contains(text(),'Messages')]")
	public WebElement msg;

	@FindBy(xpath="//div[@class='x1i10hfl x972fbf xcfux6l x1qhh985 xm0m39n x9f619 xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8 x1hl2dhg xggy1nq x1a2a7pz x6s0dn4 xjbqb8w x1ejq31n xd10rxx x1sy0etr x17r0tee x1ypdohk x78zum5 xl56j7k x1y1aw1k x1sxyh0 xwib8y2 xurb0ha xcdnw81']")
	public WebElement newmsg;

	@FindBy(xpath="//button[text()='Turn On']")
	public WebElement notify;
		
	@FindBy(xpath="")
	public WebElement news;
	
	

}

