package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationNewWindowPage {

	WebDriver driver;

	public OrganizationNewWindowPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "search_txt")
	private WebElement searchedit;
	
	@FindBy(name="search")
	private  WebElement searchnowbtn;

	public WebElement getSearchedit() {
		return searchedit;
	}

	public WebElement getSearchnowbtn() {
		return searchnowbtn;
	}
	
	//provide actions
	public void searchtxtfield(String orgname) {
		
		searchedit.sendKeys(orgname);
		searchnowbtn.click();
		
	}

}
