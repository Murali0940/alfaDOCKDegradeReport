package baseclasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import basemethods.BaseMethods;

public class Homepage extends BaseMethods
{

	BaseMethods bm = new BaseMethods();
	JavascriptExecutor js;
	Actions act;

	public String searchBar(WebDriver driver, String name) throws InterruptedException
	{

		System.out.println("search PNG========================================");
		bm.wait(driver, Duration.ofSeconds(10));

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

	public String Search_For_File_Using_Attributes(WebDriver driver, String name) throws InterruptedException
	{

		System.out.println("Search_For_File_Using_Attributes========================================");
		bm.wait(driver, Duration.ofSeconds(10));

		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement downarrow = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", downarrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		By Filenamecheckbox = By.xpath("//input[type='checkbox' or @id='sf' or @name='group1' and @value='Filename']");
		WebElement filecheckbox = driver.findElement((Filenamecheckbox));

		By Filecheckbox = By.xpath("//label[text()='Filename']/preceding::span[1]");
		WebElement fileattributecheckbox = driver.findElement((Filecheckbox));

		Thread.sleep(1000);

		// For File checkbox
		if (filecheckbox.isSelected() == true) {
			System.out.println("File checkbox is not selected");

		} else {
			System.out.println("File checkbox is not selected now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fileattributecheckbox);

		}
		Thread.sleep(1000);

		// For Attribute checkbox
		By attributechkbox = By.xpath("// label[text()='Attributes']/preceding::div[1]");
		WebElement attributecheckbox = driver.findElement((attributechkbox));

		if (attributecheckbox.isSelected() == false) {
			System.out.println(" attribute checkbox is not selected");
		} else {
			System.out.println(" attribute checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", attributecheckbox);
		}
		Thread.sleep(1000);

		// For content checkbox
		By contentchkbox = By.xpath("//label[text()='Content']/preceding::div[1]");
		WebElement contentcheckbox = driver.findElement((contentchkbox));

		if (contentcheckbox.isSelected() == false) {
			System.out.println("content attribute checkbox is not selected");
		} else {
			System.out.println("content attribute checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", contentcheckbox);
		}

		Thread.sleep(1000);

		// For File checkbox
		By Filechkbox = By.xpath("//label[text()='File']/preceding::div[1]");
		WebElement filecheckbox1 = driver.findElement((Filechkbox));

		if (filecheckbox1.isSelected() == false) {
			System.out.println("File checkbox is not selected");
		} else {
			System.out.println("File checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", filecheckbox1);
		}

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//img[@src='assets/ai-search.png']")));
		Thread.sleep(2000);
		WebElement firstfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));

		if (firstfile.isDisplayed()) {
			System.out.println("File is displayed");
		} else {
			System.out.println("File is not displayed");
		}

		act = new Actions(driver);
		WebElement firstfile1 = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(firstfile1).build().perform();
		bm.wait(driver, Duration.ofSeconds(10));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		System.out.println(driver.getCurrentUrl());

//		By download = By.xpath("//img[@src='assets/ai-download.png']");
		By download = By.xpath("//img[@src='assets/pdf/images/toolbarButton-print@2x.png']");
		WebElement downloadbutton = driver.findElement(download);

		if (downloadbutton.isDisplayed() == true)
			System.out.println("download button is displayed");
		else
			System.out.println("download button is not displayed");

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

	public String Search_For_File_Using_AttributeSearch(WebDriver driver, String name) throws InterruptedException
	{

		System.out.println("Search_For_File_Using_AttributeSearch========================================");
		bm.wait(driver, Duration.ofSeconds(10));

		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement downarrow = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", downarrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		By Filenamecheckbox = By.xpath("//input[type='checkbox' or @id='sf' or @name='group1' and @value='Filename']");
		WebElement filecheckbox = driver.findElement((Filenamecheckbox));

		By Filecheckbox = By.xpath("//label[text()='Filename']/preceding::span[1]");
		WebElement fileattributecheckbox = driver.findElement((Filecheckbox));

		Thread.sleep(1000);

		// For File checkbox
		if (filecheckbox.isSelected() == true) {
			System.out.println("File checkbox is not selected");

		} else {
			System.out.println("File checkbox is not selected now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fileattributecheckbox);

		}
		// Thread.sleep(1000);

		// For Attribute checkbox
		By attributechkbox = By.xpath("// label[text()='Attributes']/preceding::div[1]");
		WebElement attributecheckbox = driver.findElement((attributechkbox));

		if (attributecheckbox.isSelected() == true) {
			System.out.println(" attribute checkbox is not selected");
		} else {
			System.out.println(" attribute checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", attributecheckbox);
		}
		// Thread.sleep(1000);

		// For content checkbox
		By contentchkbox = By.xpath("//label[text()='Content']/preceding::div[1]");
		WebElement contentcheckbox = driver.findElement((contentchkbox));

		if (contentcheckbox.isSelected() == false) {
			System.out.println("content attribute checkbox is not selected");
		} else {
			System.out.println("content attribute checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", contentcheckbox);
		}

		Thread.sleep(2000);

		// For File checkbox
		By Filechkbox = By.xpath("//label[text()='File']/preceding::div[1]");
		WebElement filecheckbox1 = driver.findElement((Filechkbox));

		if (filecheckbox1.isSelected() == true) {
			System.out.println("File checkbox is not selected");
		} else {
			System.out.println("File checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", filecheckbox1);
		}

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//img[@src='assets/ai-search.png']")));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

//		WebElement firstfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));

		for (int i = 0; i >= 100; i++) {
			WebElement firstfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[" + i + "]"));

			if (firstfile.isDisplayed() == true) {
				System.out.println("File is displayed");
				act.doubleClick(firstfile).build().perform();
			} else {
				System.out.println("File is not displayed");
			}
		}

		act = new Actions(driver);
		WebElement firstfile1 = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(firstfile1).build().perform();
		bm.wait(driver, Duration.ofSeconds(10));
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		System.out.println(driver.getCurrentUrl());

//		By download = By.xpath("//img[@src='assets/ai-download.png']");
		By download = By.xpath("//img[@src='assets/pdf/images/toolbarButton-print@2x.png']");
		WebElement downloadbutton = driver.findElement(download);

		if (downloadbutton.isDisplayed() == true)
			System.out.println("download button is displayed");
		else
			System.out.println("download button is not displayed");

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

	public String folder_Search(WebDriver driver, String name) throws InterruptedException
	{
		System.out.println("folder_Search========================================");
		Thread.sleep(2000);
		bm.wait(driver, Duration.ofSeconds(10));

		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);

		Thread.sleep(2000);
		System.out.println("file typed in search bar");
		// search bar dropdown
		WebElement searchbutton = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchbutton);
		System.out.println("dropdown in search bar is clicked");

		// for folder search
		WebElement folderchckbx = driver.findElement(By.xpath("//label[text()='Folder']/preceding::div[1]"));
		System.out.println("folder checkbox is selected");

		Thread.sleep(1000);
		// For File checkbox
		By Filechkbox = By.xpath("//label[text()='File']/preceding::div[1]");
		WebElement filecheckbox1 = driver.findElement((Filechkbox));
		if (filecheckbox1.isSelected() == true) {
			System.out.println("File checkbox is now selected");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", filecheckbox1);
		} else {
			System.out.println("File checkbox is not selected");

		}
		Thread.sleep(1000);
		if (folderchckbx.isSelected() == true) {
			System.out.println("folder checkbox is already selected.");
		} else {

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", folderchckbx);
			System.out.println("folder checkbox is clicked");
		}

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//img[@src='assets/ai-search.png']")));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		for (int i = 0; i >= 100; i++) {
			WebElement firstfile = driver.findElement(By.xpath("(//div[@class='imageDivSmall'])[" + i + "]"));

			if (firstfile.isDisplayed() == true) {
				System.out.println("File is displayed");
				act.doubleClick(firstfile).build().perform();
				Thread.sleep(10000);
			} else {
				System.out.println("File is not displayed");
			}
		}
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement folderclick = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act = new Actions(driver);
		act.doubleClick(folderclick).build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		System.out.println(driver.getCurrentUrl());

		By download = By.xpath("//img[@src='assets/pdf/images/toolbarButton-print@2x.png']");
		WebElement downloadbutton = driver.findElement(download);

		if (downloadbutton.isDisplayed() == true)
			System.out.println("download button is displayed");
		else
			System.out.println("download button is not displayed");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		driver.close();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().window(tabs.get(0));

		bm.home_and_refresh(driver);

		return name;
	}

	public void search_File_Click_RootIcon(WebDriver driver, String name) throws InterruptedException
	{
		System.out.println("search_File_Click_RootIcon========================================");
		Thread.sleep(3000);
		bm.wait(driver, Duration.ofSeconds(20));
		By searchbar = By.xpath(
				"//img[@src='assets/qr-code.png']/following::input[@placeholder='検索' or @placeholder='Search' and @class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']");

		// search bar
		driver.findElement(searchbar).sendKeys(name);
		Thread.sleep(2000);
		System.out.println("file typed in search bar");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//img[@src='assets/ai-search.png']")));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);

		List<WebElement> firstfile = driver.findElements(By.xpath("//div[@class='imageDiv']"));
		for (WebElement file : firstfile) {
			file = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
			file.click();
			break;
		}
		Thread.sleep(3000);
		By root = By.xpath("(//img[@src='assets/ai-list.png']/following::img)[2]");
		WebElement rooticon = driver.findElement(root);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", rooticon);
		bm.wait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		By docklogo = By.xpath("//img[@src='assets/icons/logo.png']");
		WebElement logo = driver.findElement(docklogo);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", logo);
		bm.wait(driver, Duration.ofSeconds(30));

	}

}
