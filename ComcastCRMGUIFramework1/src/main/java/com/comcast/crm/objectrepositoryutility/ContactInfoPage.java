package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver;

	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactheadermsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgedit;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastnameeditdata;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement ssddata;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement seddata;
	
	
	
	

	public WebElement getContactheadermsg() {
		return contactheadermsg;
	}

	public WebElement getOrgedit() {
		return orgedit;
	}
	
	

	public WebElement getSsddata() {
		return ssddata;
	}

	public WebElement getSeddata() {
		return seddata;
	}

	public WebElement getLastnameeditdata() {
		return lastnameeditdata;
	}
	
	
	
	

}
