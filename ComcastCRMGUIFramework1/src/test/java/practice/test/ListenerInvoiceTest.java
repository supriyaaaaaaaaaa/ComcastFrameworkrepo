package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
//@Listeners(com.comcast.crm.listenerutility.ListenerImp.class)
public class ListenerInvoiceTest extends BaseClass {
	
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	
	@Test
	public void createInvoicewithcontactTest() {
		System.out.println("execute createInvoicewithcontactTest");
		
		
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
		

		
	}
	
	

}
