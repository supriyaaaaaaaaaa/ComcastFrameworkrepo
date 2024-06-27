package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product {
	
	
	WebDriver driver;

	public Product(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createNewproductBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchedit;;

	public WebElement getCreateNewproductBtn() {
		return createNewproductBtn;
	}

	public WebElement getSearchedit() {
		return searchedit;
	}

	
}
