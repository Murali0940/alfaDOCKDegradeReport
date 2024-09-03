package baseclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import basemethods.BaseMethods;

public class DrawingMangement extends BaseMethods
{
	BaseMethods bm = new BaseMethods();
	JavascriptExecutor js;
	Actions act;
	public WebDriver driver;

	public void uploadfile_in_5s(WebDriver driver) throws InterruptedException
	{
		System.out.println("uploadfile_in_5s========================================");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(1000);
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drawing_management);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement upload_file = driver.findElement(By.id("uploadFile"));
		upload_file.sendKeys("F:\\Murali - AlphaDock\\upload_files_automation\\FileTest\\AT-TestPDF.pdf");

		bm.wait(driver, Duration.ofSeconds(30));
		Thread.sleep(60000);
		System.out.println("File uploaded");
		driver.navigate().refresh();
		bm.wait(driver, Duration.ofSeconds(30));
		By foldericon = By.xpath("//label[text()='AT-TestPDF']/preceding::div[@class='imageDivSmall']");
		WebElement folder = driver.findElement(foldericon);
		if (folder.isDisplayed() == true) {
			System.out.println("File is displayed");
			act = new Actions(driver);
			act.moveToElement(
					driver.findElement(By.xpath("//label[text()='AT-TestPDF']/preceding::div[@class='imageDivSmall']")))
					.doubleClick().build().perform();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Thread.sleep(2000);
			act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
					.perform();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} else {
			System.out.println("File is already uploaded");
		}

		bm.home_and_refresh(driver);
	}

	public String searchFilein5S(WebDriver driver, String name) throws InterruptedException
	{
		System.out.println("searchFilein5S========================================");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drawing_management);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);
		bm.wait(driver, Duration.ofSeconds(10));
		// search button
		WebElement searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));

		bm.wait(driver, Duration.ofSeconds(10));
		Thread.sleep(2000);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchbutton);
		System.out.println("Clicked  search button");
		Thread.sleep(40000);

		By firstfile = By.xpath("(//div[@class='imageDiv'])[1]");
		WebElement file = driver.findElement(firstfile);
		if (file.isDisplayed() == true) {
			System.out.println("file is displayed");
//			act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
//					.perform();
			// Perform the double-click using JavaScript Executor in a single line
			WebElement ffile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
			((JavascriptExecutor) driver).executeScript(
					"var event = new MouseEvent('dblclick', {view: window, bubbles: true, cancelable: true}); arguments[0].dispatchEvent(event);",
					ffile);

			bm.wait(driver, Duration.ofSeconds(30));
			System.out.println("file double clicked");
		} else {
			System.out.println("file is not displayed");
		}

		bm.home_and_refresh(driver);

		return name;

	}

	public String fileDownloadInGridView(WebDriver driver, String filename) throws InterruptedException
	{
		System.out.println("Office file conversion in 5s");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(2000);
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drawing_management);

		// pdf file download

		System.out.println("==================================================");
		System.out.println("Drawing Management ");
		System.out.println("Download pdf file in Grid view");

		Thread.sleep(2000);
		WebElement searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);

		// For File download
		String name = filename;
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(name);
		System.out.println("Filename entered in search bar");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		WebElement clicksearch = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", clicksearch);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='imageDiv']")).click();
		Thread.sleep(2000);
		WebElement downloadbutton = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadbutton);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		bm.home_and_refresh(driver);

		return filename;

	}

}
