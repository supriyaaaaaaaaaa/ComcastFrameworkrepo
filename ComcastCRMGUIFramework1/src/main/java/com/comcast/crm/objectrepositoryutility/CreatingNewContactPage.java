package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnameEdit;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement orgnamelookupimage;

	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
     @FindBy(name="support_start_date")
     private WebElement ssdcalendar;
     
     @FindBy(name="support_end_date")
     private WebElement sedcalendar;

	public WebElement getLastnameEdit() {
		return lastnameEdit;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public WebElement getOrgnamelookupimage() {
		return orgnamelookupimage;
	}
	
	

	
	public WebElement getSsdcalendar() {
		return ssdcalendar;
	}

	public WebElement getSedcalendar() {
		return sedcalendar;
	}

		// provide action for  contact edit
		public void createcontact(String LASTNAME) {
			
			lastnameEdit.sendKeys(LASTNAME);
			savebtn.click();
		}
		
		public void date(String LASTNAME,String StartDate,String endDate) {
			lastnameEdit.sendKeys(LASTNAME);
			ssdcalendar.clear();
			ssdcalendar.sendKeys(StartDate);
			sedcalendar.clear();
			sedcalendar.sendKeys(endDate);
			savebtn.click();
			
			
			
		}
		
		public void createContactWithOrg(String contactLastname, String orgName) {
			lastnameEdit.sendKeys(contactLastname);
			orgnamelookupimage.click();
			
			
			
			
			
			
		}

	

	


}
