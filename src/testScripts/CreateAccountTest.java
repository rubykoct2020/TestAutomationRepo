package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.PredefindActions;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountTest {
	
	@Test
	public void createAccount() {
		
		System.out.println("STEP - Open Browser");
		PredefindActions.start("http://automationpractice.com/index.php");
		
		System.out.println("Navigate to Application");
		HomePage homePage = new HomePage();
		System.out.println("Navigate to Home Page");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("Verify - Authentication Header displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);
		
		System.out.println("Navigate to Authentication page");
		authenticationPage.enterEmailAddress("hana9@testing.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("Verify - Create Account Page Heading Text is as expected");
		// verify header is visible or not and show message pass in function if assert fails
		/*boolean isheadingText = createAccountPage.isCreateAccountPageHeaderText();
		Assert.assertTrue(isheadingText, "Header was not displayed");*/
		
		//Verify the content of Header on CreateAccount Page
		String headerOnCreateAccount = createAccountPage.getCreateAccountPageHeaderText();
		Assert.assertEquals(headerOnCreateAccount, "CREATE AN ACCOUNT");
		
		System.out.println("Navigate to create account page");
		// set all parameter in pojo class 
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(true);
		createAccountDetailsPojo.setFirstName("AutomationTest");
		createAccountDetailsPojo.setLastName("Execute");
		createAccountDetailsPojo.setPassword("test123");
		createAccountDetailsPojo.setDays("3");
		createAccountDetailsPojo.setMonth("12");
		createAccountDetailsPojo.setYear("1998");
		createAccountDetailsPojo.setCompany("CTB"); 
		createAccountDetailsPojo.setAddress1("address1");
		createAccountDetailsPojo.setCity("nashville");
		createAccountDetailsPojo.setState("Tennessee");
		createAccountDetailsPojo.setPostCode("32711");
		createAccountDetailsPojo.setAdditionalInfo("");
		createAccountDetailsPojo.sethPhone("6767777");
		createAccountDetailsPojo.setmNumber("3456667");
		
		// pass the pojo class reference var in method to set data
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		
		System.out.println("Navigate to MyProfile page");
		String actual = myProfilePage.getHeaderText();
		String expected = "AutomationTest Execute";
		Assert.assertEquals(actual, expected, "Verification not happend");
	}
}
