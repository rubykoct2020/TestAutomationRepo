package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.google.common.io.Files;

public class PredefindActions {
	
	protected static WebDriver driver;
	static public void start() {
	start("https://www.google.com/");
	}
	
	static public void start(String url) {
	String path = "./resources/windows/chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", path);
	driver = new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	}
	
	public static void captureScreenShot(ITestResult result) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file,new File("./takescreenshot/"+result.getName()+".png") );
			//Files.copy(file, new File("./takescreenshot/"+result.getName()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void close() {
	driver.quit();
	}
}
