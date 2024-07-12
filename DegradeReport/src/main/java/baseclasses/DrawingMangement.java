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

	JavascriptExecutor js;
	Actions act;

	public void uploadfile_in_5s(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.xpath("//img[@src='assets/icons/Digital 5S.png']")).click();
		Thread.sleep(1000);
		WebElement drawing_management = driver.findElement(By.xpath("//label[text()='Drawings 5S']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drawing_management);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		WebElement upload_file = driver.findElement(By.id("uploadFile"));
		upload_file.sendKeys("F:\\Murali - AlphaDock\\upload_files_automation\\testpdf.pdf");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("File uploaded");
		act = new Actions(driver);
		act.moveToElement(
				driver.findElement(By.xpath("//label[text()='testpdf']/preceding::div[@class='imageDivSmall']")))
				.doubleClick().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='imageDiv'])[1]"))).doubleClick().build()
				.perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		System.out.println("File Double Clicked");
		WebElement alfaDock_home = driver.findElement(By.xpath("//img[@src='assets/icons/logo.png']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", alfaDock_home);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
	}

}
