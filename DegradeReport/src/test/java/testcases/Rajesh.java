package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Rajesh
{

	public static void main(String[] args) throws InterruptedException
	{

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.get("https://www.alfadock-pack.com/");
		WebElement dropdown = driver.findElement(By.id("mySelect"));
		Select selectObject = new Select(dropdown);
		selectObject.selectByValue("English");
		driver.findElement(By.id("username")).sendKeys("interntest");
		driver.findElement(By.id("password")).sendKeys("alfatkg789");
		Thread.sleep(2000);
		driver.findElement(By.id("logmein")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys("Rajesh");
		driver.findElement(By.id("password")).sendKeys("rajesh123");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		WebElement drawingmanagementicon = driver
				.findElement(By.xpath("//input[@src='assets/icons/hyperLink.png']/following::img[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", drawingmanagementicon);
		Thread.sleep(2000);
		WebElement searchicon = driver.findElement(By.xpath("//div[@id='filtergroup']/input[@placeholder='Search']"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", searchicon);
		// searchicon.sendKeys("JPFH-A4900-NM+JPFH-A4590-NM.a3dasm");
		searchicon.sendKeys(".a3dasm");

		WebElement searchicon1 = driver.findElement(By.xpath("(//span[@class='ui-button-text ui-clickable'])[1]"));
		JavascriptExecutor js8 = (JavascriptExecutor) driver;
		js8.executeScript("arguments[0].click();", searchicon1);

		Thread.sleep(2000);

		WebElement searchicon2 = driver
				.findElement(By.xpath("(//div[@class='ui-dropdown-trigger ui-state-default ui-corner-right'])[3]"));
		JavascriptExecutor js9 = (JavascriptExecutor) driver;
		js9.executeScript("arguments[0].click();", searchicon2);

		Thread.sleep(2000);

		WebElement searchicon3 = driver.findElement(By.xpath("//span[text()='Last 30 Days']"));
		// WebElement searchicon3 = driver.findElement(By.xpath("//span[text()='Last 7
		// Days']"));
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		js10.executeScript("arguments[0].click();", searchicon3);

		js8.executeScript("arguments[0].click();", searchicon1);

		searchicon.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		boolean displayed = driver.findElement(By.xpath("//label[text()='Go to']")).isDisplayed();

		System.out.println(displayed);

		WebElement type = driver.findElement(By.xpath("(//label[text()='Go to']/following::input)[1]"));
		type.clear();
		type.sendKeys("4");
		Thread.sleep(2000);
		js10.executeScript("arguments[0].click();", type);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}

}
