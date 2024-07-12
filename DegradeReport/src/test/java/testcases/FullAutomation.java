package testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FullAutomation {

	ChromeDriver driver;
	JavascriptExecutor js;
	Actions act;

	// company login
	@BeforeSuite
	public void alfadock_companyLogin() throws InterruptedException {
		String compusername = "Atkgi";
		String password = "1234";
		String url = "https://www.alfadock-pack.com/";
		// WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().clearDriverCache().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		// url
		driver.get(url);
		System.out.println("The currrent url is : " + driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("The currrent Title is : " + driver.getTitle());
		// driver.manage().window().maximize();
		driver.get("https://www.alfadock-pack.com/");
		System.out.println("The currrent url is : " + driver.getCurrentUrl());
		Select sl = new Select(driver.findElement(By.xpath("//select[@id='mySelect']")));
		sl.selectByValue("English");
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys(compusername);
		Thread.sleep(1000);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("logmein")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@BeforeSuite
	public void userLogin() throws InterruptedException {
		String loginusername = "admin";
		String password = "admin";

		System.out.println("The currrent url is : " + driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("The currrent Title is : " + driver.getTitle());
		String title = driver.getTitle();
		String expectedTitle = "Company Login";
		if (title.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Title matched");
		} else {
			System.out.println("Not a match");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys(loginusername);
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	ExtentReports report;
	ExtentTest logger;

	@Test(enabled = true, priority = 1)
	public void searchPng() throws InterruptedException {
		System.out.println("search PNG file");
		Thread.sleep(2000);

		// report = new ExtentReports("./test-output/ExtentReport/alfaDOCK.html");
		report = new ExtentReports("F:\\RunnableJar\\ExtentReports\\Automation_Report.html");

		// Start the test
		logger = report.startTest("Upload_Files");

		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".png");
		logger.log(LogStatus.INFO, "Png entered in search bar");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// search button
		WebElement searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		logger.log(LogStatus.INFO, "PNG file searched");
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbutton);
		System.out.println("Clicked  search button");
		logger.log(LogStatus.INFO, "search button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='imageDiv'])[1]")));
		element.click();
		logger.log(LogStatus.INFO, "Result is displayed");
		System.out.println("Click the first displayed file ");
		// click the first displayed file
		// driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]")).click() ;
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		act = new Actions(driver);
		// double click the file
		WebElement doubleclicktoview = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		System.out.println("Double clicked the searched file");
		logger.log(LogStatus.INFO, "Double clicked the file");
		act.doubleClick(doubleclicktoview).build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		// switching windows
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// go to tab 2
		driver.switchTo().window(tabs2.get(1));
		logger.log(LogStatus.INFO, "PNG file viewed in viewer");
		logger.log(LogStatus.INFO, "switched to tab2");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.close();
		// go to tab 1
		driver.switchTo().window(tabs2.get(0));
		logger.log(LogStatus.INFO, "switched to tab1");
		// Go to homepage
		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		System.out.println("=====================================");
		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 2)
	public void searchBmf() throws InterruptedException {
		System.out.println("Search BMF file");
		// Start the test
		logger = report.startTest("Search BMF file");
		Thread.sleep(2000);
		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys("1812073-01.bmf");
		Thread.sleep(2000);
		System.out.println("file typed in search bar");
		logger.log(LogStatus.INFO, "file typed in search bar");
		// search button click
		WebElement searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbutton);
		Thread.sleep(2000);
		System.out.println("Search button clicked");
		logger.log(LogStatus.INFO, "Search button clicked");
		// finding the file and clicking first displayed file
		driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]")).click();
		// double clicking the file
		Thread.sleep(3000);
		System.out.println("file is searched");
		logger.log(LogStatus.INFO, "File is searched");
		// double clicking the file
		act = new Actions(driver);
		WebElement doubleclicktoview = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(doubleclicktoview).build().perform();
		Thread.sleep(5000);
		System.out.println("File is clicked");
		logger.log(LogStatus.INFO, "File is clicked");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// switching to tab 2
		driver.switchTo().window(tabs2.get(1));
		logger.log(LogStatus.INFO, "Switched to another tab (viewing in viewer)");
		Thread.sleep(10000);
		driver.close();
		// for switching to tab 1
		driver.switchTo().window(tabs2.get(0));
		logger.log(LogStatus.INFO, "Switched to tab 1");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfadock logo clicked");
		System.out.println("=================================");
		Thread.sleep(2000);

	}

	@Test(enabled = true, priority = 3)
	public void fileSearch() throws InterruptedException {
		System.out.println("File search");
		Thread.sleep(2000);

		logger = report.startTest("File search");
		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".pdf");
		System.out.println("file is typed in search bar");
		logger.log(LogStatus.INFO, "Filename is typed in search bar");
		Thread.sleep(2000);
		// search bar dropdown
		WebElement searchbutton = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchbutton);
		Thread.sleep(1000);
		System.out.println("dropdown button is clicked ");
		logger.log(LogStatus.INFO, "Dropdown button is clicked");

		// file checkbox
		WebElement filechckbx = driver.findElement(By.xpath("//label[text()='File']/preceding::div[1]"));
		System.out.println("file checkbox is selected");
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", filechckbx);
		System.out.println("file checkbox is clicked");
		logger.log(LogStatus.INFO, "File checkbox is clicked");

		// click search button
		WebElement searchfile = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchfile);
		Thread.sleep(10000);
		System.out.println("file is searched");
		logger.log(LogStatus.INFO, "File is searched");

		// Double click the first file
		act = new Actions(driver);
		WebElement doubleclicktoview = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(doubleclicktoview).build().perform();
		Thread.sleep(1000);
		System.out.println("double clicked the file");
		logger.log(LogStatus.INFO, "Double clicked the file");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
		logger.log(LogStatus.INFO, "Switched to tab1");
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(tab.get(0));
		logger.log(LogStatus.INFO, "Switched back to alfadock");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		System.out.println("========================================");
		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 4)
	public void folderSearch() throws InterruptedException {
		System.out.println("folder search ");
		logger = report.startTest("Folder search");
		Thread.sleep(2000);
		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys("test1_4");
		Thread.sleep(2000);
		System.out.println("file typed in search bar");
		logger.log(LogStatus.INFO, "File typed in search bar");
		// search bar dropdown
		WebElement searchbutton = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchbutton);
		Thread.sleep(2000);
		System.out.println("dropdown in search bar is clicked");
		logger.log(LogStatus.INFO, "Search bar dropdown is clicked");
		// for folder search
		WebElement folderchckbx = driver.findElement(By.xpath("//label[text()='Folder']/preceding::div[1]"));
		System.out.println("folder checkbox is selected");
		js.executeScript("arguments[0].click();", folderchckbx);
		System.out.println("folder checkbox is clicked");
		logger.log(LogStatus.INFO, "Folder checkbox is clicked");
		// click search button
		WebElement searchfile = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js.executeScript("arguments[0].click();", searchfile);
		Thread.sleep(10000);
		System.out.println("file is searched");
		logger.log(LogStatus.INFO, "File is searched");
		// Double click the folder
		act = new Actions(driver);
		WebElement doubleclicktoview = driver.findElement(By.xpath("(//div[@class='imageDivSmall'])[1]"));
		act.doubleClick(doubleclicktoview).build().perform();
		Thread.sleep(4000);
		System.out.println("file is selected");
		logger.log(LogStatus.INFO, "File is selected");
		// double click the file
		act = new Actions(driver);
		WebElement foldersearchfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(foldersearchfile).build().perform();
		Thread.sleep(1000);
		System.out.println("double clicked the file");
		logger.log(LogStatus.INFO, "Double clicked the file");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
		System.out.println("switched to tab 2");
		logger.log(LogStatus.INFO, "Switched to tab 2");
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(tab.get(0));
		System.out.println("switched to tab 1");
		logger.log(LogStatus.INFO, "Switched to tab 1");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		System.out.println("==============================");
		Thread.sleep(2000);

	}

	@Test(enabled = true, priority = 5)
	public void searchby_Filename() throws InterruptedException {
		System.out.println("=============search by filename=====================");
		Thread.sleep(2000);
		logger = report.startTest("search by filename");
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".pdf");
		Thread.sleep(2000);
		System.out.println("File entered in search bar");
		logger.log(LogStatus.INFO, "File entered in search bar");

		// search bar dropdown
		WebElement searchbutton = driver.findElement(By.xpath("//button[@icon='fa-caret-down']"));
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbutton);
		Thread.sleep(2000);
		System.out.println("dropdown clicked");
		logger.log(LogStatus.INFO, "Dropdown clicked");

		// search button click
		searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbutton);
		Thread.sleep(10000);
		System.out.println("Search button clicked");
		logger.log(LogStatus.INFO, "Search button clicked");

		// double click the file
		act = new Actions(driver);
		WebElement foldersearchfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		act.doubleClick(foldersearchfile).build().perform();
		System.out.println("double clicked the file");
		logger.log(LogStatus.INFO, "Double clicked the file");
		Thread.sleep(5000);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
		logger.log(LogStatus.INFO, "Switched to tab 1");
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(tab.get(0));
		logger.log(LogStatus.INFO, "Switched back to alfaDOCK");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//div[@class='ui-g-12 ui-md-3 ui-lg-3 ui-sm-12']/img[@src='assets/icons/logo.png']")).click();
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		System.out.println("====================================");
		Thread.sleep(2000);

	}

	@Test(enabled = true, priority = 6)
	public void fileRootLocation() throws InterruptedException {
		System.out.println("Root file location");
		logger = report.startTest("Root file location");
		logger.log(LogStatus.INFO, "Root File Location");
		Thread.sleep(2000);
		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".pdf");
		System.out.println("file is entered in search bar");
		logger.log(LogStatus.INFO, "File name entered in search bar");
		Thread.sleep(2000);

		// click search button
		WebElement searchfile = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchfile);
		System.out.println("file is searched");
		logger.log(LogStatus.INFO, "File is searched");
		Thread.sleep(10000);

		// folder one click
		WebElement selectfolder = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", selectfolder);
		System.out.println("folder is clicked");
		logger.log(LogStatus.INFO, "Folder clicked");
		Thread.sleep(2000);
		WebElement filelocation = driver.findElement(By.xpath("//div[@title='File Location']/img"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", filelocation);
		System.out.println("gone to root file location");
		logger.log(LogStatus.INFO, "Gone to root file location");
		Thread.sleep(4000);
		WebElement alfadocklogo = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", alfadocklogo);
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		System.out.println("alfadock logo clicked");
		System.out.println("==================================");
		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 7)
	public void filedownload() throws InterruptedException {
		Thread.sleep(2000);
		logger = report.startTest("File Download");
		// search bar
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".pdf");
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "FileName entered in search bar");
		// click search button
		WebElement searchfile = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchfile);
		Thread.sleep(4000);
		logger.log(LogStatus.INFO, "Search button clicked");

		// select file
		WebElement selectfile = driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", selectfile);
		logger.log(LogStatus.INFO, "File selected");
		Thread.sleep(2000);

		// downloadfile
		WebElement filedownload = driver.findElement(By.xpath("//div[@title='Download']/img"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", filedownload);
		logger.log(LogStatus.INFO, "Download button clicked");
		Thread.sleep(4000);
		WebElement alfadocklogo = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", alfadocklogo);
		System.out.println("alfadock logo clicked");
		logger.log(LogStatus.INFO, "alfaDOCK logo clicked");
		Thread.sleep(4000);
	}

	@Test(enabled = true, priority = 8)
	public void sps_Test() throws InterruptedException {
		Thread.sleep(3000);
		logger = report.startTest("SPS Test");

		WebElement drawingmanagement = driver
				.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png' and @class='homeItems1']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawingmanagement);
		Thread.sleep(2000);
		System.out.println("clicked drawing management");
		logger.log(LogStatus.INFO, "Clicked 5S");
		WebElement drawing5slabel = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing5slabel);
		Thread.sleep(2000);
		System.out.println("clicked 5s label");
		logger.log(LogStatus.INFO, "Clicked 5S label");
		driver.findElement(By.xpath(
				"//input[@class='ng-untouched ng-pristine ng-valid ui-inputtext ui-corner-all ui-state-default ui-widget']"))
				.sendKeys(".a3dasm");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// search button
		WebElement searchbutton = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbutton);
		System.out.println("Clicked  search button");
		logger.log(LogStatus.INFO, "search button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		WebElement firstfile = driver.findElement(By.xpath("//div[@class='imageDiv']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", firstfile);
		Thread.sleep(2000);
		System.out.println("first file clicked");
		logger.log(LogStatus.INFO, "First file clicked");
		WebElement spsicon = driver
				.findElement(By.xpath("//img[@src='assets/Image_Search64.png' and @class='ng-star-inserted']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", spsicon);
		System.out.println("clicked sps icon");
		logger.log(LogStatus.INFO, "Clicked SPS icon");

		Thread.sleep(10000);

		WebElement homeicon = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", homeicon);
		logger.log(LogStatus.INFO, "Clicked alfaDOCK homepage button");
		Thread.sleep(5000);
	}

	@Test(enabled = true, priority = 9)
	public void uploadfile_in_5s() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Start the test
		logger = report.startTest("Upload_Files");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(3000);
		logger.log(LogStatus.INFO, "5S clicked");
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing_management);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.log(LogStatus.INFO, "Drawing 5S");
		Thread.sleep(3000);
		WebElement upload_file = driver.findElement(By.id("uploadFile"));
		String filename = "testpdf09032023.pdf";
		upload_file.sendKeys("F:\\Murali - AlphaDock\\upload_files_automation\\" + filename);
		logger.log(LogStatus.INFO, "File uploaded");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("File uploaded");
		String foldername = filename.substring(0, filename.lastIndexOf('.'));
		System.out.println(foldername);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		act = new Actions(driver);
		act.moveToElement(driver
				.findElement(By.xpath("//label[text()='" + foldername + "']/preceding::div[@class='imageDivSmall']")))
				.doubleClick().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(3000);
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
				.perform();
		Thread.sleep(3000);
		System.out.println("File Double Clicked");
		logger.log(LogStatus.INFO, "File Doubled clicked");
		WebElement home = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		Thread.sleep(3000);
		System.out.println("home button clicked");
		logger.log(LogStatus.INFO, "Home Button Clicked");
		WebElement clickfolder = driver.findElement(By.xpath("//label[text()='testpdf09032023']/preceding::img[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clickfolder);
		Thread.sleep(3000);
		System.out.println("Folder clicked");
		logger.log(LogStatus.INFO, "Folder Clicked");
		WebElement deletebutton = driver.findElement(By.xpath("//img[@src='assets/ai-trash.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", deletebutton);
		Thread.sleep(2000);
		System.out.println("Delete button clicked");
		logger.log(LogStatus.INFO, "Delete button clicked");
		WebElement okbutton = driver
				.findElement(By.xpath("//span[@id='ui-dialog-3-label']/following::span[text()='OK'][1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", okbutton);
		Thread.sleep(3000);
		System.out.println("OK button clicked");
		logger.log(LogStatus.INFO, "OK button clicked");
		WebElement alfaDock_home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", alfaDock_home);
		System.out.println("Clicked Home");
		System.out.println("==================================================================================");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 10)
	public void search_fileby_filename_in_5s() throws InterruptedException {
		Thread.sleep(2000);
		logger = report.startTest("search_fileby_filenameorattribute_in_5s");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Clicked 5S");
		logger.log(LogStatus.INFO, "Clicked 5S");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing_management);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchbar.sendKeys(".pdf");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "Clicked search bar");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchoptions = driver.findElement(By.xpath("//button[@icon='fa-caret-down']//span[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchoptions);
		logger.log(LogStatus.INFO, "Clicked search options");
		WebElement attribute1 = driver.findElement(By.xpath("//label[text()='Attributes']/preceding::div[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", attribute1);
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", search);
		logger.log(LogStatus.INFO, "Clicked search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
				.perform();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Clicked home");
		Thread.sleep(5000);
	}

	@Test(enabled = true, priority = 11)
	public void search_fileby_attribute_in_5s() throws InterruptedException {
		logger = report.startTest("fileby_attribute_in_5s");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Clicked 5S");
		logger.log(LogStatus.INFO, "Clicked 5S");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing_management);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchbar.sendKeys("test123456");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		logger.log(LogStatus.INFO, "Clicked search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchoptions = driver.findElement(By.xpath("//button[@icon='fa-caret-down']//span[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchoptions);
		logger.log(LogStatus.INFO, "Clicked search options");
		Thread.sleep(2000);
		WebElement filename = driver.findElement(By.xpath("//label[text()='Filename']/preceding::span[1]"));
		logger.log(LogStatus.INFO, "Clicked filename");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", filename);
		Thread.sleep(2000);
		WebElement attribute = driver.findElement(By.xpath("//label[text()='Attributes']/preceding::div[1]"));
		logger.log(LogStatus.INFO, "Clicked attribute");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", attribute);
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		logger.log(LogStatus.INFO, "Clicked search bar");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", search);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDivSmall'])[1]"))).doubleClick().build()
				.perform();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
				.perform();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//img[@src=\"assets/icons/logo.png\"]")).click();
		logger.log(LogStatus.INFO, "Clicked home logo");
		Thread.sleep(5000);
	}

	@Test(enabled = true, priority = 12)
	public void download_of_Pdfandmsofficefilesin_GridView() throws InterruptedException {
		System.out.println("Download file in Grid view 5s");
		logger = report.startTest("Download file in Grid view 5s");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		logger.log(LogStatus.INFO, "Digital 5S");
		Thread.sleep(2000);
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing_management);

		// pdf file download

		System.out.println("==================================================");
		System.out.println("Drawing Management ");
		System.out.println("Download pdf file in Grid view");

		Thread.sleep(2000);
		WebElement searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);

		// for pdf download
		String pdffilename = ".pdf";
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(pdffilename);
		System.out.println(".pdf entered in search bar");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement clicksearch = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		logger.log(LogStatus.INFO, "pdf file searching");
		driver.findElement(By.xpath("//div[@class='imageDiv']")).click();
		Thread.sleep(2000);
		WebElement downloadbutton = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downloadbutton);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "pdf file downloaded");
		WebElement home = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);

		// xlsx file download
		Thread.sleep(2000);

		String xlsxfilename = ".xlsx";
		WebElement searchbar1 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar1);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(xlsxfilename);
		System.out.println(".xlsx entered in search bar");
		logger.log(LogStatus.INFO, "xlsx name entered in search bar");
		Thread.sleep(2000);
		WebElement clicksearch2 = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.log(LogStatus.INFO, "clicked search bar");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='imageDiv']")).click();
		logger.log(LogStatus.INFO, "File selected");
		Thread.sleep(2000);
		WebElement downloadbutton2 = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downloadbutton2);
		logger.log(LogStatus.INFO, "clicked download button");
		Thread.sleep(2000);
		WebElement home1 = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home1);

		// ppt file download
		Thread.sleep(2000);
		String pptxfilename = ".pptx";
		WebElement searchbar2 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(pptxfilename);
		System.out.println(".ppt entered in search bar");
		logger.log(LogStatus.INFO, "PPT entered in search bar");
		WebElement clicksearch3 = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch3);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.log(LogStatus.INFO, "Search button clicked");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='imageDiv']")).click();
		Thread.sleep(2000);
		WebElement downloadbutton4 = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downloadbutton4);
		logger.log(LogStatus.INFO, "Download button clicked");
		Thread.sleep(2000);
		WebElement home2 = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home2);
		logger.log(LogStatus.INFO, "Home button clicked");

		// docx file download
		Thread.sleep(2000);
		String docxfilename = ".docx";
		WebElement searchbar3 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar3);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(docxfilename);
		System.out.println(".docx entered in search bar");
		logger.log(LogStatus.INFO, "docx entered in search bar");
		Thread.sleep(2000);
		WebElement clicksearch4 = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch4);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='imageDiv']")).click();
		logger.log(LogStatus.INFO, "Search button clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		downloadbutton = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downloadbutton);
		logger.log(LogStatus.INFO, "Download icon clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);

	}

	@Test(enabled = true, priority = 13)
	public void download_of_Pdfandmsofficefiles_ListView() throws InterruptedException {

		// List View Download
		System.out.println("==================================================");
		System.out.println("Drawing Management ");
		System.out.println("Download pdf file in list view");
		logger = report.startTest("Download file in List view 5s");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		logger.log(LogStatus.INFO, "Digital 5S");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing_management);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement listview = driver.findElement(By.xpath("//img[@src='assets/ai-list.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", listview);
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "List view clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("clicked search bar");
		logger.log(LogStatus.INFO, "Search bar clicked");

		// for pdf download
		String pdffilename = ".pdf";
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(pdffilename);
		System.out.println(".pdf entered in search bar");
		logger.log(LogStatus.INFO, "pdf entered in search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement clicksearch = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch);
		System.out.println("Search button clicked ");
		logger.log(LogStatus.INFO, "Search button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click = driver.findElement(By.xpath("(//p[@class='lblfilename'])[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement downloadbutton = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downloadbutton);
		System.out.println("pdf file downloaded");
		logger.log(LogStatus.INFO, "Download button clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement home = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home Button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// xlsx file download
		Thread.sleep(2000);
		String xlsxfilename = ".xlsx";
		searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		logger.log(LogStatus.INFO, "Search bar clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(xlsxfilename);
		System.out.println(".xlsx entered in search bar");
		logger.log(LogStatus.INFO, "xlsx entered in search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement clicksearch2 = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.log(LogStatus.INFO, "Search button clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click1 = driver.findElement(By.xpath("(//p[@class='lblfilename'])[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click1);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement download = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", download);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("file downloaded");
		logger.log(LogStatus.INFO, "file downloaded");
		home = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// ppt file download
		Thread.sleep(2000);
		String pptxfilename = ".pptx";
		searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(pptxfilename);
		System.out.println(".pptx entered in search bar");
		logger.log(LogStatus.INFO, "pptx entered in search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		clicksearch = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click2 = driver.findElement(By.xpath("(//p[@class='lblfilename'])[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click2);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement download1 = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", download1);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("ppt file downloaded");
		logger.log(LogStatus.INFO, "file downloaded");
		home = driver.findElement(By.xpath("//span[@class='fa fa-home']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);

		// doc file download
		Thread.sleep(2000);
		String docxfilename = ".docx";
		searchbar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", searchbar);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(docxfilename);
		System.out.println(".docx entered in search bar");
		logger.log(LogStatus.INFO, "docx entered in search bar");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		clicksearch = driver.findElement(By.xpath("//img[@src='assets/ai-search.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clicksearch);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click3 = driver.findElement(By.xpath("//div[@class='ui-g-10']//p[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click3);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement download3 = driver.findElement(By.xpath("//img[@src='assets/ai-download.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", download3);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("docx file downloaded");
		logger.log(LogStatus.INFO, "docx file downloaded");
		WebElement gridview = driver.findElement(By.xpath("//img[@src='assets/ai-menu.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", gridview);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(enabled = true, priority = 14)
	public void pagination_View_Gridview_and_Listview() throws InterruptedException {
		logger = report.startTest("pagination_View_Gridview_and_Listview");
		System.out.println("pagination Grid View and List view");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement drawmanagement = driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawmanagement);
		logger.log(LogStatus.INFO, "5S Clicked in homepage");

		Thread.sleep(2000);
		WebElement drawing5s = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawing5s);
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "Home button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement paginator5s = driver.findElement(By.xpath(
				"(//div[@class='ui-dropdown-trigger ui-state-default ui-corner-right']/following::span[@class='ui-dropdown-trigger-icon ui-clickable fa fa-fw fa-caret-down'])[3]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", paginator5s);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(4000);
		WebElement click10 = driver.findElement(By.xpath("//span[text()='10']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click10);
		System.out.println("clicked 10");
		logger.log(LogStatus.INFO, "clicked 10");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click20 = driver.findElement(By.xpath("//span[text()='20']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click20);
		System.out.println("clicked 20");
		logger.log(LogStatus.INFO, "clicked 20");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click30 = driver.findElement(By.xpath("//span[text()='30']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click30);
		System.out.println("clicked 30");
		logger.log(LogStatus.INFO, "clicked 30");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement click100 = driver.findElement(By.xpath("//span[text()='100']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click100);
		System.out.println("clicked 100");
		logger.log(LogStatus.INFO, "clicked 100");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement listview = driver.findElement(By.xpath("//img[@src='assets/ai-list.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", listview);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("list view clicked");
		logger.log(LogStatus.INFO, "List view clicked");
		click10 = driver.findElement(By.xpath("//span[text()='10']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click10);
		System.out.println("clicked 10");
		logger.log(LogStatus.INFO, "clicked 10");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		click20 = driver.findElement(By.xpath("//span[text()='20']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click20);
		System.out.println("clicked 20");
		logger.log(LogStatus.INFO, "clicked 20");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		click30 = driver.findElement(By.xpath("//span[text()='30']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click30);
		System.out.println("clicked 30");
		logger.log(LogStatus.INFO, "clicked 30");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		click100 = driver.findElement(By.xpath("//span[text()='100']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", click100);
		System.out.println("clicked 100");
		logger.log(LogStatus.INFO, "clicked 100");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement gridview = driver.findElement(By.xpath("//img[@src='assets/ai-menu.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", gridview);
		System.out.println("clicked grid view");
		logger.log(LogStatus.INFO, "clicked Grid view");
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement mainpage = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", mainpage);
		System.out.println("clicked alfadock logo");
		logger.log(LogStatus.INFO, "clicked alfadock logo");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);

	}

	@Test(enabled = true, priority = 15)
	public void trash_files_in_5S() throws InterruptedException {
		// report = new
		// ExtentReports("F:\\RunnableJar\\ExtentReports\\Automation_Report.html");
		logger = report.startTest("trash_files_in_5S");

		System.out.println("==============================================");
		System.out.println("Trash files in 5s");
		logger.log(LogStatus.INFO, "Trash files in 5S");
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement trash = driver.findElement(By.xpath("//label[text()='Trash']"));
		System.out.println("clicked trash link");
		logger.log(LogStatus.INFO, "Clicked trash ");
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", trash);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDivSmall'])[1]"))).doubleClick().build()
				.perform();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv' or @class='imageDivSmall'])[1]")))
				.doubleClick().build().perform();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement alfaDock_home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", alfaDock_home);
		logger.log(LogStatus.INFO, "Home button clicked ");
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(enabled = true, priority = 16)
	public void sort_by_Name_Date_created_Modified_date_ascendingordescending() throws InterruptedException {
		WebElement uparrow;
		WebElement downarrow;
		System.out.println("asc_Or_Desc_ByName_CreatedDate_ModifiedDate");
		logger = report.startTest("asc_Or_Desc_ByName_CreatedDate_ModifiedDate");
		WebElement drawmanagement = driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawmanagement);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Assuming you have a WebElement representing the dropdown box
		Select dropdown = new Select(
				driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[3]/following::select[2]")));
		// Get all options in the dropdown box
		List<WebElement> options = dropdown.getOptions();
		// Get the count of options
		int optionCount = options.size();
		System.out.println("Number of options in the dropdown box: " + optionCount);
		logger.log(LogStatus.INFO, "Number of options in the dropdown box: " + optionCount);

		Select name = new Select(
				driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[3]/following::select[2]")));
		name.selectByValue("Name");
		uparrow = driver.findElement(By.xpath("//img[@src='assets/ai-uparrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", uparrow);
		Thread.sleep(2000);
		downarrow = driver.findElement(By.xpath("//img[@src='assets/ai-downarrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downarrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Select datecreated = new Select(
				driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[3]/following::select[2]")));
		datecreated.selectByValue("Date Created");
		uparrow = driver.findElement(By.xpath("//img[@src='assets/ai-uparrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", uparrow);
		Thread.sleep(2000);
		downarrow = driver.findElement(By.xpath("//img[@src='assets/ai-downarrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downarrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Select modifieddate = new Select(
				driver.findElement(By.xpath("(//img[@class='ng-star-inserted'])[3]/following::select[2]")));
		modifieddate.selectByValue("Modified Date");
		uparrow = driver.findElement(By.xpath("//img[@src='assets/ai-uparrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", uparrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		downarrow = driver.findElement(By.xpath("//img[@src='assets/ai-downarrow.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", downarrow);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("======================================================================");

	}

	@Test(enabled = true, priority = 17)
	public void placingPHDOrder() throws InterruptedException {
		System.out.println("==============Placing PHD order====================");
		logger = report.startTest("PHD order");
		WebElement drawingmanagement = driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawingmanagement);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("clicked drawing management");
		logger.log(LogStatus.INFO, "clicked drawing management");
		// clicking drawing management
		WebElement fispanel = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", fispanel);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// uploading file
		WebElement upload_file = driver.findElement(By.id("uploadFile"));
		String phdname = "PHDtest1.pdf";
		upload_file.sendKeys("F:\\Murali - AlphaDock\\upload_files_automation\\" + phdname);
		Thread.sleep(2000);
		System.out.println("file uploaded");
		logger.log(LogStatus.INFO, "file uploaded");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement phdfolderclick = driver.findElement(By.xpath("//label[text()='PHDtest1']/preceding::img[1]"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", phdfolderclick);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		System.out.println("file clicked");
		logger.log(LogStatus.INFO, "file clicked");
		WebElement phdorder = driver.findElement(By.xpath("//label[text()='Program Order']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", phdorder);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		System.out.println("PHD order clicked");
		logger.log(LogStatus.INFO, "PHD order clicked");
		WebElement clickphdradio = driver.findElement(By.xpath("//input[@id='3DPHDService' and @value='2']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clickphdradio);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.findElement(By.id("orderDescription")).sendKeys("testing purpose only");
		Thread.sleep(2000);
		System.out.println("commented testing purpose only");
		logger.log(LogStatus.INFO, "commented testing purpose only");
		WebElement submitorder = driver
				.findElement(By.xpath("//span[@class='ui-button-text ui-clickable' and text()='Submit Order']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", submitorder);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(3000);
		System.out.println("order submitted");
		logger.log(LogStatus.INFO, "order submitted");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(enabled = true, priority = 18)
	public void connectAndAirportTest() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("=====connect and airport");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger = report.startTest("connect and airport");
		WebElement drawingmanagement = driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", drawingmanagement);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// clicking drawing management
		WebElement fispanel = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", fispanel);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// uploading file
		String connectandairporttest = "ConnectAndAirportTest.pdf";
		WebElement upload_file = driver.findElement(By.id("uploadFile"));
		upload_file.sendKeys("F:\\Murali - AlphaDock\\upload_files_automation\\" + connectandairporttest);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(8000);
		System.out.println("file uploaded");
		logger.log(LogStatus.INFO, "File Uploaded ");
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		String foldername = connectandairporttest.substring(0, connectandairporttest.lastIndexOf('.'));
		System.out.println(foldername);
		WebElement clickfile = driver.findElement(
				By.xpath("//label[text()='ConnectAndAirportTest']/preceding::div[@class='imageDivSmall']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", clickfile);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		System.out.println("file clicked");
		logger.log(LogStatus.INFO, "File Clicked");
		WebElement connectpanel = driver.findElement(By.xpath("//label[@class='label' and text()='Connect']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", connectpanel);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.xpath(
				"//td[text()='murali@a-tkg.co.in' and @class='connectuser']/following::input[@value='964' and @name='sharedUsers[]']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", checkbox);
		driver.findElement(By.id("SharedMessage")).sendKeys("testing purpose only");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement connect = driver
				.findElement(By.xpath("//span[@class='ui-button-text ui-clickable' and text()='Connect']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", connect);
		logger.log(LogStatus.INFO, "Connect button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement aiairport = driver.findElement(By.xpath("//label[@class='label' and text()='ai Airport']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", aiairport);
		logger.log(LogStatus.INFO, "airport button clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement home = driver.findElement(By.xpath("*//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver.executeScript("arguments[0].click();", home);
		logger.log(LogStatus.INFO, "Home button clicked");
		Thread.sleep(3000);

	}

	@AfterSuite
	public void close() throws InterruptedException {
		report.endTest(logger);
		report.flush();
		Thread.sleep(3000);
		driver.quit();

//		// To send a mail without attachment
//		MultiPartEmail email = new MultiPartEmail();
//		email.setHostName("smtp.googlemail.com");
//		System.out.println("HostName : " + email.getHostName());
//		email.setSmtpPort(465);
//		System.out.println("PortName : " + email.getSmtpPort());
//		email.setAuthenticator(new DefaultAuthenticator("murali@a-tkg.co.in", "M1u2r3a4l5i6"));
//		email.setSSLOnConnect(true);
//		email.setFrom("murali@a-tkg.co.in");
//		System.out.println("FromAddress " + email.getFromAddress());
//		email.setSubject("TestMail");
//		System.out.println("Subject : " + email.getSubject());
//		email.setMsg("This is a test mail from Automation... :-)");
//		EmailAttachment attachment = new EmailAttachment();
//		System.out.println("Attachment done in mail ");
//		// attachment.setPath("F:\\EventReports\\Test.html");
//		attachment.setPath("F:\\RunnableJar\\ExtentReports\\Automation_Report.html");
//		email.addTo("murali@a-tkg.co.in");
//		System.out.println("ToAddresses : " + email.getToAddresses());
//		email.attach(attachment);
//		// email.addCc("rajesh@a-tkg.co.in");
//		email.send();
//		System.out.println("SentDate : " + email.getSentDate());
//		System.out.println("Mail sent");
//		Thread.sleep(3000);

	}

}
