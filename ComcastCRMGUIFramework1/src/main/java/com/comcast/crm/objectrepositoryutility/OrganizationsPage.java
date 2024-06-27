package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	WebDriver driver;

	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNeworgBtn;

	@FindBy(name = "search_text")
	private WebElement searchEdit;

	@FindBy(name = "search_field")
	private WebElement Indropdown;

	@FindBy(name = "submit")
	private WebElement searchbtn;

	public WebElement getSearchbtn() {
		return searchbtn;
	}

	public WebElement getIndropdown() {
		return Indropdown;
	}

	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getCreateNeworgBtn() {
		return createNeworgBtn;
	}

}
