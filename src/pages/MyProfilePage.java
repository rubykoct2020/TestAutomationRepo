package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;

public class MyProfilePage extends PredefindActions {

	public String getHeaderText() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span"))).getText();
		return actual;
	}
}
