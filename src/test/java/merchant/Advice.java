package merchant;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class Advice extends BaseTest {

	@BeforeClass
	public void adviceMenu() throws InterruptedException {
		driver.findElement(By.linkText("Advice")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 13)
	public void searchAndDownAdv() throws InterruptedException {
		dateSelect(); 
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement view = driver.findElement(By.xpath("//button[contains(text(),'View')]"));
		wait.until(ExpectedConditions.elementToBeClickable(view));
		view.click(); 
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//thead[contains(@class, 'vue3-easy-data-table__header')]"))); 
		WebElement down = driver.findElement(By.xpath("//button[contains(text(),'Download All')]"));
		down.click(); 
		Thread.sleep(2000);

	}
	
	
}
