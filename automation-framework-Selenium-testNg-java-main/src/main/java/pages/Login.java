package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DriverRepo.ConfigDriver;

public class Login {

	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(css = "input[value='Login']")
	WebElement loginBtn;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	WebElement errorMessage;

	public void loginUser(String emailId, String pwd) {
		email.clear();
		email.sendKeys(emailId);
		password.clear();
		password.sendKeys(pwd);
		loginBtn.click();
	}
	public boolean isLoginSuccessful() {
	    try {
	        WebDriverWait wait = new WebDriverWait(ConfigDriver.getDriver(), Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.titleContains("My Account"));
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	public boolean isEmailFieldEmpty() {
		return email.getAttribute("value").isEmpty();
	}

	public boolean isPasswordFieldEmpty() {
		return password.getAttribute("value").isEmpty();
	}

	public void submitWithoutCredentials() {
		email.clear();
		password.clear();
		loginBtn.click();
	}

	public void loginWithEmptyEmail(String pwd) {
		email.clear();
		password.clear();
		password.sendKeys(pwd);
		loginBtn.click();
	}

	public void loginWithEmptyPassword(String emailId) {
		email.clear();
		password.clear();
		email.sendKeys(emailId);
		loginBtn.click();
	}

	public boolean isEmailFieldVisible() {
		try {
			return email.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPasswordFieldVisible() {
		try {
			return password.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLoginButtonVisible() {
		try {
			return loginBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isOnLoginPage() {
		return ConfigDriver.getDriver().getCurrentUrl().contains("route=account/login");
	}

	public boolean isEmailValidationTriggered() {
		// HTML5 native validation — browser stays on page
		return ConfigDriver.getDriver().getCurrentUrl().contains("route=account/login");
	}

	public void verifyLoginFailed() {
		String msg = errorMessage.getText();

		Assert.assertTrue(msg.contains("Warning") || msg.contains("attempts") || msg.contains("No match"),
				"Invalid login not handled!");
	}
}