package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;

	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;

	@FindBy(id = "dtlview_Industry")
	private WebElement industrydata;

	@FindBy(id = "dtlview_Type")
	private WebElement typedata;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgnamedata;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phonedata;

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getIndustrydata() {
		return industrydata;
	}

	public WebElement getTypedata() {
		return typedata;
	}

	public WebElement getOrgnamedata() {
		return orgnamedata;
	}

	public WebElement getPhonedata() {
		return phonedata;
	}
	
	
	

}
