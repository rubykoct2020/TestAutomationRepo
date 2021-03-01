package testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;
import util.ReadExcel;

public class CreateAccountTest extends BaseTest {
	//Without Excel Data
	@Test
	public void createAccountSuccess() {

		System.out.println("Navigate to Application");
		//HomePage homePage = new HomePage();
		HomePage homePage = HomePage.getInstance();
		System.out.println("Navigate to Home Page");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);

		System.out.println("Navigate to Authentication page");
		authenticationPage.enterEmailAddress("hana13@testing.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();

		System.out.println("Verify - Create Account Page Heading Text is as expected");
		// verify header is visible or not and show message pass in function if assert
		// fails
		/*
		 * boolean isheadingText = createAccountPage.isCreateAccountPageHeaderText();
		 * Assert.assertTrue(isheadingText, "Header was not displayed");
		 */

		// Verify the content of Header on CreateAccount Page
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
	
	@DataProvider (name="CreateAccountDataProvider")
	public Object[][] getDataForCreateAccount() throws IOException{
		return ReadExcel.getReadExcelData("datatest.xlsx", "CreateAccount");
	}
	
	//With Excel Data
	@Test (dataProvider = "CreateAccountDataProvider")
	public void createAccountSuccessFromExcel(String email,String gender, String firstName, String lastName,
			String password, String day, String month, String year, String company, String addr1,
			String city, String state, String postCode, String info, String hPhone, String mNum, String alias ) {

		System.out.println("Navigate to Application");
		HomePage homePage = HomePage.getInstance();
		System.out.println("Navigate to Home Page");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);

		System.out.println("Navigate to Authentication page");
		authenticationPage.enterEmailAddress(email);
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();

		System.out.println("Verify - Create Account Page Heading Text is as expected");
		// Verify the content of Header on CreateAccount Page
		String headerOnCreateAccount = createAccountPage.getCreateAccountPageHeaderText();
		Assert.assertEquals(headerOnCreateAccount, "CREATE AN ACCOUNT");

		System.out.println("Navigate to create account page");
		// set all parameter in pojo class
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		boolean genderFlag = gender.equalsIgnoreCase("male"); //? true : false;
		if(genderFlag)
			createAccountDetailsPojo.setMale(true);
		else
			createAccountDetailsPojo.setMale(false);
		createAccountDetailsPojo.setMale(genderFlag);
		createAccountDetailsPojo.setFirstName(firstName);
		createAccountDetailsPojo.setLastName(lastName);
		createAccountDetailsPojo.setPassword(password);
		createAccountDetailsPojo.setDays(day);
		createAccountDetailsPojo.setMonth(month);
		createAccountDetailsPojo.setYear(year);
		createAccountDetailsPojo.setCompany(company);
		createAccountDetailsPojo.setAddress1(addr1);
		createAccountDetailsPojo.setCity(city);
		createAccountDetailsPojo.setState(state);
		createAccountDetailsPojo.setPostCode(postCode);
		createAccountDetailsPojo.setAdditionalInfo(info);
		createAccountDetailsPojo.sethPhone(hPhone);
		createAccountDetailsPojo.setmNumber(mNum);

		// pass the pojo class reference var in method to set data
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();

		System.out.println("Navigate to MyProfile page");
		String actual = myProfilePage.getHeaderText();
		String expected = firstName +" "+lastName;
		Assert.assertEquals(actual, expected, "Verification not happend");
	}
	
	@Test
	public void createAccountUIValidation() {
		System.out.println("STEP - Open Browser");
		//PredefindActions.start();
		HomePage homePage = HomePage.getInstance();

		System.out.println("STEP - Click on SignIn Link");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("STEP - Enter email on Authentication Page");
		authenticationPage.enterEmailAddress("hana12@testing.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("STEP - Navigate to Create Account Page ");
		//createAccountDetailsPojo = new CreateAccountDetailsPojo();
		//createAccountPage.enterCreateAccountDetails();
		System.out.println("STEP - Click on Registration button");
		createAccountPage.clickOnRegistration();

		List<String> listofExpectedMsg = new ArrayList<String>();
		listofExpectedMsg.add("There are 8 errors");
		listofExpectedMsg.add("You must register at least one phone number.");
		listofExpectedMsg.add("lastname is required.");
		listofExpectedMsg.add("firstname is required.");
		listofExpectedMsg.add("passwd is required.");
		listofExpectedMsg.add("address1 is required");
		listofExpectedMsg.add("city is required.");
		listofExpectedMsg.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		listofExpectedMsg.add("This country requires you to choose a State.");	

		List<String> listofactualMsg = createAccountPage.getErrorMessage();
		Assert.assertEquals(listofactualMsg, listofExpectedMsg);
	}
}
