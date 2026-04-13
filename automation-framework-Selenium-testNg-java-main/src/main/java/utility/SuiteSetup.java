package utility;

import DriverRepo.ConfigDriver;
import constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class SuiteSetup extends Constants {

    private static final Logger log = LogManager.getLogger(SuiteSetup.class);

    @BeforeTest(alwaysRun = true)
    public void setup() {
        try {
            log.info("===== Test Setup Started =====");

            Properties props = GenericMethods.readPropertiesFile();
            String browser = props.getProperty("browser", "chrome");
            log.info("Browser from config: {}", browser);

            ConfigDriver.setDriver(browser);
            log.info("Driver initialized successfully.");

            initializePages();
            log.info("Pages initialized successfully.");

            GenericMethods.launchUrl(props.getProperty("url"));
            log.info("URL launched: {}", props.getProperty("url"));
            log.info("===== Test Setup Completed =====");

        } catch (Exception e) {
            log.error("@BeforeTest FAILED: {}", e.getMessage(), e);
            throw new RuntimeException("Test setup failed: " + e.getMessage(), e);
        }
    }

    private void initializePages() {
        login        = ObjectRepo.initilizeLoginPage();
        homePage     = ObjectRepo.initilizeHomePage();
        registerPage = ObjectRepo.initializeRegisterPage();
    }

    @AfterTest(alwaysRun = true)
    public void quit() {
        log.info("===== Test Teardown =====");
        ConfigDriver.tearDown();
        // Reset ThreadLocals so next @BeforeTest gets fresh instances
        ObjectRepo.loginPage.remove();
        ObjectRepo.homePage.remove();
        ObjectRepo.registerPage.remove();
    }
}