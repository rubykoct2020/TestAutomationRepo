package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;

public class AuthenticationPage extends PredefindActions{
	WebDriverWait wait;
	public void enterEmailAddress(String emailID) {
		System.out.println("Enter email address for the create user name");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")))
		.sendKeys(emailID);
	}
	
	public CreateAccountPage clickOnCreateAccount() {
		System.out.println("Clicked on the submit button to fill the other details");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		return new CreateAccountPage();
	} 
	
	public boolean isAuthenticationHeaderVisible() {
		wait = new WebDriverWait(driver, 30);
		WebElement AuthenticationHeaderVisibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Authentication']")));
		return AuthenticationHeaderVisibleElement.isDisplayed();
	}
}
