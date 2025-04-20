package merchant;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver;
	SoftAssert sa = new SoftAssert();

	@BeforeSuite(alwaysRun=true)
	public void setup() throws InterruptedException {
		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.get("https://mps0.moco.com.np/?#/login");
			loginToPortal(); // Login once
		}
	}

	public void loginToPortal() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Perform login steps
		driver.findElement(By.id("login_user")).sendKeys("9741805403");
		Thread.sleep(2000);
		driver.findElement(By.id("login_password")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div/div/form/div[3]/div/button")).click();
		Thread.sleep(2000);
	}

	public void dateSelect() throws InterruptedException {
		/// Date Picker - Start Date
		WebElement startdateInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Start Date')]"));
		startdateInput.click();

		// Set the date value using JavaScript
		String startdateValue = "2024-04-01";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", startdateInput, startdateValue);

		// Trigger input and change events to ensure UI updates
		js.executeScript("arguments[0].dispatchEvent(new Event('input'));", startdateInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", startdateInput);

		// Wait for and click the selected Start Date
		WebElement selectedStartDate = driver
				.findElement(By.xpath("//div[contains(@class, 'dp__cell_inner dp__pointer dp__active_date')]"));
		Thread.sleep(2000);
		selectedStartDate.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Date Picker - End Date
		WebElement enddateInput = driver.findElement(By.xpath("//input[contains(@placeholder,'End Date')]"));
		enddateInput.click();

		// Set the date value using JavaScript
		String enddateValue = "2024-04-28";
		js.executeScript("arguments[0].value = arguments[1];", enddateInput, enddateValue);

		// Trigger input and change events to ensure UI updates
		js.executeScript("arguments[0].dispatchEvent(new Event('input'));", enddateInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", enddateInput);

		// **Wait explicitly for the end date element to be clickable again**
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for the date picker to be visible and stable
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dp__calendar')]")));

		// Wait for the specific `selectedEndDate` element to become clickable again
		WebElement selectedEndDate = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class, 'dp__cell_inner dp__pointer dp__active_date')]")));

		// Print debug info (for troubleshooting)
//			System.out.println("Is End Date clickable: " + selectedEndDate.isEnabled());
//			System.out.println("Is End Date displayed: " + selectedEndDate.isDisplayed());

		// Click the selected end date to confirm
		Thread.sleep(2000);
		selectedEndDate.click();
	}

//	    
//	    @AfterSuite(alwaysRun=true)
//	    public void teardown() throws InterruptedException {
//	        if (driver != null) {
//	        	Thread.sleep(4000);
//	            driver.quit();  // Close the browser after all tests
//	        }
//	    }

}