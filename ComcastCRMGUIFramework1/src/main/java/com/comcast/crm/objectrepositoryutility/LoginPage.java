package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author HP
 * 
 * contains Login page elements & business lib like login()
 *
 */

public class LoginPage  extends WebDriverUtility { // Rule-1 create a separate java class

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Rule-2 object Creation
	@FindBy(name = "user_name")
	private WebElement usernameEdit;

	@FindBy(name = "user_password")
	private WebElement passwordEdit;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// Rule 3:object Intialization // this is done in test script

	// Rule4:object Encapsulation

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;

	}
	
	/**
	 * login to application based on username ,password,url arguments
	 * @param URL
	 * @param username
	 * @param password
	 */

//provide actions
	public void loginToApp(String URL,String username, String password) {
		waitForPageToLoad(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();

	}

	
}
