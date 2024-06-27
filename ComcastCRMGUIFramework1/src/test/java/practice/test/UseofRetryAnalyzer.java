package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UseofRetryAnalyzer {
	
	@Test(retryAnalyzer =com.comcast.crm.listenerutility.RetryListenerImp.class )
	public void activatesim() {
		System.out.println("execute activatesim");
	
	//	Assert.assertEquals("", "login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");

}
}