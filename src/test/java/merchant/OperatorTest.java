package merchant;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class OperatorTest extends BaseTest {
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeClass
	public void operatormenu() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.linkText("Operator")).click();
	}

	@Test(priority = 8)
	public void addOperator() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement plus = driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-plus')]"));
		wait.until(ExpectedConditions.visibilityOf(plus));
		Thread.sleep(2000);
		plus.click();

		WebElement opname = null;
		int attempts = 0;

		while (attempts < 3) { // Retry max 3 times
			try {
				opname = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Operator Name')]"));
				wait.until(ExpectedConditions.visibilityOf(opname));
				Thread.sleep(2000);
				opname.sendKeys("Test");

				break; // Exit loop if successful
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException encountered. Retrying...");
				attempts++;
			}
		}
		WebElement opnum = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Mobile Number')]"));
		wait.until(ExpectedConditions.visibilityOf(opnum));
		Thread.sleep(2000);
		opnum.sendKeys("9741805403");

		WebElement roledrop = driver.findElement(By.xpath("//select[contains(@class,'form-select')]"));
		Select roleselect = new Select(roledrop);
		Thread.sleep(2000);
		roleselect.selectByValue("3");

		// Click the dropdown to activate the search field
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"#app > div > div.wrapper.d-flex.flex-column.min-vh-100.bg-light > div.body.flex-grow-1.px-1.pt-3 > div > div:nth-child(2) > div > div.row.mb-4 > div:nth-child(10) > div")));
		dropdown.click();

		// Locate the search input field inside the dropdown
		Thread.sleep(2000);
		WebElement searchInput = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@aria-placeholder, 'Select Outlet')]")));
		searchInput.sendKeys("Refund Outlet 2"); // Input search term

		// Wait for the option to be clickable and select it
		Thread.sleep(2000);
		WebElement option = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label, 'Refund Outlet 2')]")));
		option.click();
		searchInput.clear();
		Thread.sleep(2000);
		WebElement add = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add')]")));
		add.click();

		Thread.sleep(2000);
		// Internal Confirm
		WebElement confirmYes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Yes')]")));
		confirmYes.click();

		// Confirmation Message
		WebElement confirmation = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(@class,'modal-title')]")));

		// Assertion part
		String actualMessage = driver
				.findElement(By.xpath("//div[contains(@class, 'modal-body') and contains(text(), 'has been added')]"))
				.getText();
		System.out.println(actualMessage);

		// Regex pattern: Allows any name (words with spaces), but keeps fixed structure
		String regex = "The Operator \\\"[A-Za-z0-9_]+\\\" has been added\\.";

		// Validate message
		AssertJUnit.assertTrue("❌ Message does not match the expected pattern!\nActual: " + actualMessage,
				(Pattern.matches(regex, actualMessage)));
		Thread.sleep(2000);
		WebElement successok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Ok')]")));
		successok.click();

		Thread.sleep(2000);

	}

	@Test(priority = 9)
	public void editOperator() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

		for (WebElement row : rows) {
			WebElement mobileElement = row.findElement(By.xpath("./td[4]"));
			String mobileNumber = mobileElement.getText().trim();

			if (mobileNumber.equals("9741805403")) {

				WebElement editButton = row.findElement(By.xpath(".//td[last()]//a[1]"));
				Thread.sleep(2000);
				editButton.click();
			}

			WebElement newname = null;
			int attempts = 0;

			while (attempts < 3) { // Retry max 3 times
				try {
					newname = driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter Operator Name')]"));
					wait.until(ExpectedConditions.visibilityOf(newname));
					newname.clear();
					Thread.sleep(2000);
					newname.sendKeys("Sams");
					break; // Exit loop if successful
				} catch (StaleElementReferenceException e) {
					System.out.println("StaleElementReferenceException encountered. Retrying...");
					attempts++;
				}
			}

			WebElement newrole = driver.findElement(By.xpath("//select[contains(@class,'form-select')]"));
			Select roleselect = new Select(newrole);
			roleselect.selectByIndex(1);
			Thread.sleep(2000);

			WebElement edit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit')]")));
			edit.click();

			Thread.sleep(2000);
			WebElement confirmYes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Yes')]")));
			confirmYes.click();

			Thread.sleep(2000);
			// Internal Confirm
			WebElement confirmOk = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Ok')]")));

			confirmOk.click();

		}
	}

	@Test(priority = 10)
	public void deleteOperator() throws Exception {

		try {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

		    for (WebElement row : rows) {
		        try {
		            WebElement mobileElement = row.findElement(By.xpath("./td[4]"));
		            String mobileNumber = mobileElement.getText().trim();

		            if (mobileNumber.equals("9741805403")) {

		                WebElement deleteButton = row.findElement(
		                        By.xpath(".//td[last()]//*[name()='svg' and contains(@class,'fa-trash')]"));

		                Thread.sleep(2000);
		                deleteButton.click();

		                WebElement reasonText = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter Reason')]"));
		                wait.until(ExpectedConditions.elementToBeClickable(reasonText));

		                reasonText.sendKeys("Test");
		                Thread.sleep(2000);

		                WebElement Yes = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
		                Yes.click();

		                WebElement Ok = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
		                Ok.click();
		            }
		        } catch (NoSuchElementException e) {
		            System.out.println("Element not found: " + e.getMessage());
		        } catch (Exception e) {
		            System.out.println("An unexpected error occurred: " + e.getMessage());
		        }
		    }
		} catch (Exception e) {
		    System.out.println("Error in execution: " + e.getMessage());
		}
	}

	@Test(priority = 11)
	public void searchOperator() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement roledrop = driver
				.findElement(By.xpath("//label[contains(text(), 'Role')]/following-sibling::select"));
		Select roleselect = new Select(roledrop);
		Thread.sleep(2000);
		roleselect.selectByIndex(1);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-magnifying-glass')]")).click();

	}

}
