package merchant;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
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

public class FAQs extends BaseTest {

	@Test(priority = 16)
	public void faqmenu() throws InterruptedException {
		driver.findElement(By.linkText("FAQs")).click();
		
		Set<String> windowhandles = driver.getWindowHandles(); 
		System.out.println(windowhandles);
		
		Iterator<String> iterate= windowhandles.iterator(); 
		String parent = iterate.next();
		String child = iterate.next(); 
	
	
		driver.switchTo().window(child); 
		
		System.out.println("FAQs"+driver.getCurrentUrl());
		
		AssertJUnit.assertTrue("Redirection Failed", (driver.getCurrentUrl().contains("faqs")));
		
		Thread.sleep(2000);
		
		driver.switchTo().window(parent); 
		
	}
	@Test(priority = 17)
	public void tutorialsmenu() throws InterruptedException {
		driver.findElement(By.linkText("Tutorials")).click();
		
		Set<String> windowhandles = driver.getWindowHandles(); 
		System.out.println(windowhandles);
		
		Iterator<String> iterate= windowhandles.iterator(); 
		String parent = iterate.next();
		String child = iterate.next(); 
		String child2 = iterate.next();
	
	
		driver.switchTo().window(child2); 
		
		System.out.println("Youtube Tutorials ="+driver.getCurrentUrl());
		
		AssertJUnit.assertTrue("Redirection Failed",(driver.getCurrentUrl().contains("youtube.com/@moco-digitalwallet9784")));
		
		Thread.sleep(2000);

		
	}
	

}
