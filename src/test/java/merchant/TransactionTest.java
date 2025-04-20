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

public class TransactionTest extends BaseTest

{

	@BeforeClass
	public void transactionmenu() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.linkText("Transactions")).click();
	}

	@Test(priority = 12)
	public void transaction() throws InterruptedException {
		searchTxn();
		Thread.sleep(2000);
		downloadTxn();
		Thread.sleep(2000);
		printTxn();

	}

	public void searchTxn() throws InterruptedException {
		dateSelect();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement search = driver
				.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-magnifying-glass')]"));
		wait.until(ExpectedConditions.visibilityOf(search));
		search.click();

	}

	public void downloadTxn() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement down = driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-download')]"));
		wait.until(ExpectedConditions.visibilityOf(down));
		Thread.sleep(2000);
		down.click();

	}

	public void printTxn() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[contains(@class, 'even-row')]")));
		WebElement print = driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-print')]"));
		Thread.sleep(2000);
		print.click();

	}
}
