package Maven.qait.HRISMavenProgram;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicHRIS {

	public WebDriver driver;

	//Login loginForm;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
	}

	@Test
	public void basicHRIS() {
		System.out.println("djhasjhfd");
		driver.get("https://hris.qainfotech.com/login.php");
		System.out.println("djhasjhfd");
		//driver.navigate().to("https://hris.qainfotech.com/login.php");
		// Assert.assertEquals("https://hris.qainfotech.com/login.php",
		// driver.getCurrentUrl(), "Strings are not matching");
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUserName")).sendKeys("shitijkhanna");
		driver.findElement(By.id("txtPassword")).sendKeys("Shitij@321#");
		System.out.println("Shitij");
		driver.findElement(By.xpath("//input[@class='btn pull-left']")).click();
		driver.navigate().to("https://hris.qainfotech.com:8086/time/timesheet");
		Assert.assertEquals("https://hris.qainfotech.com:8086/time/timesheet", driver.getCurrentUrl(),
				"Strings are not matching");

		// System.out.println("After clicking Basic courses");
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
