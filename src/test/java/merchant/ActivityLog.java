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

public class ActivityLog extends BaseTest {

	@BeforeClass
	public void activitymenu() {
		driver.findElement(By.linkText("Activity Log")).click();
	}

	@Test(priority = 15)
	public void search() throws InterruptedException {

		WebElement operatordrop = driver
				.findElement(By.xpath("//label[contains(text(), 'Operator')]/following-sibling::select"));
		Select operatorselect = new Select(operatordrop);
		operatorselect.selectByValue("9818487677");
		Thread.sleep(2000);

		WebElement sectiondrop = driver
				.findElement(By.xpath("//label[contains(text(), 'Section')]/following-sibling::select"));
		Select sectionselect = new Select(sectiondrop);
		sectionselect.selectByVisibleText("Operator");

		Thread.sleep(2000);
		WebElement operationdrop = driver
				.findElement(By.xpath("//label[contains(text(), 'Operation')]/following-sibling::select"));
		Select operationselect = new Select(operationdrop);
		operationselect.selectByValue("1004");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[name()='svg' and contains(@class, 'fa-magnifying-glass')]")).click();
		Thread.sleep(2000);

	}

}
