package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ControlActions {
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	public static void launchBrowser() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://staging.app.hirecorrecto.com");
	}

	public static void closeBrowser() {
		driver.close();
	}
}
