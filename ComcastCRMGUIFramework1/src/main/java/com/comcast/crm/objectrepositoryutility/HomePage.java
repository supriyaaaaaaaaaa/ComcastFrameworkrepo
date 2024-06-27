package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(linkText = "Organizations")
	private WebElement orglink;

	@FindBy(linkText = "Contacts")
	private WebElement contactlink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignslink;

	@FindBy(linkText = "More")
	private WebElement morelink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutlink;

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getCampaignslink() {
		return campaignslink;
	}

	public WebElement getMorelink() {
		return morelink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignoutlink() {
		return signoutlink;
	}

	// provide actions
	public void navigateToCampaginPage() {
		Actions act = new Actions(driver);
		act.moveToElement(morelink).perform();

		campaignslink.click();
	}
	
	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();

		signoutlink.click();
	}	
		
	}


