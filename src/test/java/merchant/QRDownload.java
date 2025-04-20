package merchant;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
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

public class QRDownload extends BaseTest {

	@Test(priority = 14)
	public void qrMenu() throws InterruptedException {
		driver.findElement(By.linkText("Download QR")).click();
		
		 // Find all download buttons in the table
        List<WebElement> downloadButtons = driver.findElements(By.xpath("//*[name()='svg' and contains(@class, 'fa-download')]"));

        // Loop through and click each download button
        for (WebElement button : downloadButtons) {
            button.click();
            Thread.sleep(10000); // Wait for download to initiate
        }
	
	}
}
