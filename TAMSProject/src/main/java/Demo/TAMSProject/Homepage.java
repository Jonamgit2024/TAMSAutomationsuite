package Demo.TAMSProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class Homepage {
	WebDriver driver;
	
	public Homepage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//img[@class='logoimage']")
	private WebElement logoimage;
	public WebElement logoimage() {
		return logoimage;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[1]")
	private WebElement navigationlink1;
	public WebElement navigationlink1() {
		return navigationlink1;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[2]")
	private WebElement navigationlink2;
	public WebElement navigationlink2() {
		return navigationlink2;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[3]")
	private WebElement navigationlink3;
	public WebElement navigationlink3() {
		return navigationlink3;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[4]")
	private WebElement navigationlink4;
	public WebElement navigationlink4() {
		return navigationlink4;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[5]")
	private WebElement navigationlink5;
	public WebElement navigationlink5() {
		return navigationlink5;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[6]")
	private WebElement navigationlink6;
	public WebElement navigationlink6() {
		return navigationlink6;
	}
	@FindBy(xpath="(//a[@class='nav-link'])[7]")
	private WebElement navigationlink7;
	public WebElement navigationlink7() {
		return navigationlink7;
	}
	@FindBy(xpath="(//button[@id='custom-close-btn'])")
	private WebElement loginclsbtn;
	public WebElement loginclsbtn() {
		return loginclsbtn;
	}
	@FindBy(xpath="//a[@class='nav-link' and text()='Login']")
	private WebElement loginlnk;
	public WebElement loginlnk() {
		return loginlnk;
	}
	@FindBy(xpath="//input[@id='swal-input1']")
	private WebElement input1;
	public WebElement input1() {
		return input1;
	}
	@FindBy(xpath="//input[@id='swal-input2']")
	private WebElement input2;
	public WebElement input2() {
		return input2;
	}
	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginbtn;
	public WebElement loginbtn() {
		return loginbtn;
	}
	@FindBy(xpath="//button[text()='Sign Up']")
	private WebElement signupbtn;
	public WebElement signupbtn() {
		return signupbtn;
	}

	@FindBy(xpath="//label[@id='lblerror']")
	private WebElement loginerror;
	public WebElement loginerror() {
		return loginerror;
	}
	@FindBy(xpath="//label[text()='This user is not registered']")
	private WebElement errormsg1;
	public WebElement errormsg1() {
		return errormsg1;
	}
	
	
	
	
	
}
