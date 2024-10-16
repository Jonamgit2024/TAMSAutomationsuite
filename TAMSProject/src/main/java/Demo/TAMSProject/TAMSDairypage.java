package Demo.TAMSProject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class TAMSDairypage {
	WebDriver driver;
	
	public TAMSDairypage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@role='button' and text()='Diary']")
	private WebElement dairyanchorbutton;
	public WebElement dairyanchorbutton() {
		return dairyanchorbutton;
	}
	@FindBy(xpath="//div[@id='datepickers']")
	private WebElement datepickers;
	public WebElement datepickers() {
		return datepickers;
	}
	@FindBy(xpath = "//thead[@class='fc-head']//span")
	private List <WebElement> tableheaderspantag;
	public List<WebElement>  tableheaderspantag() {
		return  tableheaderspantag;
	}
	//inDay view this will be a element hence same x-path for 2 
	@FindBy(xpath = "//thead[@class='fc-head']//span")
	private WebElement tableheaderspantagdayview;
	public WebElement tableheaderspantagdayview() {
		return  tableheaderspantagdayview;
	}

	@FindBy(xpath="//div[@id='datepickers']//select[@class='ui-datepicker-month']")
	private WebElement datepickermonthselect;
	public WebElement datepickermonthselect() {
		return datepickermonthselect;
	}
	
	@FindBy(xpath="//div[@id='datepickers']//select[@class='ui-datepicker-year']")
	private WebElement datepickeryearselect;
	public WebElement datepickeryearselect() {
		return datepickeryearselect;
	}
	//For Previous and Next buttons it is tricky and suggesting IDs usage at that buttons level
	
	@FindBy(xpath="//button[@class='fc-prev-button fc-button fc-state-default fc-corner-left']")
	// or try this xpath -(//div[@class='fc-button-group']//button)[1]
	private WebElement previousbutton;
	public WebElement previousbutton() {
	return previousbutton;
	}		
	@FindBy(xpath="//button[@class='fc-next-button fc-button fc-state-default fc-corner-right']")
	// or try this xpath -(//div[@class='fc-button-group']//button)[2]
	private WebElement nextbutton;
	public WebElement nextbutton() {
	return nextbutton;
	}						
	@FindBy(xpath="//div[@class='fc-center']//h2")
	private WebElement dateheader;
	public WebElement dateheader() {
		return dateheader;
	}		
		
	@FindBy(xpath="//div[@class='fc-button-group']//button[text()='day']")
	private WebElement daybutton;
	public WebElement daybutton() {
		return daybutton;
	}
	
	@FindBy(xpath="//div[@class='fc-button-group']//button[text()='week']")
	private WebElement weekbutton;
	public WebElement weekbutton() {
		return weekbutton;
	}	
	@FindBy(xpath="//div[@class='fc-button-group']//button[text()='month']")
	private WebElement monthbutton;
	public WebElement monthbutton() {
		return monthbutton;
	}
	@FindBy(xpath="//button[text()='today']")
	private WebElement todaybutton;
	public WebElement todaybutton() {
		return todaybutton;
	}
	
	@FindBy(xpath = "//div[@id='datepickers']//select[@class='ui-datepicker-month']//option")
	private List <WebElement> monthselections;
	public List<WebElement>  monthselections() {
		return  monthselections;
	}
	
	@FindBy(xpath = "//div[@id='datepickers']//select[@class='ui-datepicker-year']//option")
	private List <WebElement> yearselections;
	public List<WebElement>  yearselections() {
		return yearselections;
	}
	@FindBy(xpath = "//div[@id='datepickers']//tbody//a")
	private List <WebElement> dateselections;
	public List<WebElement>  dateselections() {
		return dateselections;
	}
	
	@FindBy(xpath = "//span[@class='fc-day-number']")
	private List <WebElement> daynumbers;
	public List<WebElement>  daynumbers() {
		return daynumbers;
	}
	
	@FindBy(xpath = "//td[@class='fc-event-container']")
	private List <WebElement> eventcontainers;
	public List<WebElement>  eventcontainers() {
		return eventcontainers;
	}
	
	@FindBy(xpath = "//td[@class='fc-event-container']//a")
	private List <WebElement> gridevents;
	public List<WebElement>  gridevents() {
		return gridevents;
	}
}