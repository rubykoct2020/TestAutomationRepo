package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import util.PropertiesFileReader;

public class ProductDetailsPage extends PredefindActions {

	private PropertiesFileReader productDetailsPageProperties;
	private static ProductDetailsPage productDetailsPage;

	private ProductDetailsPage() {
		productDetailsPageProperties = new PropertiesFileReader("./src/config/productDetailsPageProperties");
	}

	public static ProductDetailsPage getInstance() {
		if (productDetailsPage == null)
			productDetailsPage = new ProductDetailsPage();
		return productDetailsPage;
	}

	public void captureProductDetails() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String productName = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(productDetailsPageProperties.getValue("productName"))))
				.getText();
		String unitPrice = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(productDetailsPageProperties.getValue("price"))))
				.getText();
		String quantity = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(productDetailsPageProperties.getValue("quantity"))))
				.getText();
		// String size =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(productDetailsPageProperties.getValue("price")))).getText();
		String size = getSelectedOption();
		System.out.println(productName + " " + unitPrice + " " + quantity + " " + size);
	}

	private String getSelectedOption() {
		String size = "";
		Select s = new Select(driver.findElement(By.cssSelector(productDetailsPageProperties.getValue("size"))));
		List<WebElement> list = s.getOptions();
		for (WebElement ele : list) {
			ele.getText();
			size = ele.getAttribute("title");
		}
		return size;
	}
}
