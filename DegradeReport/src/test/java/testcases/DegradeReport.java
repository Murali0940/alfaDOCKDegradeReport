package testcases;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseclasses.Complogin;
import baseclasses.DrawingMangement;
import baseclasses.Homepage;
import baseclasses.UserLogin;
import basemethods.BaseMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DegradeReport
{

	public WebDriver driver;
	BaseMethods bm = new BaseMethods();
	Complogin clogin;
	UserLogin ulogin;
	Homepage homepage;
	DrawingMangement dm;

	@BeforeTest
	public void driverinfo() throws IOException, InterruptedException
	{

		String path = "F:\\ScreenShotsAndExtentReport\\ExtentReport\\DegradeTestReport.html";
		bm.setupExtentReport(driver, path);
		bm.startTest(driver, "driver_info");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("start-maximized");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		bm.driverinfo(driver);
		bm.logInfo(driver, "driver info verified");

		bm.startTest(driver, "comp_login_Page");
		clogin = new Complogin(driver);
		clogin.setLanguage(driver);
		clogin.setCname(driver);
		System.out.println("Compname entered");
		bm.logInfo(driver, "username entered in complogin");
		clogin.setCpass(driver);
		bm.logInfo(driver, "password entered in complogin");
		clogin.clogin(driver);
		bm.logInfo(driver, "complogin button clicked");
		clogin.complogin_page_validate_url(driver);

		bm.startTest(driver, "User_Login_page");
		ulogin = new UserLogin();
		ulogin.userPageValidate(driver);
		ulogin.setUname(driver);
		bm.logInfo(driver, "username entered");
		ulogin.setUpass(driver);
		bm.logInfo(driver, "password entered");
		ulogin.ulogin(driver);
		bm.logInfo(driver, "login successfull");
		ulogin.userlogin_page_validate_url(driver);
		bm.logInfo(driver, "userpage validated");

	}

	@Test(priority = 1, enabled = true, testName = "searchPNG_in_Homepage")
	public void searchpng() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "searchPNG");
		homepage.searchBar(driver, ".png");
		bm.logInfo(driver, "PNG file viewed");

	}

	@Test(priority = 2, enabled = true, testName = "searchBMF_in_Homepage")
	public void searchBMF() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "searchBMF");
		homepage.searchBar(driver, ".bmf");
		bm.logInfo(driver, "BMF file viewed");

	}

	@Test(priority = 3, enabled = true, testName = "Search_File_using_Searchbar_FilenameAttributes")
	public void search_File_using_FilenameAttribute() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "search File using FilenameAttribute");
		homepage.Search_For_File_Using_Attributes(driver, ".pdf");
		bm.logInfo(driver, "File is searched with attributes using Filename Checkbox");

	}

	@Test(priority = 4, enabled = true, testName = "Search_File_using_Searchbar_with_Filename and AttributeSearch")
	public void search_File_using_AttributeSearch() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "search File using FilenameAttribute");
		homepage.Search_For_File_Using_AttributeSearch(driver, ".pdf");
		bm.logInfo(driver, "File is searched with attributes using Attribute Checkbox");

	}

	@Test(priority = 5, enabled = true, testName = "Search_Folder_using_Searchbar_with_Filename and AttributeSearch")
	public void search_Folder_using_AttributeSearch() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "search Folder using AttributeSearch");
		homepage.folder_Search(driver, ".pdf");
		bm.logInfo(driver, "Folder is searched with attributes using Attribute Checkbox");

	}

	@Test(priority = 6, enabled = true, testName = "File-Root_Location")
	public void file_Root_Location() throws InterruptedException
	{
		homepage = new Homepage();
		bm.startTest(driver, "File_root_location");
		homepage.search_File_Click_RootIcon(driver, ".pdf");
		bm.logInfo(driver, "File Root location searched and found the location");

	}

	@Test(priority = 7, enabled = true, testName = "upload File in 5S")
	public void upload_file_in_5s() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "upload File in 5S");
		dm.uploadfile_in_5s(driver);
		bm.logInfo(driver, "File uploaded sucessfully in 5S");

	}

	@AfterTest
	public void tearDown() throws InterruptedException, EmailException
	{

		bm.endreport(driver);
		bm.endTest(driver);
		bm.browserclose(driver);
//		System.out.println("Browser closed and report generated");
//
//		MailWithAttachment mwa = new MailWithAttachment();
//		mwa.sendDegradeReport();

	}

}