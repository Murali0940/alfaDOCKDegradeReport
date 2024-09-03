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
import baseclasses.TestFile;
import baseclasses.UserLogin;
import basemethods.BaseMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass
{

	public WebDriver driver;
	BaseMethods bm = new BaseMethods();
	Complogin clogin;
	UserLogin ulogin;
	Homepage homepage;
	DrawingMangement dm;
	TestFile tf;

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
		tf.sample(driver, ".png");
		bm.logInfo(driver, "PNG file viewed");

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
