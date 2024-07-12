package testcases;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import basemethods.BaseMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestCases {

	public WebDriver driver;
	BaseMethods bm = new BaseMethods();
	WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws IOException {
		String path = "F:\\ScreenShotsAndExtentReport\\ExtentReport\\LoginTestReport.html";
		bm.setupExtentReport(driver, path);
		bm.startTest(driver, "driver_info");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("start-maximized");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		bm.driverinfo(driver);
		bm.logInfo(driver, "driver info verified");

	}

	@Test(enabled = true, priority = 1, testName = "comp_login_With_Valid_Credentials")
	public void loginWithValidCredentials() throws InterruptedException {

		bm.startTest(driver, "comp_login_With_Valid_Credentials");

		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.id("username"));

		// Enter username
		WebElement usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys("Atkgi");
		bm.logInfo(driver, "username entered");

		// Enter password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("1234");
		bm.logInfo(driver, "password entered");

		// Click login button
		WebElement loginButton = driver.findElement(By.id("logmein"));
		loginButton.click();
		bm.logInfo(driver, "login button clicked");
		bm.wait(driver, Duration.ofSeconds(20));
		bm.waitForElementToBeVisible(By.id("username"));
		Thread.sleep(3000);
		WebElement logoutbutton = driver.findElement(By.id("logout"));
		if (logoutbutton.isDisplayed() == true) {
			bm.logPASS(driver, "login sucessfull");
			logoutbutton.click();
			Thread.sleep(3000);
		} else {
			bm.logFAIL(driver, "login failed");
		}

	}

	@Test(enabled = true, priority = 2, testName = "comp_login_With_Invalid_Credentials")
	public void loginWithInValidCredentials() throws InterruptedException {

		bm.startTest(driver, "comp_login_With_Invalid_Credentials");

		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.id("username"));

		// Enter username
		WebElement usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys("Atkgi");
		bm.logInfo(driver, "username entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Enter password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("123");
		bm.logInfo(driver, "password entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Click login button
		WebElement loginButton = driver.findElement(By.id("logmein"));
		loginButton.click();
		bm.wait(driver, Duration.ofSeconds(20));
		bm.logInfo(driver, "login button clicked");
		bm.logInfo(driver, "popup displayed");
		Thread.sleep(3000);
		By dialogbox = By.xpath("//h4[text()='ログインエラー']/following::button[text()='OK' or @class='btn btn-default']");
		driver.findElement(dialogbox).click();
		bm.logInfo(driver, "popup closed");
		Thread.sleep(2000);

	}

	@Test(enabled = true, priority = 3, testName = "user_login_With_Invalid_Credentials")
	public void userLoginWithInvalidCredentials() throws InterruptedException {

		bm.startTest(driver, "user_login_With_Invalid_Credentials");

		Thread.sleep(2000);
		driver.navigate().refresh();

		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.id("username"));

		// Enter username
		WebElement usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys("Atkgi");
		bm.logInfo(driver, "comp login username entered");

		// Enter password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("1234");
		bm.logInfo(driver, "comp login password entered");

		// Click login button
		WebElement loginButton = driver.findElement(By.id("logmein"));
		loginButton.click();
		bm.logInfo(driver, "comp login login button clicked");
		bm.wait(driver, Duration.ofSeconds(20));
		bm.waitForElementToBeVisible(By.id("username"));
		Thread.sleep(3000);

		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.id("username"));

		// Enter username
		usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys("admi");
		bm.logInfo(driver, "user page username entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Enter password
		passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("admin");
		bm.logInfo(driver, "user page password entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Click login button
		loginButton = driver.findElement(By.id("login"));
		loginButton.click();
		bm.wait(driver, Duration.ofSeconds(20));
		bm.logInfo(driver, "user page login button clicked");
		bm.logInfo(driver, "popup displayed");
		Thread.sleep(3000);
		By dialogbox = By.xpath("//h4[text()='ログインエラー']/following::button[text()='OK' or @class='btn btn-default']");
		driver.findElement(dialogbox).click();
		bm.logInfo(driver, "popup closed");
		Thread.sleep(2000);

	}

	@Test(enabled = true, priority = 4, testName = "user_login_With_valid_Credentials")
	public void userLoginWithValidCredentials() throws InterruptedException {

		driver.navigate().refresh();
		Thread.sleep(2000);
		bm.startTest(driver, "user_login_With_valid_Credentials");

		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.id("username"));

		// Enter username
		WebElement usernameField = driver.findElement(By.id("username"));
		usernameField.sendKeys("admin");
		bm.logInfo(driver, "username entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Enter password
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("admin");
		bm.logInfo(driver, "password entered");
		bm.wait(driver, Duration.ofSeconds(5));

		// Click login button
		WebElement loginButton = driver.findElement(By.id("login"));
		loginButton.click();
		bm.wait(driver, Duration.ofSeconds(20));
		Thread.sleep(3000);
		bm.logInfo(driver, "login button clicked");
		String expectedURL = "https://www.alfadock-pack.com/ver10/#/home";
		String actualURL = driver.getCurrentUrl();
		Thread.sleep(3000);
		// Create WebDriverWait instance with a timeout of 10 seconds
		bm.wait(driver, Duration.ofSeconds(20));

		// Wait until the element with id "myButton" is clickable
		bm.waitForElementToBeClickable(By.xpath("//img[@src='assets/icons/Digital 5S.png']"));
		Assert.assertEquals(expectedURL, actualURL);

		if (expectedURL.equals(actualURL)) {
			System.out.println("Login successful!");
			bm.logPASS(driver, "Login successful!");
		} else {
			System.out.println("Login failed!");
			bm.logFAIL(driver, "Login failed!");
		}

	}

	@AfterSuite
	public void tearDown() throws InterruptedException, EmailException, IOException {
		bm.endTest(driver);
		bm.endreport(driver);
		bm.browserclose(driver);
		System.out.println("Browser closed and report generated");

		System.out.println("Mail sending started ");
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("murali@a-tkg.co.in", "M1u2r3a4l5i6"));
		email.setSSLOnConnect(true);
		email.setFrom("murali@a-tkg.co.in");
		email.setSubject("AlfaDOCK Automation Report");
		email.setMsg("Dear sir,\r\n" + "Please find the attachment below");
		EmailAttachment attachment = new EmailAttachment();
		System.out.println("Attachment done in mail ");
		attachment.setPath("F:\\ScreenShotsAndExtentReport\\ExtentReport\\LoginTestReport.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		email.addTo("murali@a-tkg.co.in");

		email.attach(attachment);
		email.send();
		System.out.println("Mail sent");
		System.out.println("-----------------------------------------------------");

//		MailWithAttachment mwa = new MailWithAttachment();
//		mwa.sendDegradeReport();
	}

}
