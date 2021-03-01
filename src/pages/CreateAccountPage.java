package pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountPage extends PredefindActions {
	WebDriverWait wait;
	private static CreateAccountPage createAccountPage;
	private CreateAccountPage() {
		super();
	}
	
	public static CreateAccountPage getInstance(){
		if(createAccountPage == null) {
			createAccountPage = new CreateAccountPage();
		}
		return createAccountPage;
	}
	
	private void selectGender(boolean isMale) {
		System.out.println("STEP - Select title");
		wait = new WebDriverWait(driver, 30);
		if (isMale) {
			WebElement genMaleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
			wait.until(ExpectedConditions.elementToBeClickable(genMaleElement)).click();
		} else {
			WebElement genFemaleElement = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id_gender2")));
			genFemaleElement.click();
		}
		//genderElement.click();
		
		// another way of using if else
		/*genderElement = createAccountDetailsPojo.isMale() ? wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")))
				:  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
		wait.until(ExpectedConditions.elementToBeClickable(genderElement));
		genderElement.click();*/
	}
	
	private void enterFirstName(String firstName) {
		System.out.println("STEP - Enter First Name");
		if (firstName != null) {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname"))).sendKeys("");
			driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstName);
		} else 
			System.out.println("First Name is blank");
	}
	
	private void enterLastName(String lastName) {
		if (lastName != null) {
			System.out.println("STEP - Enter Last Name");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_lastname"))).sendKeys(lastName);
		} else 
			System.out.println("Last name is blank");	
	}
	
	private void enterPassword(String password) {
		if(password != null) {
		System.out.println("STEP - Enter Password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd"))).sendKeys(password);
		}else
			System.out.println("Password is blank");
	}
	
	private void selectDays(String day){
		System.out.println("STEP - Select Birthdate selected from drop down");
		Select s = new Select(driver.findElement(By.id("days")));
		s.selectByValue(day);
	}
	
	private void selectMonth(String month) {
		System.out.println("STEP - Birth month selected");
		Select s = new Select(driver.findElement(By.id("months")));
		s.selectByValue(month);
	}
	
	private void selectYear(String year) {
		System.out.println("STEP - Birth year selected");
		Select s = new Select(driver.findElement(By.id("years")));
		s.selectByValue(year);
	}
	
	private void enterCompany(String company) {
		System.out.println("STEP - Company Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company"))).sendKeys(company);
	}
	
	private void enterAddress(String address) {
		System.out.println("STEP - Address Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address1"))).sendKeys(address);	
	}
	
	private void enterCity(String city) {
		System.out.println("STEP - City Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys(city);
	}
	
	private void selectState(String state) {
		System.out.println("STEP - State is selected");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
		Select s = new Select(driver.findElement(By.id("id_state")));
		s.selectByVisibleText(state);
	}
	private void enterPost(String post) {
		System.out.println("STEP - Postcode is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postcode"))).sendKeys(post);
	}
	private void enterInfo(String info) {
		System.out.println("STEP - Other information is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("other"))).sendKeys(info);
	}
	
	private void enterHPhone(String hPhone) {
		System.out.println("STEP - Home mobile number is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys(hPhone);
	}
	private void entermPhone(String mPhone) {
		System.out.println("STEP - Mobile number is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_mobile"))).sendKeys(mPhone);
	}
	
	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		selectGender(createAccountDetailsPojo.isMale());
		enterFirstName(createAccountDetailsPojo.getFirstName());
		enterLastName(createAccountDetailsPojo.getLastName());
		enterPassword(createAccountDetailsPojo.getPassword());
		selectDays(createAccountDetailsPojo.getDays());
		selectMonth(createAccountDetailsPojo.getMonth());
		selectYear(createAccountDetailsPojo.getYear());
		enterCompany(createAccountDetailsPojo.getCompany());
		enterAddress(createAccountDetailsPojo.getAddress1());
		enterCity(createAccountDetailsPojo.getCity());
		selectState(createAccountDetailsPojo.getState());
		enterPost(createAccountDetailsPojo.getPostCode());
		enterInfo(createAccountDetailsPojo.getAdditionalInfo());
		enterHPhone(createAccountDetailsPojo.gethPhone());
		entermPhone(createAccountDetailsPojo.getmNumber());
	}
	
	public MyProfilePage clickOnRegistration() {
		System.out.println("Details Registered in Application");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		return MyProfilePage.getInstance();
	}
	
	// To check header text is displayed or not
	public boolean isCreateAccountPageHeaderText() {
		wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("#noSlide h1"), "CREATE AN ACCOUNT"));// .getText();
	}

	// wait for the page to load then return the Header text on CreateAccount Page
	public String getCreateAccountPageHeaderText() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#customer_firstname")));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#noSlide h1"))).getText();
	}
	
	// method to get error message on Create Account page
	public List<String> getErrorMessage() {
		List<WebElement> listOfErrorElements = driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText = new ArrayList<String>();
		String errorMsgHeader = driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText();
		listOfErrorText.add(0, errorMsgHeader);
		for(WebElement msgElement: listOfErrorElements) {
			listOfErrorText.add(msgElement.getText());
		}
		return listOfErrorText;
	}
}
