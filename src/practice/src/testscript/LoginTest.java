package testscript;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	@Test(invocationCount = 5)
	public void varifyValidLoginCredentials() {
		System.out.println("STEP : Open chrome browser and load https://staging.app.hirecorrecto.com");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://staging.app.hirecorrecto.com");

		System.out.println("VARIFY : Login page is loaded");
		WebElement hire360WelcomeElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Welcome to Hire360\"]")));

		boolean welcomeMessageFlag = hire360WelcomeElement.isDisplayed();
		Assert.assertTrue(welcomeMessageFlag);

		System.out.println("STEP : Eneter UserName");
		driver.findElement(By.id("outlined-adornment-email")).sendKeys("nilam.korade05@gmail.com");

		System.out.println("STEP : Eneter Password");
		driver.findElement(By.id("outlined-adornment-password")).sendKeys("Pass@123");

		System.out.println("STEP : Click Login button");
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();

		System.out.println(
				"VRIFY : Dashboard page is displyed in case of correct credentials,Invalid credtentials message in case of invalid credentials.");
		wait.until(ExpectedConditions.urlContains("dashboard"));

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://staging.app.hirecorrecto.com/dashboard";
		Assert.assertEquals(actualUrl, expectedUrl);

		driver.quit();
	}
}
