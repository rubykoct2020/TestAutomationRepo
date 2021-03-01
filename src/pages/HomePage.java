package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import util.PropertiesFileReader;

public class HomePage extends PredefindActions {
	PropertiesFileReader homePageProperties;
	private static HomePage homePage;
	private HomePage() {
		homePageProperties = new PropertiesFileReader("./src/config/homePageProperties");
	}
	
	public static HomePage getInstance() {
		if(homePage == null)
			homePage = new HomePage();
		return homePage;
	}
	
	public AuthenticationPage clickOnSignIn() {
		System.out.println("Home Page - Click on sign in");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(homePageProperties.getValue("SignInLink"))));
		wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
		return AuthenticationPage.getInstance();
	}
}
