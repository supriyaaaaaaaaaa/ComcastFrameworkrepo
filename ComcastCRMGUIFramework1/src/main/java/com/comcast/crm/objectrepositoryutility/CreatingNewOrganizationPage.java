package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgnameEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	@FindBy(name = "industry")
	private WebElement industrydropdown;

	@FindBy(name = "accounttype")
	private WebElement typedropdown;
	
	@FindBy(id="phone")
	private WebElement phoneedit;

	

	public WebElement getOrgnameEdit() {
		return orgnameEdit;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getIndustrydropdown() {
		return industrydropdown;
	}

	public WebElement getTypedropdown() {
		return typedropdown;
	}
	
	

	public WebElement getPhoneedit() {
		return phoneedit;
	}

	// provide action for organization edit
	public void createorg(String orgName) {
		orgnameEdit.sendKeys(orgName);
		savebtn.click();
	}

	// provide actions for industry drop down

	public void createorg(String orgName, String idustry) {
		orgnameEdit.sendKeys(orgName);
		Select sel = new Select(industrydropdown);
		sel.selectByVisibleText(idustry);
		savebtn.click();
	}
	
	public void createorg(String orgName, String idustry,String type) {
		orgnameEdit.sendKeys(orgName);
		Select sel = new Select(industrydropdown);
		sel.selectByVisibleText(idustry);
		Select sel1 = new Select(typedropdown);
		sel1.selectByVisibleText(type);
		savebtn.click();

	}
	
	public void createorgwithphone(String orgName,String phoneno) {
		orgnameEdit.sendKeys(orgName);
		phoneedit.sendKeys(phoneno);
		
		
		
		savebtn.click();
	}

}
