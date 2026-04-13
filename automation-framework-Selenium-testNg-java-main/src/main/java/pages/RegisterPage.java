package pages;

import DriverRepo.ConfigDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	WebElement firstName;

	@FindBy(id = "input-lastname")
	WebElement lastName;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(id = "input-confirm")
	WebElement confirmPassword;

	@FindBy(xpath = "//label[@for='input-agree']")
	WebElement agree;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;

	public void registerUser(String fName, String lName, String mail, String phone, String pwd) {

		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		email.sendKeys(mail);
		telephone.sendKeys(phone);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(pwd);
		agree.click();
		continueBtn.click();
	}

	public void registerWithPasswordMismatch(String fName, String lName, String mail, String phone, String pwd,
			String wrongConfirm) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		email.sendKeys(mail);
		telephone.sendKeys(phone);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(wrongConfirm);
		agree.click();
		continueBtn.click();
	}

	public void registerWithEmptyFields() {
		agree.click();
		continueBtn.click();
	}

	public boolean isPasswordMismatchErrorDisplayed() {
		try {
			java.util.List<WebElement> errors = ConfigDriver.getDriver().findElements(By.cssSelector("#input-confirm .text-danger"));
			for (WebElement error : errors) {
				if (error.isDisplayed() && error.getText().toLowerCase().contains("does not match")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFieldErrorDisplayed(String fieldId) {
		try {
			WebElement field = ConfigDriver.getDriver().findElement(By.id(fieldId));
			String nextSiblingClass = ConfigDriver.getDriver()
					.findElement(By.cssSelector("#" + fieldId + " ~ .text-danger")).getText();
			return !nextSiblingClass.isEmpty();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean areValidationErrorsDisplayed() {
		try {
			java.util.List<WebElement> errors = ConfigDriver.getDriver().findElements(By.cssSelector(".text-danger"));
			return errors.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPasswordLengthErrorDisplayed() {
		try {
			java.util.List<WebElement> errors = ConfigDriver.getDriver().findElements(By.cssSelector(".text-danger"));
			for (WebElement error : errors) {
				if (error.isDisplayed() && error.getText().contains("Password must be between")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDuplicateEmailErrorDisplayed() {
		try {
			WebElement alert = ConfigDriver.getDriver().findElement(By.cssSelector(".alert-danger"));
			return alert.isDisplayed() && alert.getText().contains("already registered");
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isOnRegisterPage() {
		return ConfigDriver.getDriver().getCurrentUrl().contains("route=account/register");
	}

	public boolean isRegistrationSuccessful() {
		String msg = ConfigDriver.getDriver().findElement(By.tagName("h1")).getText();
		return msg.contains("Your Account Has Been Created!");
	}
}