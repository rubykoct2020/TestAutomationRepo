package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
