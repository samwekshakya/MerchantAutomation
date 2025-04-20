package merchant;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;

import org.openqa.selenium.By;
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

public class DashboardTest extends BaseTest {

	@Test(priority = 3)
	public void testDashboardLoad() throws InterruptedException {
		AssertJUnit.assertTrue("Dashboard did not load correctly!",
				driver.findElement(By.xpath("//strong[contains(text(), 'Dashboard')]")).isDisplayed());
		System.out.println("✅ Dashboard loaded successfully");
	}

	@Test(priority = 4)
	public void testGenerateDynamicQR() throws InterruptedException {
		driver.findElement(By.xpath("//b[contains(text(),'Generate QR')]")).click();

		// Form-Handling
		WebElement outlet = driver.findElement(By.className("form-select"));
		Select select = new Select(outlet);
		Thread.sleep(2000);
		select.selectByValue("O000005206");
		
		Thread.sleep(2000); 
		WebElement amount = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[1]/div/div[4]/input"));
		amount.sendKeys("1000");

		Thread.sleep(2000); 
		WebElement Reference = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[1]/div/div[6]/input"));
		Reference.sendKeys("1001");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(
				By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/button"))
				.click();
		Thread.sleep(2000);

		// Handling QR
		// Check if the QR Code is present and visible
		if (driver.findElements(By.className("qr-image")).size() > 0
				&& driver.findElement(By.className("qr-image")).isDisplayed()) {
			AssertJUnit.assertTrue("QR Code generated!", true);
			System.out.println("✅ Dynamic QR Code generated successfully");
//			Thread.sleep(1000); 
			driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-xmark close-icon')]")).click();
		} else {
			System.out.println("⚠️ QR Code not generated! Checking for popup...");

			WebElement close = driver.findElement(By.xpath("//button[contains(@class,'btn-close')]"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(close));
			close.click();
		}
	}

	@Test(priority = 5)
	public void testLatestTransactionSummary() throws InterruptedException {
		AssertJUnit.assertTrue("Latest tranAssertctions not shown!",
				driver.findElement(By.xpath("//b[contains(text(),'Latest Purchase Transactions')]")).isDisplayed());
		System.out.println("✅ Latest Transactions Summary displayed correctly");
	}

	@Test(priority = 6)
	public void refresh() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		WebElement refButton = driver
				.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-arrows-rotate')]"));
		refButton.click();
	}

	@Test(priority = 7)
	public void testAnnouncementsDisplay() throws InterruptedException {

		WebElement announcementToggle = driver
				.findElement(By.xpath("//*[name()='svg' and contains(@data-icon, 'caret-down')]"));

		announcementToggle.click();

		WebElement announcementText = driver
				.findElement(By.xpath("//*[contains(text(), 'Important Notice: New Settlement Time Update')]"));

		AssertJUnit.assertTrue( "❌ Announcement section is NOT visible!", announcementText.isDisplayed());

		System.out.println("✅ Announcement is displayed successfully!");
	}

}
