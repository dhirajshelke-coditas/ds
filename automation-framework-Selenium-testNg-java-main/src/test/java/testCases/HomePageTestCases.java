package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverRepo.ConfigDriver;
import utility.GenericMethods;
import utility.SuiteSetup;

public class HomePageTestCases extends SuiteSetup {

    private static final Logger log = LogManager.getLogger(HomePageTestCases.class);

    @BeforeMethod
    public void setupTest() {
        GenericMethods.launchUrl(GenericMethods.readPropertiesFile().getProperty("url"));
    }

    @Test(priority = 1, description = "Verify navigation to Register page from HomePage",
            groups = {"homepage", "smoke"})
    public void verifyNavigationToRegisterPage() {
        log.info("TC-HP01: Verify navigation to Register page");

        homePage.clickMyAccounts();
        homePage.clickRegister();

        String url = ConfigDriver.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("route=account/register"),
            "TC-HP01 FAILED: Not navigated to Register page. URL: " + url);
        log.info("TC-HP01 PASSED");
    }

    @Test(priority = 2, description = "Verify navigation to Login page from HomePage",
            groups = {"homepage", "smoke"})
    public void verifyNavigationToLoginPage() {
        log.info("TC-HP02: Verify navigation to Login page");

        homePage.clickMyAccounts();
        homePage.clickLogin();

        String url = ConfigDriver.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("route=account/login"),
            "TC-HP02 FAILED: Not navigated to Login page. URL: " + url);
        log.info("TC-HP02 PASSED");
    }

    @Test(priority = 3, description = "Verify page title on homepage",
            groups = {"homepage", "smoke"})
    public void verifyHomePageTitle() {
        log.info("TC-HP03: Verify homepage title");

        String title = ConfigDriver.getDriver().getTitle();
        Assert.assertFalse(title.isEmpty(), "TC-HP03 FAILED: Page title is empty");
        log.info("TC-HP03 PASSED - Title: {}", title);
    }
}