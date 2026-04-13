package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverRepo.ConfigDriver;
import utility.GenericMethods;
import utility.SuiteSetup;

public class LoginTestCases extends SuiteSetup {

    private static final Logger log = LogManager.getLogger(LoginTestCases.class);

    @BeforeMethod
    public void setupTest() {
        log.info("Clearing session and navigating to base URL before each test");
        ConfigDriver.getDriver().manage().deleteAllCookies();
        GenericMethods.launchUrl(GenericMethods.readPropertiesFile().getProperty("url"));
        ConfigDriver.getDriver().navigate().refresh();
    }

    // ===================== UI / SMOKE =====================

    @Test(priority = 1, description = "Verify all login page elements are visible",
            groups = {"login", "smoke"})
    public void verifyLoginPageElementsAreVisible() {
        log.info("TC-L01: Verify all login page elements are visible");

        homePage.clickMyAccounts();
        homePage.clickLogin();

        Assert.assertTrue(login.isEmailFieldVisible(),
                "TC-L01 FAILED: Email field is not visible");
        Assert.assertTrue(login.isPasswordFieldVisible(),
                "TC-L01 FAILED: Password field is not visible");
        Assert.assertTrue(login.isLoginButtonVisible(),
                "TC-L01 FAILED: Login button is not visible");

        log.info("TC-L01 PASSED - All login page elements are visible");
    }

    // ===================== POSITIVE TEST CASES =====================

    @Test(priority = 2, description = "Verify successful login with valid credentials",
            groups = {"login", "smoke"})
    public void verifySuccessfulLogin() {
        log.info("TC-L02: Verify successful login with valid credentials");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginUser("dc@gmail.com", "Test@01");
        

        Assert.assertTrue(login.isLoginSuccessful(), "TC-L02 FAILED: Login unsuccessful with valid credentials");

        log.info("TC-L02 PASSED");
    }

    @Test(priority = 3, description = "Verify successful login and logout", groups = {"login", "smoke"})
    public void verifySuccessfulLogout() {
        log.info("TC-L03: Verify successful login and logout");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginUser("dc@gmail.com", "Test@01");

        Assert.assertTrue(
            login.isLoginSuccessful(),
            "TC-L03 FAILED: Login failed"
        );

        homePage.clickMyAccounts();
        homePage.clickLogout();

        Assert.assertTrue(
            homePage.isLogoutSuccessful(),
            "TC-L03 FAILED: Logout page not displayed"
        );
        log.info("TC-L03 PASSED");
    }

    // ===================== NEGATIVE TEST CASES =====================

    @Test(priority = 4, description = "Verify login with invalid email and password",
            groups = {"login", "regression"})
    public void verifyLoginWithInvalidCredentials() {
        log.info("TC-L04: Verify login with invalid credentials");

        homePage.clickMyAccounts();
        homePage.clickLogin();

        String email = "invalid" + System.currentTimeMillis() + "@mail.com";
        login.loginUser(email, "WrongPass123");
        login.verifyLoginFailed();

        log.info("TC-L04 PASSED - Error message validated");
    }

    @Test(priority = 5, description = "Verify login with wrong password for valid email",
            groups = {"login", "regression"})
    public void verifyLoginWithWrongPassword() {
        log.info("TC-L05: Verify login with correct email but wrong password");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginUser("dc@gmail.com", "WrongPassword@999");
        login.verifyLoginFailed();

        log.info("TC-L05 PASSED");
    }

    // ===================== FIELD VALIDATION TEST CASES =====================

    @Test(priority = 6, description = "Verify login with empty email field stays on login page",
            groups = {"login", "field-validation"})
    public void verifyLoginWithEmptyEmail() {
        log.info("TC-L06: Verify login with empty email");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginWithEmptyEmail("Test@1234");

        Assert.assertTrue(
                login.isEmailValidationTriggered(),
                "TC-L06 FAILED: Should stay on login page when email is empty"
        );
        log.info("TC-L06 PASSED - Browser validation triggered for empty email");
    }

    @Test(priority = 7, description = "Verify login with empty password stays on login page",
            groups = {"login", "field-validation"})
    public void verifyLoginWithEmptyPassword() {
        log.info("TC-L07: Verify login with empty password");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginWithEmptyPassword("dc@gmail.com");

        Assert.assertTrue(
                login.isEmailValidationTriggered(),
                "TC-L07 FAILED: Should stay on login page when password is empty"
        );
        log.info("TC-L07 PASSED");
    }

    @Test(priority = 8, description = "Verify login with both fields empty",
            groups = {"login", "field-validation"})
    public void verifyLoginWithBothFieldsEmpty() {
        log.info("TC-L08: Verify login with both email and password empty");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.submitWithoutCredentials();

        Assert.assertTrue(
                login.isEmailValidationTriggered(),
                "TC-L08 FAILED: Should remain on login page"
        );
        log.info("TC-L08 PASSED");
    }

    @Test(priority = 9, description = "Verify login with invalid email format",
            groups = {"login", "field-validation"})
    public void verifyLoginWithInvalidEmailFormat() {
        log.info("TC-L09: Verify login with invalid email format");

        homePage.clickMyAccounts();
        homePage.clickLogin();
        login.loginUser("notAnEmail", "Test@1234");

        Assert.assertTrue(
                login.isEmailValidationTriggered(),
                "TC-L09 FAILED: Should stay on login page for invalid email format"
        );
        log.info("TC-L09 PASSED");
    }
}