package com.bis.testlocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RoughClass {
	public static void main(String[] args) {
		
	WebDriver d=new ChromeDriver();
	d.get("https://www.amazon.in/");
	WebElement search = d.findElement(By.id("twotabsearchtextbox"));
	search.sendKeys("hi");
	}	

}
