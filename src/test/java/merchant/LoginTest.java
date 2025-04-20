package merchant;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	public static String browser = "chrome"; // External Configuration- XLS, CSV
    protected WebDriver driver;
   
    
    @BeforeMethod
    public void loginsetup() {
		if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.get("https://mps0.moco.com.np/?#/login");
		
    }

	@Test(priority=0)
	public void ValidLoginTest() throws InterruptedException {
		driver.findElement(By.id("login_user")).sendKeys("9818487677");
		Thread.sleep(2000); 
		driver.findElement(By.id("login_password")).sendKeys("123456");
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div/div/form/div[3]/div/button")).click();
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1)); 
	    wait.until(ExpectedConditions.urlContains("dashboard"));
	   Thread.sleep(2000); 
	    AssertJUnit.assertTrue( "Login failed!", driver.getCurrentUrl().toLowerCase().contains("dashboard"));
	

	}

	@Test(priority=1)
	public void testInvalidLogin() throws InterruptedException {
		driver.findElement(By.id("login_user")).sendKeys("9843455891");
		Thread.sleep(2000); 
		driver.findElement(By.id("login_password")).sendKeys("123456");
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div/div/form/div[3]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		WebElement errorMsg = driver.findElement(By.xpath("//*[@id='app']/div[2]/div"));
		AssertJUnit.assertTrue( "Error mesAssertge not shown for invalid login!",errorMsg.isDisplayed());
		System.out.println("✅ TC_02: Login failed with incorrect credentials as expected");
		Thread.sleep(2000); 
	}

	@Test(priority=2)
	public void testForgotPINRedirection() throws InterruptedException {
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\'reset\']")).click();
		driver.findElement(By.id("login_user")).sendKeys("9843455891");
		Thread.sleep(2000); 
		driver.findElement(By.id("login_user")).sendKeys("9843455891");
		Thread.sleep(2000); 
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    wait.until(ExpectedConditions.urlContains("reset"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 AssertJUnit.assertTrue("Redirection failed!",driver.getCurrentUrl().toLowerCase().contains("reset"));
		  System.out.println("✅ TC_03: Forgot PIN redirection successful");
		  Thread.sleep(2000); 
		
		
	}

	
	@AfterMethod
	public void teardown() {
	driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		 if (driver!= null) {
		        driver.quit();
		    } else {
		        System.out.println("WebDriver is null, skipping quit()");
		    }
	}
}
