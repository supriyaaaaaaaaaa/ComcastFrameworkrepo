package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactTest {

	public static void main(String[] args) throws Exception {
		// create object properties file class
		FileUtility flib = new FileUtility();
		
		// create object excel file class
		ExcelUtility elib = new ExcelUtility();
		
	
		String URL = flib.getDataFromPropertiesFile("url");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		 
		// read test script data from excel file
	
		String LASTNAME = elib.getDataFromExcel("contact", 1, 2); 
		

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// step1:login to app
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		// step2:navigate to Contacts module

		driver.findElement(By.linkText("Contacts")).click();
		// step3:click on create Contacts button

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		// step4:enter the details& create Contacts

		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step5:verify Contacts name in the header of the msg
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

		if (header.contains(LASTNAME)) {
			System.out.println("contacts created successfully");
		} else {
			System.out.println("Failed to create contacts");
		}

		// verify Header lastname info expected result
		String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastname.equals(LASTNAME)) {
			System.out.println(LASTNAME + "  information is verified==PASS");
		} else {
			System.out.println(LASTNAME + " is not verified==FAIL");
		}
		// step6:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
