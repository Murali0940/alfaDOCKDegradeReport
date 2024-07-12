package baseclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import basemethods.BaseMethods;



public class Complogin extends BaseMethods {

	WebDriverWait wait;
	WebDriver driver;

	public Complogin(WebDriver driver) {
		this.driver = driver;
	}

	By cname = By.id("username");
	By cpass = By.id("password");
	By clogin = By.id("logmein");

	public void setLanguage(WebDriver driver) throws InterruptedException {
		Select sl = new Select(driver.findElement(By.xpath("//select[@id='mySelect']")));
		sl.selectByValue("English");

	}

	public void setCname(WebDriver driver) throws InterruptedException {

		String compname = driver.findElement(By.id("companylogin")).getText();
		System.out.println(compname);
		Thread.sleep(2000);

		// Create WebDriverWait instance with a timeout of 10 seconds
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));

		driver.findElement(By.id("username")).sendKeys("Atkgi");

	}

	public void setCpass(WebDriver driver) {
		driver.findElement(By.id("password")).sendKeys("1234");

	}

	public UserLogin clogin(WebDriver driver) throws InterruptedException {

		driver.findElement(By.id("logmein")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return new UserLogin();

	}

	public void complogin_page_validate_url(WebDriver driver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String expectedURL = "https://www.alfadock-pack.com/userlogin.html";
		String actualURL = driver.getCurrentUrl();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Create WebDriverWait instance with a timeout of 20 seconds
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		Assert.assertEquals(expectedURL, actualURL);

		if (expectedURL.equals(actualURL)) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Login failed!");
		}

	}

}
