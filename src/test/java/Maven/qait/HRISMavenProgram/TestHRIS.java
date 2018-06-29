package Maven.qait.HRISMavenProgram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.tap.hristest.LoginHRIS;

public class TestHRIS {

	public WebDriver driver;
	LoginHRIS loginForm;

	Properties prop = new Properties();
	FileInputStream fileInput;
	String user_name;
	String incorrect_username;
	String incorrect_password;
	String password;

	/*
	 * @BeforeClass public void beforeClass() { driver = new ChromeDriver(); }
	 */
	@BeforeClass
	public void Login() {
		driver = new ChromeDriver();
		driver.get("https://hris.qainfotech.com/login.php");

		System.out.println("After Click");
		driver.manage().window().maximize();
		
		try {
			fileInput = new FileInputStream("src/main/resources/dataFile.properties");
			try {
				prop.load(fileInput);
				user_name = prop.getProperty("username");
				incorrect_username = prop.getProperty("incorrectusername");
				incorrect_password = prop.getProperty("incorrectpassword");
				password = prop.getProperty("correctpassword");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Test(priority = 1) public void linkHit() {
	 * driver.navigate().to("https://hris.qainfotech.com/login.php");
	 * driver.findElement(By.xpath("//a[@href='#panel1']")).click();
	 * System.out.println("Click on the HRIS Login URL"); }
	 */

	@Test(priority = 1)
	public void enterCorrectCredentials() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		driver.findElement(By.id("txtUserName")).sendKeys(user_name);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.xpath("//input[@class='btn pull-left']")).click();
		driver.findElement(By.xpath("//a[@href='https://hris.qainfotech.com:8086/time/timesheet']")).click();
		// driver.navigate().to("https://hris.qainfotech.com:8086/time/timesheet");
		Assert.assertEquals("https://hris.qainfotech.com:8086/time/timesheet", driver.getCurrentUrl(),
				"Strings are not matching");
		driver.findElement(By.xpath("//a[@class='profile-btn']//img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='https://hris.qainfotech.com:8086/user/logoff']")).click();

	}

	@Test(priority = 2)
	public void bothIncorrectUsernameAndPassword() {
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		driver.findElement(By.id("txtUserName")).sendKeys(incorrect_username);
		driver.findElement(By.id("txtPassword")).sendKeys(incorrect_password);
		driver.findElement(By.xpath("//input[@class='btn pull-left']")).click();
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		System.out.println("Text: " + driver.findElement(By.cssSelector(".alert-error")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-error")).getText().equals("Invalid Login"));
		
	}

	@Test(priority = 3)
	public void CorrectUsernameButIncorrectPassword() {
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		driver.findElement(By.id("txtUserName")).sendKeys(user_name);
		driver.findElement(By.id("txtPassword")).sendKeys(incorrect_password);
		driver.findElement(By.xpath("//input[@class='btn pull-left']")).click();
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-error")).getText().equals("Invalid Login"));
		
	}

	@Test(priority = 4)
	public void IncorrectUsernameButCorrectPassword() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		driver.findElement(By.id("txtUserName")).sendKeys(incorrect_username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.xpath("//input[@class='btn pull-left']")).click();
		driver.findElement(By.xpath("//a[@href='#panel1']")).click();
		System.out.println(driver.findElement(By.cssSelector(".alert-error")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-error")).getText().equals("Invalid Login"));
		Thread.sleep(2000);
		  //WebDriverWait wait = new WebDriverWait(driver, 10);
		/*
		 * Assert.assertEquals(driver.findElement(By.cssSelector(".alert-error")).
		 * getText().compareTo("Invalid Login"), true);
		 */
	}

	@Test(priority = 5)
	public void AboutHRIS() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel2\"]/div")).isDisplayed());
		Thread.sleep(2000);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 6)
	public void Celebration() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel3']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel3\"]")).isDisplayed());
		 Thread.sleep(2000);
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 7)
	public void QAITUpdates() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel4']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel4\"]")).isDisplayed());
		Thread.sleep(2000);
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 8)
	public void HRPolicy() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel5']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel5\"]")).isDisplayed());
		Thread.sleep(2000);
		  //WebDriverWait wait = new WebDriverWait(driver, 10);
	}

	@Test(priority = 9)
	public void FoodMenu() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel7']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel7\"]")).isDisplayed());
		Thread.sleep(2000);
		 //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 10)
	public void DidYouKnow() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#panel6']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"panel6\"]")).isDisplayed());
		Thread.sleep(2000);
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
