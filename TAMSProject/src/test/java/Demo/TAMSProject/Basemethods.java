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
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basemethods {

	@SuppressWarnings("deprecation")
	public void weblaunch(WebDriver driver) throws IOException, InterruptedException {
		WebDriverManager.chromedriver();
		Reporter.log( "Message", true );
		driver = new ChromeDriver();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		driver.get(prop.getProperty("HomeURL"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Homepage hmpg = new Homepage(driver);
		Assert.assertTrue(hmpg.logoimage().isDisplayed());
		Reporter.log("Web Launched and Website is loaded the logo image is displayed");
	}

	@SuppressWarnings("deprecation")
	public void zoomout75(WebDriver driver) throws AWTException {
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
		Reporter.log("The Browser is zoomed out to 75% for entire website visibility");
	}

	@SuppressWarnings("deprecation")
	public void login(WebDriver driver) throws Exception {
		Homepage hmpg = new Homepage(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		hmpg.loginlnk().click();
		hmpg.input1().sendKeys(prop.getProperty("correctusername1"));
		hmpg.input2().sendKeys(prop.getProperty("password1"));
		hmpg.loginbtn().click();
		TAMSHome thmp = new TAMSHome(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(thmp.loginheader().isDisplayed());
		Reporter.log("Login is succesful and User entered the TAMS Dashobaord");
	}

}
