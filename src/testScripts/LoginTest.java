package testScripts;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.MyProfilePage;

public class LoginTest extends BaseTest{
	@Test (priority=2)
	public void verifySucessLogin() {
		
		System.out.println("STEP - Click on Sign in link on Home page");
		//HomePage home = new HomePage();
		//AuthenticationPage auth = home.clickOnSignIn();
		AuthenticationPage auth = AuthenticationPage.getInstance();
		System.out.println("STEP - Enter Username");
		auth.enterUsername("hana99@test.com");
		
		System.out.println("STEP - Enter Password");
		auth.enterPassword("testing123");
		
		System.out.println("STEP - Click on SignIn button to Login");
		MyProfilePage myProfilePage  = auth.clickOnSignIn();
		String actualHeaderText = myProfilePage.getaccountHeaderText();
		
		System.out.println("STEP - Verify Successful Login and Header is displayed on My Account page");
		String expectedHeader = "MY ACCOUNT";
		Assert.assertEquals(expectedHeader, actualHeaderText);	
	}
	
	@Test (priority=1)
	public void verifyInvalidLogin() {
		
		System.out.println("STEP - Click on Sign in link on Home page");
		HomePage home = HomePage.getInstance();
		AuthenticationPage auth = home.clickOnSignIn();
		
		System.out.println("STEP - Enter Username");
		auth.enterUsername("hana@test.com");
		
		System.out.println("STEP - Enter Password");
		auth.enterPassword("testing123");
		
		System.out.println("STEP - Click on SignIn button to Login");
		auth.clickOnSignIn();
		String actualHeaderText = auth.getInvalidMessage();
		
		System.out.println("STEP - Verify Successful Login and Header is displayed on My Authentication page");
		String expectedHeader = "Authentication failed.";
		Assert.assertEquals(expectedHeader, actualHeaderText);	
	}
	
}
