package pages;

import DriverRepo.ConfigDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "My account")
    WebElement myAccount;

    @FindBy(linkText = "Login")
    WebElement login;

    @FindBy(linkText = "Register")
    WebElement register;

    @FindBy(linkText = "Logout")
    WebElement logout;

    public void clickMyAccounts() {

        myAccount.click();
    }

    public void clickLogin() {
        login.click();
    }

    public void clickRegister() {
        register.click();
    }

    public void clickLogout() {
        logout.click();
    }

    public boolean isLogoutSuccessful() {
        return ConfigDriver.getDriver().getTitle().contains("Account Logout");
    }

    public boolean isLogoutVisible() {
        try {
            return logout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}