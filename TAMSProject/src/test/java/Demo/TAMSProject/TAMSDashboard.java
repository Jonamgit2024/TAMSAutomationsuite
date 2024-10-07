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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TAMSDashboard {
	WebDriver driver;

	@SuppressWarnings("deprecation")
	@Test(priority = 1, groups = {"Login"})
	public void weblaunch() throws IOException, InterruptedException {
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
		Reporter.log("The Browser is zoomed out to 75% for entire website visibility");
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
		Reporter.log("Login is succesful and User entered the TAMS Dashobaord");
	}
	
	@Test(priority =4)
	public void dashboardvalidation() throws Exception {
		Reporter.log( "Message", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDashboardURL") );
		
		//card-blocks verification
		Assert.assertTrue(tmdb.dashboardcardblock1header().getText().contains(prop.getProperty("dashboardcardblock1header")));
		Reporter.log("Card Block 1 is verified with :"+tmdb.dashboardcardblock1header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock2header().getText().contains(prop.getProperty("dashboardcardblock2header")));
		Reporter.log("Card Block 2 is verified with :"+tmdb.dashboardcardblock2header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock3header().getText().contains(prop.getProperty("dashboardcardblock3header")));
		Reporter.log("Card Block 3 is verified with :"+tmdb.dashboardcardblock3header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock4header().getText().contains(prop.getProperty("dashboardcardblock4header")));
		Reporter.log("Card Block 4 is verified with :"+tmdb.dashboardcardblock4header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock5header().getText().contains(prop.getProperty("dashboardcardblock5header")));
		Reporter.log("Card Block 5 is verified with :"+tmdb.dashboardcardblock5header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock6header().getText().contains(prop.getProperty("dashboardcardblock6header")));
		Reporter.log("Card Block 6 is verified with :"+tmdb.dashboardcardblock6header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock7header().getText().contains(prop.getProperty("dashboardcardblock7header")));
		Reporter.log("Card Block 7 is verified with :"+tmdb.dashboardcardblock7header().getText());
		Assert.assertTrue(tmdb.dashboardcardblock8header().getText().contains(prop.getProperty("dashboardcardblock8header")));
		Reporter.log("Card Block 8 is verified with :"+tmdb.dashboardcardblock8header().getText());

		//legend-text verification
		Assert.assertEquals(prop.getProperty("dashboardLegendText"), tmdb.legendtext().getText());
		Reporter.log("Dash Board Legend Text is verified with :"+tmdb.legendtext().getText());
		
		//Agent, Airlines, Data - range visibility and its inputs verification
		Assert.assertTrue(tmdb.SelectbarfollowingAgent().isDisplayed());
		Reporter.log("Select Bar presence following Agent text is verified" );
		Assert.assertTrue(tmdb.SelectbarfollowingAirlines().isDisplayed());
		Reporter.log("Select Bar presence following Airlines text is verified" );
		Assert.assertTrue(tmdb.DateRangeText().isDisplayed());
		
		//Data-range input and text verification
		Reporter.log("Presence of Date Range text is verified" );
		Assert.assertTrue(tmdb.DateRangeInput().isDisplayed());
		Reporter.log("Presence of Input following the Date Range text is verified" );
		Reporter.log("The Base Structure is verified" );
	}
	@SuppressWarnings("deprecation")
	@Test(priority =5)
	public void Agent_Airline_Daterange_SelectionsValidation() throws Exception {
		Reporter.log( "Message", true );
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		//Switch case for different users
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		String Default_dockets = tmdb.Dockets_count().getText();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Agent_name Selection
		String Agent_name=null;
		Agent_name=prop.getProperty("Agent_Selection");
		Reporter.log("Agent selected is :"+Agent_name+"space check");
		tmdb.AgentSelectionbutton().click();
		Select slct1 = new Select(tmdb.AgentSelectionbutton());
		//Failed by Visible Text from 24thSeptember Passed on 23rd September
		//slct1.selectByVisibleText(Agent_name);
		slct1.selectByValue("1");
		
		//Airline_name Selection
		String Airline_name = null;
		Airline_name = prop.getProperty("Airline_Selection");
		tmdb.AirlineSelectionbutton().click();
		Select slct2 = new Select(tmdb.AirlineSelectionbutton());
		slct2.selectByVisibleText(Airline_name);
		
		//Selecting Date Range input
		String Data_Range = null;
		Data_Range = prop.getProperty("Date_Range");
		tmdb.DateRangeInput().click();
		Actions act = new Actions(driver);
		switch(Data_Range){
			case "Today":
				act.moveToElement(tmdb.Date_Range_Selection_Today()).click(tmdb.Date_Range_Selection_Today()).build().perform();
				break;
			case "Last 7 days":
				act.moveToElement(tmdb.Date_Range_Selection_Last7days()).click(tmdb.Date_Range_Selection_Last7days()).build().perform();
				break;
			case "Month to date":
				act.moveToElement(tmdb.Date_Range_Selection_Monthtodate()).click(tmdb.Date_Range_Selection_Monthtodate()).build().perform();
				break;
			case "Year to date":
				act.moveToElement(tmdb.Date_Range_Selection_Yeartodate()).click(tmdb.Date_Range_Selection_Yeartodate()).build().perform();
				break;
			case "The Previous Month":
				act.moveToElement(tmdb.Date_Range_Selection_Previousmonth()).click(tmdb.Date_Range_Selection_Previousmonth()).build().perform();
				break;
			case "Manual":
				act.moveToElement(tmdb.DateRangeInput()).click(tmdb.DateRangeInput()).build().perform();
				tmdb.DateRangeInput().sendKeys((prop.getProperty("Date_Range_Manual")));
				break;
			default:
				System.out.println("If you want to enter the specific data- Data range options select manual and enter it manually in Data_Range_input manually");
				
		}
		
		//Show Results and Reset button 
		tmdb.ShowResultsbutton().click();
		Thread.sleep(5000);//Thread sleep is used for the user interface to refresh and check
		String Current_dockets = tmdb.Dockets_count().getText();
		Reporter.log("The Default Dockets are"+Default_dockets+"Dockets count after the selections is "+Current_dockets);
		//Assuming there will be a change in Dockets count with selection
		SoftAssert sftasrt = new SoftAssert();
		sftasrt.assertNotEquals(Default_dockets, Current_dockets,"In case of both Agent and Airline is kept --select-- the asserion might fail");
		tmdb.Resetbutton().click();
		Thread.sleep(5000);//Thread sleep is used for the user interface to check
		String Current_docketsreset = tmdb.Dockets_count().getText();
		Reporter.log("The Default Dockets are"+Default_dockets+"Dockets count after the reset is "+Current_docketsreset);
		Assert.assertEquals(Default_dockets, Current_docketsreset);
		Thread.sleep(5000);//Thread sleep is used for the user interface to refresh and check
	}
	@Test(priority =6)
	public void cardblockswithSelectionsValidation() throws Exception {
		Reporter.log( "Message", true );
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		//Switch case for different users
		Reporter.log("Card Blocks verification started");
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		
		//Agent_name Selection
		String Agent_name1=null;
		Agent_name1=prop.getProperty("Agent_Selection1");
		Reporter.log("Agent selected is :"+Agent_name1+"space check");
		tmdb.AgentSelectionbutton().click();
		Select slct3 = new Select(tmdb.AgentSelectionbutton());
		//slct3.selectByVisibleText(Agent_name1);
		slct3.selectByValue("1");
		
		//Airline_name Selection
		String Airline_name1= null;
		Airline_name1 = prop.getProperty("Airline_Selection1");
		tmdb.AirlineSelectionbutton().click();
		Select slct4 = new Select(tmdb.AirlineSelectionbutton());
		slct4.selectByVisibleText(Airline_name1);
		tmdb.ShowResultsbutton().click();
		Thread.sleep(5000);//Thread sleep is used for the user interface to refresh and check
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//Clicking Dockets cardblock
		tmdb.Dockets_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle1"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle1"));
		tmdb.Flexbox_close().click();
		Dockettableverification(driver);
		
		//Clicking Contacts cardblock
		tmdb.Contactinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle2"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle2"));	
		tmdb.Flexbox_close().click();
		Contactstableverification(driver);
		
		//Clicking Passengers cardblock
		tmdb.passengerinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle3"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle3"));	
		tmdb.Flexbox_close().click();
		Passengerstableverification(driver);
		
		//Clicking CostPrice cardblock
		tmdb.costpriceinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle1"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle1"));	
		tmdb.Flexbox_close().click();
		Dockettableverification(driver);
		
		//Clicking Saleprice cardblock
		tmdb.salepriceinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle1"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle1"));	
		tmdb.Flexbox_close().click();
		Dockettableverification(driver);
		
		//Clicking Commission cardblock
		tmdb.commissioninfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle1"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle1"));	
		tmdb.Flexbox_close().click();
		Dockettableverification(driver);
		
		//Clicking PaymentsDone cardblock
		tmdb.paymentdone_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle4"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle4"));	
		tmdb.Flexbox_close().click();
		tmdb.paymentdone_count().click();
		Paymentstableverification(driver);
		
		//Clicking PendingPayments cardblock
		tmdb.pendingpayment_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.FlexboxHeader().getText(),prop.getProperty("Flexboxtitle5"));
		Reporter.log("Expected : "+tmdb.FlexboxHeader().getText()+" Actual : "+prop.getProperty("Flexboxtitle5"));	
		tmdb.Flexbox_close().click();
		tmdb.pendingpayment_count().click();
		Paymentstableverification(driver);
	}
	
	public static void Dockettableverification(WebDriver driver) throws Exception {
		
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Dockets Table Verification
		tmdb.Dockets_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.tableheaders().get(0).getText(),prop.getProperty("DocketFlextableheader0"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(0).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader0"));
		
		Assert.assertEquals(tmdb.tableheaders().get(1).getText(),prop.getProperty("DocketFlextableheader1"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(1).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader1"));
		Assert.assertEquals(tmdb.tablesearchplaceholder().get(0).getAttribute("placeholder"),prop.getProperty("DocketFlextableheader1"));
		Reporter.log("--  Dockets TablesearchPlaceHolder is : "+tmdb.tablesearchplaceholder().get(0).getAttribute("placeholder")+" Expected is : "+prop.getProperty("DocketFlextableheader1"));
		
		Assert.assertEquals(tmdb.tableheaders().get(2).getText(),prop.getProperty("DocketFlextableheader2"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(2).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader2"));
		Assert.assertEquals(tmdb.tableheaders().get(3).getText(),prop.getProperty("DocketFlextableheader3"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(3).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader3"));
		Assert.assertEquals(tmdb.tableheaders().get(4).getText(),prop.getProperty("DocketFlextableheader4"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(4).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader4"));
		Assert.assertEquals(tmdb.tableheaders().get(5).getText(),prop.getProperty("DocketFlextableheader5"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(5).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader5"));
		Assert.assertEquals(tmdb.tableheaders().get(6).getText(),prop.getProperty("DocketFlextableheader6"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(6).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader6"));
		Assert.assertEquals(tmdb.tableheaders().get(7).getText(),prop.getProperty("DocketFlextableheader7"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(7).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader7"));
		Assert.assertEquals(tmdb.tableheaders().get(8).getText(),prop.getProperty("DocketFlextableheader8"));
		Reporter.log("--  Dockets Table Header1 is : "+tmdb.tableheaders().get(8).getText()+" Expected is : "+prop.getProperty("DocketFlextableheader8"));
		pagenavigatebuttons(driver);
		tmdb.Flexbox_close().click();
	}
	public static void Contactstableverification(WebDriver driver) throws Exception {
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Contacts Table Verification
		tmdb.Contactinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.tableheaders().get(0).getText(),prop.getProperty("ContactsFlextableheader0"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(0).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader0"));
		Assert.assertEquals(tmdb.tableheaders().get(1).getText(),prop.getProperty("ContactsFlextableheader1"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(1).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader1"));
		Assert.assertEquals(tmdb.tableheaders().get(2).getText(),prop.getProperty("ContactsFlextableheader2"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(2).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader2"));
		Assert.assertEquals(tmdb.tableheaders().get(3).getText(),prop.getProperty("ContactsFlextableheader3"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(3).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader3"));
		Assert.assertEquals(tmdb.tableheaders().get(4).getText(),prop.getProperty("ContactsFlextableheader4"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(4).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader4"));
		Assert.assertEquals(tmdb.tableheaders().get(5).getText(),prop.getProperty("ContactsFlextableheader5"));
		Reporter.log("--  Contacts Table Header1 is : "+tmdb.tableheaders().get(5).getText()+" Expected is : "+prop.getProperty("ContactsFlextableheader5"));	
		pagenavigatebuttons(driver);
		tmdb.Flexbox_close().click();
		
	}
	public static void Passengerstableverification(WebDriver driver) throws Exception {
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Contacts Table Verification
		tmdb.passengerinfo_count().click();
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.tableheaders().get(0).getText(),prop.getProperty("PassengersFlextableheader0"));
		Reporter.log("--  Passenger Table Header1 is : "+tmdb.tableheaders().get(0).getText()+" Expected is : "+prop.getProperty("PassengersFlextableheader0"));
		Assert.assertEquals(tmdb.tableheaders().get(1).getText(),prop.getProperty("PassengersFlextableheader1"));
		Reporter.log("--  Passengers Table Header1 is : "+tmdb.tableheaders().get(1).getText()+" Expected is : "+prop.getProperty("PassengersFlextableheader1"));
		Assert.assertEquals(tmdb.tableheaders().get(2).getText(),prop.getProperty("PassengersFlextableheader2"));
		Reporter.log("--  Passengers Table Header1 is : "+tmdb.tableheaders().get(2).getText()+" Expected is : "+prop.getProperty("PassengersFlextableheader2"));
		Assert.assertEquals(tmdb.tableheaders().get(3).getText(),prop.getProperty("PassengersFlextableheader3"));
		Reporter.log("--  Passengers Table Header1 is : "+tmdb.tableheaders().get(3).getText()+" Expected is : "+prop.getProperty("PassengersFlextableheader3"));
		Assert.assertEquals(tmdb.tableheaders().get(4).getText(),prop.getProperty("PassengersFlextableheader4"));
		Reporter.log("--  Passengers Table Header1 is : "+tmdb.tableheaders().get(4).getText()+" Expected is : "+prop.getProperty("PassengersFlextableheader4"));
		pagenavigatebuttons(driver);
		tmdb.Flexbox_close().click();
		
	}
	public static void Paymentstableverification(WebDriver driver) throws Exception {
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Payment Table Verification
		wait.until(ExpectedConditions.visibilityOf(tmdb.FlexboxHeader()));
		Assert.assertEquals(tmdb.tableheaders().get(0).getText(),prop.getProperty("PaymentsFlextableheader0"));
		Reporter.log("--  Payment Table Header1 is : "+tmdb.tableheaders().get(0).getText()+" Expected is : "+prop.getProperty("PaymentsFlextableheader0"));
		Assert.assertEquals(tmdb.tableheaders().get(1).getText(),prop.getProperty("PaymentsFlextableheader1"));
		Reporter.log("--  Payment Table Header1 is : "+tmdb.tableheaders().get(1).getText()+" Expected is : "+prop.getProperty("PaymentsFlextableheader1"));
		Assert.assertEquals(tmdb.tableheaders().get(2).getText(),prop.getProperty("PaymentsFlextableheader2"));
		Reporter.log("--  Payment Table Header1 is : "+tmdb.tableheaders().get(2).getText()+" Expected is : "+prop.getProperty("PaymentsFlextableheader2"));
		Assert.assertEquals(tmdb.tableheaders().get(3).getText(),prop.getProperty("PaymentsFlextableheader3"));
		Reporter.log("--  Payment Table Header1 is : "+tmdb.tableheaders().get(3).getText()+" Expected is : "+prop.getProperty("PaymentsFlextableheader3"));
		Assert.assertEquals(tmdb.tableheaders().get(4).getText(),prop.getProperty("PaymentsFlextableheader4"));
		Reporter.log("--  Payment Table Header1 is : "+tmdb.tableheaders().get(4).getText()+" Expected is : "+prop.getProperty("PaymentsFlextableheader4"));
		pagenavigatebuttons(driver);
		tmdb.Flexbox_close().click();	
	}
	
	
	
	
	
	public static void pagenavigatebuttons(WebDriver driver) throws Exception {
		TAMSDshboard tmdb = new TAMSDshboard(driver);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//Page navigater buttons verification
		WebElement previousbutton = tmdb.Navigationpreviousbutton();
		WebElement nextbutton =tmdb.Navigationnextbutton();
		wait.until(ExpectedConditions.visibilityOf(tmdb.pagenavigatebuttons().get(1)));
		int noofpages=tmdb.pagenavigatebuttons().size();
		System.out.println("No of pages is : "+noofpages);
		 //Throwing Stale Element Exception
		SoftAssert sftasrt = new SoftAssert(); 
		//for(int i=0;i<noofpages-1;i++) {tmdb.pagenavigatebuttons().get(i+1).click();} 
		//Navigation Next Verified
		nextbutton.click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(tmdb.pagenavigatebuttons().get(1))); 
		nextbutton.click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(tmdb.pagenavigatebuttons().get(1))); 
		//Last Page click
		tmdb.pagenavigatebuttons().get(noofpages-2).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(tmdb.pagenavigatebuttons().get(noofpages-1))); 
		sftasrt.assertFalse(nextbutton.isEnabled(),"seems Next is enabled");
		Reporter.log("------  Next button is not enabled by default showing Last page results"); 
		Reporter.log("------  Navigation Next is Verified");
		previousbutton.click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(tmdb.pagenavigatebuttons().get(1))); 
		previousbutton.click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(tmdb.pagenavigatebuttons().get(1))); 
		tmdb.pagenavigatebuttons().get(1).click();
		Thread.sleep(1500);
		sftasrt.assertFalse(previousbutton.isEnabled(),"Seems Previous is enabled");
		Reporter.log("------  Previous button is not enabled by default showing 1st page results"); 
		Reporter.log("------  Navigation Previous is Verified");
			 
	}

}
