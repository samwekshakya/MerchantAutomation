package merchant;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MerchInfo extends BaseTest

{

	@BeforeClass
	public void merchanalyticmenu() {
		driver.findElement(By.linkText("Merchant Analytics")).click();
	}

	@Test(priority = 4)

	public void searchTxn() {
		/// Date Picker - Start Date
		WebElement dateInput = driver.findElement(By.xpath("//input[contains(@aria-label,'Datepicker input')]"));
		dateInput.click();

		// Set the date value using JavaScript
		String dateValue = "03/2024";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", dateInput, dateValue);

		// Trigger input and change events to ensure UI updates
		js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);

		// Wait for the calendar UI to update
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Ensure year selection is visible & clickable
		WebElement yearPicker = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class, 'dp__btn dp--year-select')]")));
		js.executeScript("arguments[0].click();", yearPicker);

		// Click on another input field to close the date picker
		WebElement outlet = driver.findElement(By.xpath("//label[contains(@class,'form-label') and contains(text(),'Outlet')]")); // Change to an actual field
//		outlet.click();


		

		
		
		
		
//		// Select the correct year (if needed)
//		WebElement targetYear = wait.until(ExpectedConditions.elementToBeClickable(
//			    By.xpath("//div[contains(@class, 'dp__overlay_cell_active dp__overlay_cell_pad') and contains(text(), '2024')]")));		
//			((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetYear);

		// Wait for and click the selected Start Date
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		WebElement selectedDate = driver.findElement(By.xpath("//div[contains(@class, 'dp__overlay_cell_active')]"));
//		selectedDate.click();

//		js.executeScript("arguments[0].click();", selectedDate);

	}

}
