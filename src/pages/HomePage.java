package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;

public class HomePage extends PredefindActions {
	
	public AuthenticationPage clickOnSignIn() {
		System.out.println("Home Page - Click on sign in");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login")));
		wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
		System.out.println("page");
		return new AuthenticationPage();
	}
}
