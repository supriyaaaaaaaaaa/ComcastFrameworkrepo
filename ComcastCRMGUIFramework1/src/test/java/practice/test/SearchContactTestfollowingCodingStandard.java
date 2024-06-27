package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author HP
 *
 */

public class SearchContactTestfollowingCodingStandard extends BaseClass {
	
	/**
	 * 
	 * scenario : login () ==>navigatecontact ==>createcontact ()==verify
	 */
	
	@Test
	public void searchContactTest() {
		/*step1: login to app*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
		
	}
	

}
