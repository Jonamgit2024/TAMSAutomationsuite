package Demo.TAMSProject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TAMSDshboard {
	WebDriver driver;

	public TAMSDshboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='card-block'])[1]//child::h3")
	private WebElement dashboardcardblock1header;
	public WebElement dashboardcardblock1header() {
		return dashboardcardblock1header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[2]//child::h3")
	private WebElement dashboardcardblock2header;
	public WebElement dashboardcardblock2header() {
		return dashboardcardblock2header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[3]//child::h3")
	private WebElement dashboardcardblock3header;
	public WebElement dashboardcardblock3header() {
		return dashboardcardblock3header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[4]//child::h3")
	private WebElement dashboardcardblock4header;
	public WebElement dashboardcardblock4header() {
		return dashboardcardblock4header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[5]//child::h3")
	private WebElement dashboardcardblock5header;
	public WebElement dashboardcardblock5header() {
		return dashboardcardblock5header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[6]//child::h3")
	private WebElement dashboardcardblock6header;
	public WebElement dashboardcardblock6header() {
		return dashboardcardblock6header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[7]//child::h3")
	private WebElement dashboardcardblock7header;
	public WebElement dashboardcardblock7header() {
		return dashboardcardblock7header;
	}
	@FindBy(xpath = "(//div[@class='card-block'])[8]//child::h3")
	private WebElement dashboardcardblock8header;
	public WebElement dashboardcardblock8header() {
		return dashboardcardblock8header;
	}
	@FindBy(xpath = "//legend[@id='legendtext-0']")
	private WebElement legendtext;
	public WebElement legendtext() {
		return legendtext;
	}
	@FindBy(xpath = "//label[text()=' Agent:']//following::select[@id='agent_id']")
	private WebElement SelectbarfollowingAgent;
	public WebElement SelectbarfollowingAgent() {
		return SelectbarfollowingAgent;
	}
	@FindBy(xpath = "//label[text()='Airlines:']//following::select[@id='airline_id']")
	private WebElement SelectbarfollowingAirlines;
	public WebElement SelectbarfollowingAirlines() {
		return SelectbarfollowingAirlines;
	}
	@FindBy(xpath = "//label[text()='Date Range: ']")
	private WebElement DateRangeText;
	public WebElement DateRangeText() {
		return DateRangeText;
	}

	@FindBy(xpath = "//input[@id='idschestartdate']")
	private WebElement DateRangeInput;
	public WebElement DateRangeInput() {
		return DateRangeInput;
	}
	
	@FindBy(xpath = "//button[text()='Show Results']")
	private WebElement ShowResultsbutton;
	public WebElement ShowResultsbutton() {
		return ShowResultsbutton;
	}
	@FindBy(xpath = "//button[text()='Reset']")
	private WebElement Resetbutton;
	public WebElement Resetbutton() {
		return Resetbutton;
	}
	@FindBy(xpath = "//select[@id='agent_id']")
	private WebElement AgentSelectionbutton;
	public WebElement AgentSelectionbutton() {
		return AgentSelectionbutton;
	}
	@FindBy(xpath = "//select[@id='airline_id']")
	private WebElement AirlineSelectionbutton;
	public WebElement AirlineSelectionbutton() {
		return AirlineSelectionbutton;
	}
	@FindBy(xpath = "//span[text()='Today']")
	private WebElement Date_Range_Selection_Today;
	public WebElement Date_Range_Selection_Today() {
		return Date_Range_Selection_Today;
	}
	@FindBy(xpath = "//span[text()='Last 7 days']")
	private WebElement Date_Range_Selection_Last7days;
	public WebElement Date_Range_Selection_Last7days() {
		return Date_Range_Selection_Last7days;
	}
	
	@FindBy(xpath = "//span[text()='Month to date']")
	private WebElement Date_Range_Selection_Monthtodate;
	public WebElement Date_Range_Selection_Monthtodate() {
		return Date_Range_Selection_Monthtodate;
	}
	@FindBy(xpath = "//span[text()='Year to date']")
	private WebElement Date_Range_Selection_Yeartodate;
	public WebElement Date_Range_Selection_Yeartodate() {
		return Date_Range_Selection_Yeartodate;
	}
	@FindBy(xpath = "//span[text()='The Previous Month']")
	private WebElement Date_Range_Selection_Previousmonth;
	public WebElement Date_Range_Selection_Previousmonth() {
		return Date_Range_Selection_Previousmonth;
	}
	@FindBy(xpath = "//span[@id='docket_count']//a")
	private WebElement Dockets_count;
	public WebElement Dockets_count() {
		return Dockets_count;
	}
	@FindBy(xpath = "//span[@id='contactinfo_count']//a")
	private WebElement Contactinfo_count;
	public WebElement Contactinfo_count() {
		return Contactinfo_count;
	}
	@FindBy(xpath = "//span[@id='passengerinfo_count']//a")
	private WebElement passengerinfo_count;
	public WebElement passengerinfo_count() {
		return passengerinfo_count;
	}
	@FindBy(xpath = "//span[@id='costpricetotalamount']//a")
	private WebElement costpriceinfo_count;
	public WebElement costpriceinfo_count() {
		return costpriceinfo_count;
	}
	@FindBy(xpath = "//span[@id='salepricetotalamount']//a")
	private WebElement salepriceinfo_count;
	public WebElement salepriceinfo_count() {
		return salepriceinfo_count;
	}
	@FindBy(xpath = "//span[@id='commissiontotalamount']//a")
	private WebElement commissioninfo_count;
	public WebElement  commissioninfo_count() {
		return  commissioninfo_count;
	}
	@FindBy(xpath = "//span[@id='paymentdone']//a")
	private WebElement paymentdone_count;
	public WebElement paymentdone_count() {
		return paymentdone_count;
	}
	@FindBy(xpath = "//span[@id='pendingpayment']//a")
	private WebElement pendingpayment_count;
	public WebElement pendingpayment_count() {
		return pendingpayment_count;
	}
	//Flex box header and close is kind of odd because of presence of Bulk Header and Bulk close
	@FindBy(xpath = "(//h5[@class='modal-title'])[2]")
	private WebElement FlexboxHeader;
	public WebElement FlexboxHeader() {
		return FlexboxHeader;
	}
	@FindBy(xpath = "(//button[@class='close'])[2]")
	private WebElement Flexbox_close;
	public WebElement Flexbox_close() {
		return Flexbox_close;
	}
	@FindBy(xpath = "//tr[@class='tableheader']//th")
	private List <WebElement> tableheaders;
	public List<WebElement> tableheaders() {
		return tableheaders;
	}
	@FindBy(xpath = "//th[@class='tablesearch']//input")
	private List <WebElement> tablesearchplaceholder;
	public List<WebElement> tablesearchplaceholder() {
		return tablesearchplaceholder;
	}
	@FindBy(xpath = "//a[@data-dt-idx]")
	private List <WebElement> pagenavigatebuttons;
	public List<WebElement> pagenavigatebuttons() {
		return pagenavigatebuttons;
	}
	@FindBy(xpath = "//a[text()='Next']")
	private WebElement Navigationnextbutton;
	public WebElement Navigationnextbutton() {
		return Navigationnextbutton;
	}
	@FindBy(xpath = "//a[text()='Previous']")
	private WebElement Navigationpreviousbutton;
	public WebElement Navigationpreviousbutton() {
		return Navigationpreviousbutton;
	}
	
}
