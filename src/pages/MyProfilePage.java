package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import util.PropertiesFileReader;

public class MyProfilePage extends PredefindActions {
	private PropertiesFileReader myProfilePageProperties;
	private static MyProfilePage myProfilePage;
	
	private MyProfilePage() {
		// TODO Auto-generated constructor stub
		myProfilePageProperties = new PropertiesFileReader("./src/config/myProfilePageProperties");
	}

	public static MyProfilePage getInstance() {
		if(myProfilePage == null)
			myProfilePage = new MyProfilePage();
		return myProfilePage;	
	}
	
	public String getHeaderText() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span"))).getText();
		return actual;
	}
	
	public String getaccountHeaderText() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement actualHeaderText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-heading")));
		return	actualHeaderText.getText();
	}
	
	public ProductCatagoryPage selectSection(String sectionName) {
		WebElement catagory = null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		switch (sectionName.toUpperCase()) {
			case "WOMEN":
			catagory = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(myProfilePageProperties.getValue("WomenLink"))));
			catagory.click();
			break;
			
			case "DRESSES":
			catagory = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(myProfilePageProperties.getValue("DressesLink"))));
			catagory.click();
			break;
			
			case "T-SHIRTS":
			catagory = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(myProfilePageProperties.getValue("TShirtLink"))));
			catagory.click();
			break;
			
			default:
				break;	
		}
		return ProductCatagoryPage.getInstance();
	}
	
}
