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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TAMSLogin {
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
	}
	@SuppressWarnings("deprecation")
	@Test(priority =3)
	public void login() throws Exception {
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
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority=4)
	public void TAMSNavbarVerification() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		TAMSHome thmp = new TAMSHome(driver);
		thmp.anchorbutton1().click();
		wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton2()));
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDashboardURL") );
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton1"), thmp.anchorbutton1().getText());
		Reporter.log("-- The TAMS Navigation Bar 1st button is verified to be : "+prop.getProperty("TAMSanchorbutton1"));
		//thmp.anchorbutton2().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton3()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton2"), thmp.anchorbutton2().getText());
		Reporter.log("-- The TAMS Navigation Bar 2nd button is verified to be : "+prop.getProperty("TAMSanchorbutton2"));
		//thmp.anchorbutton3().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton4()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton3"), thmp.anchorbutton3().getText());
		Reporter.log("-- The TAMS Navigation Bar 3rd button is verified to be : "+prop.getProperty("TAMSanchorbutton3"));
		//thmp.anchorbutton4().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton5()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton4"), thmp.anchorbutton4().getText());
		Reporter.log("-- The TAMS Navigation Bar 4th button is verified to be : "+prop.getProperty("TAMSanchorbutton4"));
		//thmp.anchorbutton5().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton6()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton5"), thmp.anchorbutton5().getText());
		Reporter.log("-- The TAMS Navigation Bar 5th button is verified to be : "+prop.getProperty("TAMSanchorbutton5"));
		//thmp.anchorbutton6().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton7()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton6"), thmp.anchorbutton6().getText());
		Reporter.log("-- The TAMS Navigation Bar 6th button is verified to be : "+prop.getProperty("TAMSanchorbutton6"));
		//thmp.anchorbutton7().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton8()));
		Assert.assertEquals(prop.getProperty("TAMSanchorbutton7"), thmp.anchorbutton7().getText());
		Reporter.log("-- The TAMS Navigation Bar 7th button is verified to be : "+prop.getProperty("TAMSanchorbutton7"));
		//thmp.anchorbutton8().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton9()));
		Assert.assertTrue(thmp.anchorbutton8().getText().contains(prop.getProperty("TAMSanchorbutton8")));
		Reporter.log("-- The TAMS Navigation Bar 8th button is verified to be : "+prop.getProperty("TAMSanchorbutton8"));
		//thmp.anchorbutton9().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton10()));
		Assert.assertTrue(thmp.anchorbutton9().getText().contains(prop.getProperty("TAMSanchorbutton9")));
		Reporter.log("-- The TAMS Navigation Bar 9th button is verified to be : "+prop.getProperty("TAMSanchorbutton9"));
		//thmp.anchorbutton10().click();
		//wait.until(ExpectedConditions.elementToBeClickable(thmp.anchorbutton1()));
		Assert.assertTrue(thmp.anchorbutton10().getText().contains(prop.getProperty("TAMSanchorbutton10")));
		Reporter.log("-- The TAMS Navigation Bar 10th button is verified to be : "+prop.getProperty("TAMSanchorbutton10"));
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority=5)
	public void TAMSUserVerification() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);	
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		TAMSHome thmp = new TAMSHome(driver);
		thmp.anchorbutton1().click();
		Assert.assertTrue(thmp.loginheader().isDisplayed());
		Reporter.log("-- TAMS Login Header is Displayed --");
		Assert.assertTrue(thmp.headerleftimg().isDisplayed());
		Reporter.log("-- User Data in the Header : "+(thmp.userdataheader().getText()));
		Assert.assertTrue(thmp.userdataheader().getText().contains(prop.getProperty("correctusername1")));
		Reporter.log("-- User Data Header is cross verified with the given user--");
		Actions act = new Actions(driver);
		act.moveToElement(thmp.helpicon());
		Assert.assertTrue(thmp.helpicon().isDisplayed());
		Reporter.log("-- Help Icon is Displayed--");
		act.moveToElement(thmp.pwdchngicon());
		Assert.assertTrue(thmp.pwdchngicon().isDisplayed());
		Reporter.log("-- Password Change Icon is Displayed--");
		act.moveToElement(thmp.logout());
		Assert.assertTrue(thmp.pwdchngicon().isDisplayed());
		thmp.logout().click();
	}
	@Test(priority=6)
	public void closure() {
		driver.quit();
	}

}
