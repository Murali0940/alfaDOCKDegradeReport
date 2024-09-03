package basemethods;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseMethods
{

	public WebDriver driver;
	public ExtentTest test;
	public ExtentReports reports;
	WebDriverWait wait;

	// driver info
	public void driverinfo(WebDriver driver)
	{

		String URL = "https://www.alfadock-pack.com/";
		driver.get(URL);

	}

	public int waitkaro(int sec)
	{
		new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(sec)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return sec;
	}

	public void browserclose(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}

	// extent report path
	public String setupExtentReport(WebDriver driver, String path) throws IOException
	{
		// path = "F:\\ScreenShotsAndExtentReport\\ExtentReport\\LoginTestReport.html";
		reports = new ExtentReports(path, true);
		return path;

	}

	// extent report start test
	public void startTest(WebDriver driver, String testName)
	{
		test = reports.startTest(testName);

	}

	// extent report logStatus.INFO
	public void logInfo(WebDriver driver, String message)
	{
		test.log(LogStatus.INFO, message);

	}

	// extent report logStatus.PASS
	public void logPASS(WebDriver driver, String message)
	{
		test.log(LogStatus.PASS, message);

	}

	// extent report logStatus.FAIL
	public void logFAIL(WebDriver driver, String message)
	{
		test.log(LogStatus.FAIL, message);

	}

	// extent report end test
	public void endTest(WebDriver driver)
	{
		reports.endTest(test);
	}

	// extent report end and generate the report.
	public void endreport(WebDriver driver)
	{
		reports.flush();
	}

	public Duration wait(WebDriver driver, Duration i)
	{
		wait = new WebDriverWait(driver, i); // Initialize WebDriverWait with a timeout of 10 seconds
		return i;
	}

	public WebElement waitForElementToBeClickable(By locator)
	{
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeVisible(By locator)
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForVibilityOfElement(WebElement element)
	{
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void home_and_refresh(WebDriver driver) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		WebElement logo = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", logo);
		System.out.println("alfadock logo clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

}
