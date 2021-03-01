package testScripts;



import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import base.PredefindActions;

public class BaseTest {
	@BeforeClass
	public void setUp() {
		System.out.println("STEP - Open Browser");
		PredefindActions.start("http://automationpractice.com/index.php");
	}
	
	@AfterClass
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			PredefindActions.captureScreenShot(result);
			System.out.println("ScreenShot is taken for "+ result.getName());
		}
		System.out.println("Close Browser");
		PredefindActions.close();
	}
}
