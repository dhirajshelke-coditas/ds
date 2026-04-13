package DriverRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConfigDriver {

    static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(String browserName) {
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name cannot be null");
        }
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.get().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void tearDown() {
        if(driver.get()!= null) {
            driver.get().quit();
        }

    }


}
