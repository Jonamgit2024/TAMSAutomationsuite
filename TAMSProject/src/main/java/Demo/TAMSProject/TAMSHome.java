package Demo.TAMSProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TAMSHome {
	WebDriver driver;

	public TAMSHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Travel Agency Management System']")
	private WebElement loginheader;
	public WebElement loginheader() {
		return loginheader;
	}
	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement logout;
	public WebElement logout() {
		return logout;
	}
	@FindBy(xpath = "(//a[@role='button'])[1]")
	private WebElement anchorbutton1;
	public WebElement anchorbutton1() {
	return anchorbutton1;
	}
	@FindBy(xpath = "(//a[@role='button'])[2]")
	private WebElement anchorbutton2;
	public WebElement anchorbutton2() {
	return anchorbutton2;
	}
	@FindBy(xpath = "(//a[@role='button'])[3]")
	private WebElement anchorbutton3;
	public WebElement anchorbutton3() {
	return anchorbutton3;
	}
	@FindBy(xpath = "(//a[@role='button'])[4]")
	private WebElement anchorbutton4;
	public WebElement anchorbutton4() {
	return anchorbutton4;
	}	
	@FindBy(xpath = "(//a[@role='button'])[5]")
	private WebElement anchorbutton5;
	public WebElement anchorbutton5() {
	return anchorbutton5;
	}
	@FindBy(xpath = "(//a[@role='button'])[6]")
	private WebElement anchorbutton6;
	public WebElement anchorbutton6() {
	return anchorbutton6;
	}
	@FindBy(xpath = "(//a[@role='button'])[7]")
	private WebElement anchorbutton7;
	public WebElement anchorbutton7() {
	return anchorbutton7;
	}
	@FindBy(xpath = "(//a[@role='button'])[8]")
	private WebElement anchorbutton8;
	public WebElement anchorbutton8() {
	return anchorbutton8;
	}
	@FindBy(xpath = "(//a[@role='button'])[9]")
	private WebElement anchorbutton9;
	public WebElement anchorbutton9() {
	return anchorbutton9;
	}
	@FindBy(xpath = "(//a[@role='button'])[10]")
	private WebElement anchorbutton10;
	public WebElement anchorbutton10() {
	return anchorbutton10;
	}
	@FindBy(xpath = "//div[@class='header-right-user']")
	private WebElement userdataheader;
	public WebElement userdataheader() {
	return userdataheader;
	}
	@FindBy(xpath = "//div[@id='header-left']//preceding :: img")
	private WebElement headerleftimg;
	public WebElement headerleftimg() {
	return headerleftimg;
	}
	
	@FindBy(xpath = "//i[@title='Help']")
	private WebElement helpicon;
	public WebElement helpicon() {
	return helpicon;
	}
	@FindBy(xpath = "//i[@title='Change Password']")
	private WebElement pwdchngicon;
	public WebElement pwdchngicon() {
	return pwdchngicon;
	}
}
