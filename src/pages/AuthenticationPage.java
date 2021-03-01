package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import util.PropertiesFileReader;

public class AuthenticationPage extends PredefindActions{
	WebDriverWait wait;
	private PropertiesFileReader authProperties;
	private static AuthenticationPage authenticationPage;
	private AuthenticationPage() {
		authProperties = new PropertiesFileReader("./src/config/authenticationPageProperties.properties");
	}
	
	public static AuthenticationPage getInstance() {
		if (authenticationPage == null)
			authenticationPage = new AuthenticationPage();
		return authenticationPage;
	}
	
	public void enterEmailAddress(String emailID) {
		System.out.println("Enter email address for the create user name");
		wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")))
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(authProperties.getValue("CreateAccountEmail")))).sendKeys(emailID);	
	}
	
	public CreateAccountPage clickOnCreateAccount() {
		System.out.println("Clicked on the submit button to fill the other details");
		wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(authProperties.getValue("CreateAccountButton")))).click();
		return CreateAccountPage.getInstance();
	} 
	
	public boolean isAuthenticationHeaderVisible() {
		wait = new WebDriverWait(driver, 30);
		//WebElement AuthenticationHeaderVisibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Authentication']")));
		WebElement AuthenticationHeaderVisibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authProperties.getValue("AuthHeaderText"))));
		return AuthenticationHeaderVisibleElement.isDisplayed();
	}
	
	public MyProfilePage clickOnSignIn() {
		wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".icon-lock.left"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(authProperties.getValue("LoginSignInButton")))).click();
		return MyProfilePage.getInstance();
	}
	
	public void enterUsername(String username) {
		wait = new WebDriverWait(driver, 10);
		//WebElement textbox= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		WebElement textbox= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(authProperties.getValue("LoginUsername"))));
		textbox.clear();
		textbox.sendKeys(username);
	}
	
	public void enterPassword(String pwd) {
		wait = new WebDriverWait(driver, 10);
		//WebElement textbox= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwd")));
		WebElement textbox= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(authProperties.getValue("Loginpassword"))));
		textbox.clear();
		textbox.sendKeys(pwd);
	}
	
	public String getInvalidMessage() {
		return	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger li"))).getText();
	}
	
	public MyProfilePage doLogin(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		MyProfilePage myProfilePage = clickOnSignIn();
		return myProfilePage;
	}
}
