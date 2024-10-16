package Demo.TAMSProject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Year;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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

public class TAMSDairy extends Basemethods {
	
	static WebDriver driver;
	//used monthnamefull globlal variable to avoid calling it in different methods
	static String monthnamefull=null;
	static String yearnumber=null;
	static int datenumber=0;
	static String dayinweek=null;
	
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
	
	@SuppressWarnings("deprecation")
	@Test(priority =4)
	public void TAMSDairyOverview() throws Exception {
		//Overview verification 
		Reporter.log( "TAMSDairyOverview Validation", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDairypage tdpg = new TAMSDairypage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		tdpg.dairyanchorbutton().click();
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDairyURL") );
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(25));
		
		//Getting Current Date,Month and DAY from Selenium/Java
		String monthdisplay=getmonthdata();
		String datedisplay=getdatedata();
		String currentdayoftheweek=getweekdata();
		Reporter.log("The Monthdisplay expected is :"+monthdisplay);
		Reporter.log("The Daydisplay expected is :"+datedisplay);
		Reporter.log("The current day of the week is : "+currentdayoftheweek);
		
		//Date pickers Availability
		Assert.assertTrue(tdpg.datepickers().isDisplayed());
		
		//Today disabled by default
		Assert.assertFalse(tdpg.todaybutton().isEnabled());
		
		//Month view default and Verification
		Assert.assertTrue(tdpg.monthbutton().isDisplayed());
		monthviewvalidation();
		Assert.assertEquals(tdpg.dateheader().getText(),monthdisplay);
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Expected is : "+monthdisplay);
		
		//Day view click and verification
		Assert.assertTrue(tdpg.daybutton().isDisplayed());
		tdpg.daybutton().click();
		Assert.assertEquals(tdpg.tableheaderspantagdayview().getText(), currentdayoftheweek);
		Reporter.log("Text in Table Header is : "+tdpg.tableheaderspantagdayview().getText()+" Expected is : "+currentdayoftheweek);
		Assert.assertEquals(tdpg.dateheader().getText(),datedisplay);
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Expected is : "+datedisplay);
		
		//week view click and partial verification
		Assert.assertTrue(tdpg.weekbutton().isDisplayed());
		tdpg.weekbutton().click();
		weekviewvalidation();
		Assert.assertTrue(tdpg.dateheader().getText().contains(yearnumber));
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Is Partially Verified with Year number : "+yearnumber);
		
		//Previous and Next button Validation
		//In Day View
		SoftAssert sftasrt = new SoftAssert();
		tdpg.daybutton().click();
		tdpg.previousbutton().click();
		Assert.assertNotEquals(tdpg.dateheader().getText(),datedisplay);
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Is not Same as "+datedisplay);
		Assert.assertEquals(tdpg.tableheaderspantagdayview().getText(),previousday());
		Reporter.log("Text in Table Header is : "+tdpg.tableheaderspantagdayview().getText()+" Expected is : "+previousday());
		tdpg.nextbutton().click();
		sftasrt.assertEquals(tdpg.dateheader().getText(),datedisplay);
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Is Same as "+datedisplay+" As it comes back to current day");
		tdpg.nextbutton().click();
		sftasrt.assertNotEquals(tdpg.dateheader().getText(),datedisplay,"Some times the Response time is very low in such cases next button response may fail");
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Is not Same as "+datedisplay);
		sftasrt.assertEquals(tdpg.tableheaderspantagdayview().getText(), nextday(),"Bad response time might fail these cases");
		Reporter.log("Text in Table Header is : "+tdpg.tableheaderspantagdayview().getText()+" Expected is : "+nextday());
		
		//In Month view
		tdpg.monthbutton().click();
		tdpg.previousbutton().click();
		Assert.assertTrue(tdpg.dateheader().getText().contains(previousmonth()));
		Reporter.log("Text in Table Header is : "+tdpg.dateheader().getText()+" Expected is : "+previousmonth());
		wait.until(ExpectedConditions.visibilityOf(tdpg.nextbutton()));
		tdpg.nextbutton().click();
		sftasrt.assertEquals(tdpg.dateheader().getText(),monthdisplay,"Some cases might fail due to bad response time");
		Reporter.log("Text in DATE Header is : "+tdpg.dateheader().getText()+" Expected is : "+monthdisplay+" As it Comes back to the current Month");
		wait.until(ExpectedConditions.visibilityOf(tdpg.nextbutton()));
		tdpg.nextbutton().click();
		sftasrt.assertTrue(tdpg.dateheader().getText().contains(nextmonth()),"Some times the Response time is very low in such cases next button response may fail");
		Reporter.log("Text in Table Header is : "+tdpg.dateheader().getText()+" Expected is : "+nextmonth());
	}
	@SuppressWarnings("deprecation")
	@Test(priority =5)
	public void TAMSDairyDatepicking() throws Exception {
		Reporter.log( "TAMSDairyOverview Validation", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDairypage tdpg = new TAMSDairypage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		tdpg.dairyanchorbutton().click();
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDairyURL") );
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(tdpg.datepickers()));
		String monthname=null;
		String monthnamefull=null;
		monthname = monthpickfirst3char();
		monthnamefull=prop.getProperty("MonthpickFullname");
		Select slct1=new Select(tdpg.datepickermonthselect());
		slct1.selectByVisibleText(monthname);
		//A 5-Second wait gap is given here as the response time for the dairy is low
		Thread.sleep(5000);
		String yearname=null;
		yearname = prop.getProperty("Yearpick");
		Select slct2=new Select(tdpg.datepickeryearselect());
		slct2.selectByVisibleText(yearname);
		String dateselected=null;
		dateselected = prop.getProperty("Datepicked");
		int dateselection=Integer.parseInt(dateselected)-1;
		//A 5-Second wait gap is given here as the response time for the dairy is low
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfAllElements(tdpg.dateselections()));
		tdpg.dateselections().get(dateselection).click();
		Reporter.log("Given Date is selected and clicked upon :"+dateselected);
		//Make note-the Dairy view is not ready for such quick selections tried Action class as well..not fruitful
		wait.until(ExpectedConditions.visibilityOf(tdpg.todaybutton()));
		//A 5-Second wait gap is given here as the response time for the dairy is low
		Thread.sleep(5000);
		String dateheaderdayview=monthnamefull+" "+dateselected+", "+yearname;
		Assert.assertEquals(tdpg.dateheader().getText(),dateheaderdayview);
		Reporter.log("The Header of the calender is verified to be : "+tdpg.dateheader().getText()+" Selected date is : "+dateheaderdayview);
		tdpg.monthbutton().click();
		Reporter.log("Selction of given Month and Year is Done : "+monthname+" "+yearname);
		String dateheadermonthview=monthnamefull+" "+yearname;
		Assert.assertEquals(tdpg.dateheader().getText(),dateheadermonthview);
		Reporter.log("The Header of the calender is verified to be : "+dateheadermonthview);
		//Clicking on Today to come back to the current date
		tdpg.todaybutton().click();
		Reporter.log("The Today button is Clicked");
		Thread.sleep(3000);
		Assert.assertEquals(tdpg.dateheader().getText(),getmonthdata());
		Reporter.log("The Header of the Calender is verifed with current month --- current month is :"+getmonthdata()+" Header of the calender is :"+tdpg.dateheader().getText());
	}
	@SuppressWarnings("deprecation")
	@Test(priority =6)
	public void TAMSDairyEventContainers() throws Exception {
		Reporter.log( "TAMSDairy Event Containers Validation", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDairypage tdpg = new TAMSDairypage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		tdpg.dairyanchorbutton().click();
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDairyURL"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(tdpg.daynumbers()));
		Actions act =new Actions(driver);
		int noofevents=tdpg.eventcontainers().size();
		Reporter.log("Number of Events in Current month is :"+noofevents);
		if(noofevents>1) {
			act.moveToElement(tdpg.eventcontainers().get(0)).build().perform();
			act.moveToElement(tdpg.eventcontainers().get(0)).click(tdpg.eventcontainers().get(0)).build().perform();
			Reporter.log("Clicked on the Event");
			Assert.assertTrue(driver.getCurrentUrl().contains("docket/edit_docket/"));
			Reporter.log("Event Clicking Reidrected the URL Succesfully to "+driver.getCurrentUrl());
			driver.navigate().back();
			Reporter.log("Succesfully came back to the current URL"+driver.getCurrentUrl());
			Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("TAMSDairyURL"));
		}
		//checking random events as well with gap of 7
		for(int i=2;i<noofevents;i=i+5) {
			wait.until(ExpectedConditions.visibilityOfAllElements(tdpg.daynumbers()));
			act.moveToElement(tdpg.eventcontainers().get(i)).build().perform();
			Assert.assertTrue(tdpg.gridevents().get(i).getAttribute("href").contains("docket/edit_docket/"));
			int a =i+1;
			Reporter.log("Validating the "+a+" Term : Its href Contains docket/edit_docket/ ");
		}
		
	}
	
	public static void monthviewvalidation() throws Exception {
		Reporter.log( "Month view Validation", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDairypage tdpg = new TAMSDairypage(driver);
		/*
		 * for(int i=0;i<7;i++) {
		 * Assert.assertEquals(tdpg.tableheaderspantag().get(i).getText(),prop.getProperty("MonthViewTableHeader"));
		 * Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(i).getText()+"Expected is"+prop.getProperty("MonthViewTableHeader0")); }
		 */
		Assert.assertEquals(tdpg.tableheaderspantag().get(0).getText(),prop.getProperty("MonthViewTableHeader0"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(0).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader0"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(1).getText(),prop.getProperty("MonthViewTableHeader1"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(1).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader1"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(2).getText(),prop.getProperty("MonthViewTableHeader2"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(2).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader2"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(3).getText(),prop.getProperty("MonthViewTableHeader3"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(3).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader3"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(4).getText(),prop.getProperty("MonthViewTableHeader4"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(4).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader4"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(5).getText(),prop.getProperty("MonthViewTableHeader5"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(5).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader5"));
		Assert.assertEquals(tdpg.tableheaderspantag().get(6).getText(),prop.getProperty("MonthViewTableHeader6"));
		Reporter.log("---First Tab of the Month view is : "+tdpg.tableheaderspantag().get(6).getText()+" Expected is"+prop.getProperty("MonthViewTableHeader6"));
	}
	public static void weekviewvalidation() throws Exception {
		Reporter.log( "Week view Validation", true );
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		TAMSDairypage tdpg = new TAMSDairypage(driver);
		//Make note the week is only partially validated for the week days table as same in month
		Assert.assertTrue(tdpg.tableheaderspantag().get(0).getText().contains(prop.getProperty("MonthViewTableHeader0")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(0).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader0"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(1).getText().contains(prop.getProperty("MonthViewTableHeader1")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(1).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader1"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(2).getText().contains(prop.getProperty("MonthViewTableHeader2")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(2).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader2"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(3).getText().contains(prop.getProperty("MonthViewTableHeader3")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(3).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader3"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(4).getText().contains(prop.getProperty("MonthViewTableHeader4")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(4).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader4"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(5).getText().contains(prop.getProperty("MonthViewTableHeader5")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(5).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader5"));
		Assert.assertTrue(tdpg.tableheaderspantag().get(6).getText().contains(prop.getProperty("MonthViewTableHeader6")));
		Reporter.log("---First Tab of week view is : "+tdpg.tableheaderspantag().get(6).getText()+" Verified Partially with :"+prop.getProperty("MonthViewTableHeader6"));
		
	}
	public static String getmonthdata() {
		//In this we obtain the current Date, Year and Month
		//Month 
		SimpleDateFormat month = new SimpleDateFormat("MM");
		Date date1 = new Date();
		// Now format the date
		String monthnumber= month.format(date1);
		String monthname=null;
		switch(monthnumber){
		case "1": monthname ="January";break;
		case "2": monthname ="February";break;
		case "3": monthname ="March";break;
		case "4": monthname ="April";break;
		case "5": monthname ="May";break;
		case "6": monthname ="June";break;
		case "7": monthname ="July";break;
		case "8": monthname ="August";break;
		case "9": monthname ="September";break;
		case "10": monthname ="October";break;
		case "11": monthname ="November";break;
		case "12": monthname ="December";break;
		default:Reporter.log("-------------Failure with Month Selection------------------");	
		}
		monthnamefull=monthname;
		// Year
		int yearnum= Year.now().getValue();
		String year= String.valueOf(yearnum);
		yearnumber=year;
		String monthdisplay=monthname+" "+year;
		return monthdisplay;
	}
	public static String getdatedata() {
		// Year
		int yearnum= Year.now().getValue();
		Reporter.log("This Year is :"+yearnum);
		String year= String.valueOf(yearnum);
		
		//Date
		SimpleDateFormat date = new SimpleDateFormat("dd");
		Date date2 = new Date();
		String datenmbr= date.format(date2);
		switch(datenmbr){
		case "01": datenmbr ="1";break;
		case "02": datenmbr ="2";break;
		case "03": datenmbr ="3";break;
		case "04": datenmbr ="4";break;
		case "05": datenmbr ="5";break;
		case "06": datenmbr ="6";break;
		case "07": datenmbr ="7";break;
		case "08": datenmbr ="8";break;
		case "09": datenmbr ="9";break;
		default:Reporter.log("-------------Date is above 10th-So, No correction is required------------------");	
		}
		Reporter.log("Date:"+datenmbr);
		int datenumber=Integer.parseInt(datenmbr);
		String datedisplay=monthnamefull+" "+datenumber+","+" "+year;
		Reporter.log(datedisplay);
		return datedisplay;	
	}
	public static String getweekdata() {
		//String weekdisplay=null;
		SimpleDateFormat date = new SimpleDateFormat("EEEE");
		Date date3 = new Date();
		String dayoftheweek= date.format(date3);
		dayinweek=dayoftheweek;
		return dayoftheweek;		
	}
	public static String previousday() {
		//Make a note dayinweek is initally defined as null it gets it value only when getweekdata() is employed once
		String previousday=null;
		String currentday=dayinweek;
		switch(currentday){
			case "Monday":previousday="Sunday";break;
			case "Tuesday":previousday="Monday";break;
			case "Wednesday":previousday="Tuesday";break;
			case "Thursday":previousday="Wednesday";break;
			case "Friday":previousday="Thursday";break;
			case "Saturday":previousday="Friday";break;
			case "Sunday":previousday="Saturday";break;
			default:Reporter.log("Unable to Know the DAY of the  week");
		}
		return previousday;
	}

	public static String nextday() {
		//Make a note dayinweek is initally defined as null it gets it value only when getweekdata() is employed once
		String nextday=null;
		String currentday=dayinweek;
		switch(currentday){
			case "Monday":nextday="Tuesday";break;
			case "Tuesday":nextday="Wednesday";break;
			case "Wednesday":nextday="Thursday";break;
			case "Thursday":nextday="Friday";break;
			case "Friday":nextday="Saturday";break;
			case "Saturday":nextday="Sunday";break;
			case "Sunday":nextday="Monday";break;
			default:Reporter.log("Unable to Know the DAY of the  week");
		}
		return nextday;
	}
	public static String previousmonth() {
		//Make a note monthnamefull is initally defined as null it gets it value only when getmonthdata() is deployed once
		String previousmonth=null;
		String currentmonth=monthnamefull;
		switch(currentmonth){
			case "January":previousmonth="December";break;
			case "February":previousmonth="January";break;
			case "March":previousmonth="February";break;
			case "April":previousmonth="March";break;
			case "May":previousmonth="April";break;
			case "June":previousmonth="May";break;
			case "July":previousmonth="June";break;
			case "August":previousmonth="July";break;
			case "September":previousmonth="August";break;
			case "October":previousmonth="September";break;
			case "November":previousmonth="October";break;
			case "December":previousmonth="November";break;
			default:Reporter.log("Unable to Know the Month");
		}
		return previousmonth;
	}
	public static String nextmonth() {
		//Make a note monthnamefull is initally defined as null it gets it value only when getmonthdata() is deployed once
		String nextmonth=null;
		String currentmonth=monthnamefull;
		switch(currentmonth){
			case "January":nextmonth="February";break;
			case "February":nextmonth="March";break;
			case "March":nextmonth="April";break;
			case "April":nextmonth="May";break;
			case "May":nextmonth="June";break;
			case "June":nextmonth="July";break;
			case "July":nextmonth="August";break;
			case "August":nextmonth="September";break;
			case "September":nextmonth="October";break;
			case "October":nextmonth="November";break;
			case "November":nextmonth="December";break;
			case "December":nextmonth="January";break;
			default:Reporter.log("Unable to Know the Month");
		}
		return nextmonth;
	}
	public static String monthpickfirst3char() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("data.properties");
		prop.load(fis);
		String monthpick="";
		String monthpickfullname=prop.getProperty("MonthpickFullname");
        for(int i =0;i<3;i++) {
        	monthpick=monthpick+monthpickfullname.charAt(i);
        }
        Reporter.log(monthpickfullname+" Is Shortened to :"+monthpick);
		return monthpick;
	}
}
