package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountPage extends PredefindActions {
	WebDriverWait wait;

	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		System.out.println("STEP - Select title");
		wait = new WebDriverWait(driver, 30);
		WebElement genderElement;
		if(createAccountDetailsPojo.isMale()) {
		genderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
		wait.until(ExpectedConditions.elementToBeClickable(genderElement));
		}else {
		genderElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id_gender2")));
		}
		genderElement.click();
		
		// another way of using if else
		/*genderElement = createAccountDetailsPojo.isMale() ? wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")))
				:  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
		wait.until(ExpectedConditions.elementToBeClickable(genderElement));
		genderElement.click();*/
		
		System.out.println("STEP - Enter First Name");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname"))).sendKeys("");
		driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(createAccountDetailsPojo.getFirstName());
		
		System.out.println("STEP - Enter Last Name");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_lastname"))).sendKeys(createAccountDetailsPojo.getLastName());

		System.out.println("STEP - Enter Password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd"))).sendKeys(createAccountDetailsPojo.getPassword());

		System.out.println("STEP - Select Birthdate selected from drop down");
		Select s = new Select(driver.findElement(By.id("days")));
		s.selectByValue(createAccountDetailsPojo.getDays());

		System.out.println("STEP - Birth month selected");
		s = new Select(driver.findElement(By.id("months")));
		s.selectByValue(createAccountDetailsPojo.getMonth());

		System.out.println("STEP - Birth year selected");
		s = new Select(driver.findElement(By.id("years")));
		s.selectByValue(createAccountDetailsPojo.getYear());

		System.out.println("STEP - Company Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company"))).sendKeys(createAccountDetailsPojo.getCompany());

		System.out.println("STEP - Address Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address1"))).sendKeys(createAccountDetailsPojo.getAddress1());

		System.out.println("STEP - City Name is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys(createAccountDetailsPojo.getCity());

		System.out.println("STEP - State is selected");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
		s = new Select(driver.findElement(By.id("id_state")));
		s.selectByVisibleText(createAccountDetailsPojo.getState());

		System.out.println("STEP - Postcode is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postcode"))).sendKeys(createAccountDetailsPojo.getPostCode());

		System.out.println("STEP - Other information is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("other"))).sendKeys(createAccountDetailsPojo.getAdditionalInfo());

		System.out.println("STEP - Home mobile number is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys(createAccountDetailsPojo.gethPhone());

		System.out.println("STEP - Mobile number is entered");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_mobile"))).sendKeys(createAccountDetailsPojo.getmNumber());
	}

	public MyProfilePage clickOnRegistration() {
		System.out.println("Details Registered in Application");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		return new MyProfilePage();
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
}
