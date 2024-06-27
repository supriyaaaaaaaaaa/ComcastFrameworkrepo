package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	@Test(dataProvider = "getdata")
	public void getproductInfoTest(String brandName, String productName) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);

		// capture product info
		String price = driver.findElement(By.xpath("//span[text()='" + productName+ "']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]")).getText();
		Thread.sleep(2000);
		System.out.println(price);
		Thread.sleep(2000);

		driver.close();
	}

	@DataProvider
	public Object[][] getdata() {
		Object[][] objArr = new Object[3][2];
		objArr[0][0] = "iphone";
		objArr[0][1] = "Apple iPhone 15 (128 GB) - Black";

		objArr[1][0] = "iphone";
		objArr[1][1] = "Apple iPhone 14 (128 GB) - Blue";

		objArr[2][0] = "iphone";
		objArr[2][1] = "Apple iPhone 13 (128GB) - Green";

		return objArr;

	}

}
