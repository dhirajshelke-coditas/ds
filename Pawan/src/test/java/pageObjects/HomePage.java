package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText = "My account")
	WebElement btnMyAccount;
	
	@FindBy(linkText = "Register")
	WebElement btnRegister;

	
	@FindBy(linkText = "Login")
	WebElement btnLogin;
	
	
	public void clickMyAccounts() {
		btnMyAccount.click();
	}
	
	public void clickRegister() {
		btnRegister.click();
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	

	
}