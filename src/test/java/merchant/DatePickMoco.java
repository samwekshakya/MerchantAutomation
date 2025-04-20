package merchant;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickMoco {

	public static String browser = "chrome"; // External Configuration- XLS, CSV
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		if (browser.equals("Firefix")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.get("https://mps0.moco.com.np/?#/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

		WebElement uname = driver.findElement(By.id("login_user"));
		uname.sendKeys("9818487677");
		WebElement pass = driver.findElement(By.id("login_password"));
		pass.sendKeys("123456");

		WebElement login = driver
				.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div/div/form/div[3]/div/button"));
		login.click();

		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/ul/li[3]/a")).click();// trans click

		// Date Picker - Start Date
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
		WebElement selectedStartDate = driver.findElement(By.xpath("//div[contains(@class, 'dp__cell_inner dp__pointer dp__active_date')]"));
		selectedStartDate.click(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Can be replaced with WebDriverWait

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the date picker to be visible and stable
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dp__calendar')]")));

		// Wait for the specific `selectedEndDate` element to become clickable again
		WebElement selectedEndDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'dp__cell_inner dp__pointer dp__active_date')]")));

		// Print debug info (for troubleshooting)
//		System.out.println("Is End Date clickable: " + selectedEndDate.isEnabled());
//		System.out.println("Is End Date displayed: " + selectedEndDate.isDisplayed());

		// Click the selected end date to confirm
		selectedEndDate.click();


	}
}
