package Demo.TAMSProject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Trail {
	WebDriver driver;

	@SuppressWarnings("deprecation")
	@Test(priority = 1, groups = {"Login"})
	public void weblaunch() throws IOException, InterruptedException {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		driver.get(prop.getProperty("HomeURL"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Homepage hmpg = new Homepage(driver);
		Assert.assertTrue(hmpg.logoimage().isDisplayed());	
		System.out.println("LOGO Image is visible");
		}

	@SuppressWarnings("deprecation")
	@Test(priority = 2, groups = {"Login"})
	public void zoomout75() throws AWTException {
		Homepage hmpg = new Homepage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hmpg.logoimage().click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(hmpg.navigationlink2()));
		Robot robot = new Robot();
		for (int i = 0; i < 3; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		Assert.assertTrue(hmpg.navigationlink7().isDisplayed());
		System.out.println("Zoomed out to 75% and now Login is visible");
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 3)
	public void homepageverification() throws IOException, Exception {
		Homepage hmpg = new Homepage(driver);
		hmpg.logoimage().click();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		hmpg.navigationlink1().click();
		Assert.assertEquals(prop.getProperty("Homenavlink1"), hmpg.navigationlink1().getText());
		hmpg.navigationlink2().click();
		Assert.assertEquals(prop.getProperty("Homenavlink2"), hmpg.navigationlink2().getText());
		hmpg.navigationlink3().click();
		Assert.assertEquals(prop.getProperty("Homenavlink3"), hmpg.navigationlink3().getText());
		hmpg.navigationlink4().click();
		Assert.assertEquals(prop.getProperty("Homenavlink4"), hmpg.navigationlink4().getText());
		hmpg.navigationlink5().click();
		Assert.assertEquals(prop.getProperty("Homenavlink5"), hmpg.navigationlink5().getText());
		hmpg.navigationlink6().click();
		Assert.assertEquals(prop.getProperty("Homenavlink6"), hmpg.navigationlink6().getText());

		// Assert.assertEquals(prop.getProperty("navlink7"),
		// hmpg.navigationlink7().getText());
		hmpg.navigationlink7().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(hmpg.loginclsbtn().isDisplayed());
		hmpg.loginclsbtn().click();
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 4)
	public void loginvalidation() throws Exception {
		Homepage hmpg = new Homepage(driver);
		hmpg.logoimage().click();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		Assert.assertTrue(hmpg.input1().isDisplayed());
		// Wrong case1 - Wrong User name , Correct Password
		hmpg.loginlnk().click();
		hmpg.input1().sendKeys(prop.getProperty("username2"));
		hmpg.input2().sendKeys(prop.getProperty("password1"));
		hmpg.loginbtn().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(hmpg.errormsg1().isDisplayed());
		hmpg.loginclsbtn().click();
		// Wrong case2 - Wrong User name, Wrong Password
		hmpg.loginlnk().click();
		hmpg.input1().sendKeys(prop.getProperty("username2"));
		hmpg.input2().sendKeys(prop.getProperty("password4"));
		hmpg.loginbtn().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(hmpg.errormsg1().isDisplayed());
		hmpg.loginclsbtn().click();
		/*
		 * // Wrong case3 - Correct User name, Wrong Password hmpg.loginlnk().click();
		 * hmpg.input1().sendKeys(prop.getProperty("correctusername1"));
		 * hmpg.input2().sendKeys(prop.getProperty("password2"));
		 * hmpg.loginbtn().click(); driver.manage().timeouts().implicitlyWait(20,
		 * TimeUnit.SECONDS); Assert.assertTrue(hmpg.errormsg1().isDisplayed());
		 * hmpg.loginclsbtn().click();
		 */
		// Correct case
		hmpg.loginlnk().click();
		hmpg.input1().sendKeys(prop.getProperty("correctusername1"));
		hmpg.input2().sendKeys(prop.getProperty("password1"));
		hmpg.loginbtn().click();
		TAMSHome thmp = new TAMSHome(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(thmp.loginheader().isDisplayed());
		thmp.logout().click();
	}
	@Test(priority=5)
	public void closure() {
		driver.quit();
	}
}
