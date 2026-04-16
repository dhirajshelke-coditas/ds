package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage {
	
	
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(id = "input-firstname")
	WebElement txtFirstname;
	
	@FindBy(id = "input-lastname")
	WebElement txtLastname;
	
	@FindBy(id = "input-email")
	WebElement txtEmail;
	
	@FindBy(id = "input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(id = "input-confirm")
	WebElement txtConPass;

	@FindBy(css  = "label[for='input-agree']")
	WebElement btnAgree;
	
	@FindBy(css = "input[value='Continue']")
	WebElement btnContinue;
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmailName(String ename) {
		txtEmail.sendKeys(ename);
	}
	
	public void setPhNumber(String pnum) {
		txtTelephone.sendKeys(pnum);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setConfirmPass(String pass) {
		txtConPass.sendKeys(pass);
	}
	
	public void clickAgree() {
		btnAgree.click();;
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	
	

}















