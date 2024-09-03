package testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		// Configure ChromeOptions to handle downloads
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
		prefs.put("safebrowsing.enabled", "true");
		options.setExperimentalOption("prefs", prefs);

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

	@Test(priority = 8, enabled = true, testName = "Search_By_File_in_5S")
	public void search_By_File_in_5S() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "Search_By_File_in_5S");
		dm.searchFilein5S(driver, ".pdf");
		bm.logInfo(driver, "File viewed in viewer");

	}

	@Test(priority = 9, enabled = true, testName = "PDF file download in list view")
	public void fileDownloadin5S() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "PDF file download in list view");
		dm.fileDownloadInGridView(driver, ".pdf");
		bm.logInfo(driver, "PDF File Downloaded");

	}

	@Test(priority = 10, enabled = true, testName = "XLSX file download in Grid view")
	public void xlsxFileDownloadin5S() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "XLSX file download in Grid view");
		dm.fileDownloadInGridView(driver, ".xlsx");
		bm.logInfo(driver, "XLSX File Downloaded");

	}

	@Test(priority = 11, enabled = true, testName = "PPTX file download in Grid view")
	public void pptxFileDownloadin5S() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "PPTX file download in Grid view");
		dm.fileDownloadInGridView(driver, ".pptx");
		bm.logInfo(driver, "pptx File Downloaded");

	}

	@Test(priority = 12, enabled = true, testName = "DOCX file download in Grid view")
	public void DOCXFileDownloadin5S() throws InterruptedException
	{
		DrawingMangement dm = new DrawingMangement();
		bm.startTest(driver, "DOCX file download in Grid view");
		dm.fileDownloadInGridView(driver, ".docx");
		bm.logInfo(driver, "docx File Downloaded");

	}

	@AfterTest
	public void tearDown() throws InterruptedException, EmailException, IOException
	{

		bm.endreport(driver);
		bm.endTest(driver);
		bm.browserclose(driver);
		Desktop.getDesktop()
				.browse(new File("F:\\ScreenShotsAndExtentReport\\ExtentReport\\DegradeTestReport.html").toURI());
//		System.out.println("Browser closed and report generated");
//
//		MailWithAttachment mwa = new MailWithAttachment();
//		mwa.sendDegradeReport();

	}

}