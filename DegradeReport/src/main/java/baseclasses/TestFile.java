package baseclasses;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import basemethods.BaseMethods;

public class TestFile extends BaseMethods
{

	BaseMethods bm;
	Actions act;

	public String sample(WebDriver driver, String name) throws InterruptedException
	{
		// bm.startTest(driver, "searchPNG");
		bm.wait(driver, Duration.ofSeconds(10));

		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);
		bm.logInfo(driver, "name entered in search bar");

		bm.wait(driver, Duration.ofSeconds(10));
		// search button
		WebElement searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));

		bm.wait(driver, Duration.ofSeconds(10));
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchbutton);
		System.out.println("Clicked  search button");
		bm.logInfo(driver, "Clicked  search button");
		// bm.logInfo(driver, "Clicked search button");
		bm.wait(driver, Duration.ofSeconds(30));
		bm.waitForElementToBeClickable(By.xpath("(//div[@class='imageDiv'])[1]"));

		// double click
		act = new Actions(driver);
		By file = By.xpath("(//div[@class='imageDiv'])[1]");
		act.doubleClick(driver.findElement(file)).build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		bm.wait(driver, Duration.ofSeconds(30));
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		System.out.println(driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.close();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().window(tabs.get(0));

		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return name;

	}

}
