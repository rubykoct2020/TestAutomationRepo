package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefindActions;
import expectionHandling.ProductNotFoundException;
import util.PropertiesFileReader;

public class ProductCatagoryPage extends PredefindActions {

	private PropertiesFileReader productCatagoryPageProperties;
	private static ProductCatagoryPage productCatagoryPage;

	private ProductCatagoryPage() {
		// TODO Auto-generated constructor stub
		productCatagoryPageProperties = new PropertiesFileReader("./src/config/productCatagoryPageProperties");
	}

	public static ProductCatagoryPage getInstance() {
		if (productCatagoryPage == null)
			productCatagoryPage = new ProductCatagoryPage();
		return productCatagoryPage;
	}

	public List<WebElement> getProductList() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector(productCatagoryPageProperties.getValue("ProductList"))));
		return productList;
	}

	public ProductDetailsPage selectFirstProduct(List<WebElement> productList) {
		try {
			if (productList.size() > 0)
				productList.get(0).click();
			else
				throw new ProductNotFoundException("Product not found");
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return ProductDetailsPage.getInstance();
	}
	
	public ProductDetailsPage clickOnPopUpProduct() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement prodElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(productCatagoryPageProperties.getValue("ProductPopUp"))));
		prodElement.click();
		return ProductDetailsPage.getInstance();
	}
}
