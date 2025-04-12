package testscript;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class DashboardTest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		System.out.println("Step : Open chrome browser and load https://staging.app.hirecorrecto.com");
		WebDriver driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://staging.app.hirecorrecto.com");

		System.out.println("Wait until Login page loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Welcome to Hire360\"]")));

		System.out.println("STEP : Enter USERNAME");
		driver.findElement(By.id("outlined-adornment-email")).sendKeys("nilam.korade05@gmail.com");

		System.out.println("STEP : Eneter PASSWORD");
		driver.findElement(By.id("outlined-adornment-password")).sendKeys("Pass@123");

		System.out.println("STEP : Click Login button");
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();

	}

	@Test
	public void VerifyDashboradUIElements() {
		
		System.out.println("VERIFY :  \"Hello, Welcome Back!!\" message on the dashboard.");
		WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Hello, Welcome Back!!\"]")));
		Assert.assertTrue(webElement.isDisplayed());
		
		System.out.println("VERIFY :  \"Total Assessments\" Lable.");
		WebElement totalAssessmentsLabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Assessments\"]")));
		//WebElement totalAssessmentsLabel=driver.findElement(By.xpath("//p[text()=\'Total Assessments\']"));
		Assert.assertTrue(totalAssessmentsLabel.isDisplayed());
		
		System.out.println("VERIFY : \"Total Assessments\" count is >= 0.");
		WebElement assessmentsCountElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Assessments\"]/following-sibling::h6")));
		int totalAssessmentCount=Integer.parseInt(assessmentsCountElement.getText());
		Assert.assertTrue(totalAssessmentCount>=0, "Total Assessments count is negative!");
		
		System.out.println("VERIFY : \"Total Total Appeared\" Lable.");
		WebElement totalAppearedLabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Appeared\"]")));
		Assert.assertTrue(totalAppearedLabel.isDisplayed());
		
		System.out.println("VERIFY : \"Total Total Appeared\" count is >= 0.");
		WebElement appearedCount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Appeared\"]/following-sibling::h6")));
		int totalAppearedCount=Integer.parseInt(appearedCount.getText());
		Assert.assertTrue(totalAppearedCount>=0, "Total Appeared count is negative!");
		
		System.out.println("VERIFY : \"Total Qualified\" Lable");
		WebElement totalQualifiedLabel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Qualified\"]")));
		Assert.assertTrue(totalQualifiedLabel.isDisplayed());
		
		System.out.println("VERIFY : \"Total Qualified\" count should be in %.");
		// Wait for the element and get text
		WebElement qualifiedCount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Total Qualified\"]/following-sibling::h6")));
		String qualifiedCountText=qualifiedCount.getText();	
		System.out.println("Extracted text: " + qualifiedCountText);
		// Validate % symbol is present
		Assert.assertTrue(qualifiedCountText.endsWith("%"),"Qualified count should show in %, but it was: " + qualifiedCountText);
		// Extract numeric value and validate it's >= 0
		int totalQualifiedCount=Integer.parseInt(qualifiedCountText.replace("%", ""));
		Assert.assertTrue(totalQualifiedCount>=0, "Qualified % count should be >= 0, but it was: " + totalQualifiedCount);
		System.out.println("Total Qualified count: " + totalQualifiedCount + "%");
	
		System.out.println("VERIFY : \"View Template\" button is visible");
		WebElement viewTemplate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"View Template\"]")));
		Assert.assertTrue(viewTemplate.isDisplayed());
		
		System.out.println("VERIFY : \"Create Question\" button is visible");
		WebElement createQuestion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Create Question\"]")));
		Assert.assertTrue(createQuestion.isDisplayed());
		
		System.out.println("VERIFY : \"Create Assessment\" button is visible");
		WebElement createAssessment=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Create Assessment\"]")));
		Assert.assertTrue(createAssessment.isDisplayed());
		
	}

}
