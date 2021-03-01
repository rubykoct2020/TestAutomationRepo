package testScripts;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.ProductCatagoryPage;
import pages.ProductDetailsPage;

public class E2EProductPurchaseTest extends BaseTest {

	@Test
	public void placeOrder() {
		HomePage homePage = HomePage.getInstance();
		AuthenticationPage authPage = homePage.clickOnSignIn();
		System.out.println("STEP - Login");
		MyProfilePage myProfilePage=authPage.doLogin("hana99@test.com", "testing123");
		
		System.out.println("Select Women Section on catagory page ");
		ProductCatagoryPage productCatagoryPage = myProfilePage.selectSection("Women");
		List<WebElement> productList = productCatagoryPage.getProductList();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productList.size(), 7);
		softAssert.assertTrue(productList.size() >=1, "Not same");
		
		System.out.println("Select Product on Product Catagory page");
		ProductDetailsPage productDetailsPage = productCatagoryPage.selectFirstProduct(productList);
			
		/*System.out.println("Click on Product Pop Up");
		ProductDetailsPage productDetailsPage = productCatagoryPage.clickOnPopUpProduct();*/
		
		System.out.println("Get product Details");
		productDetailsPage.captureProductDetails();
		
	}
}
