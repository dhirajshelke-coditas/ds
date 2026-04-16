package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	public WebDriver driver;
	
	public Logger logger;

	@BeforeClass
	public void setup() {
	    
		logger= LogManager.getLogger(this.getClass());
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}
	
	@AfterClass
//	public void teardown() {
//		driver.quit();
//
//	}

    public String randomString() {
    	return RandomStringUtils.randomAlphabetic(5);
    }
	
    public String randomNumber() {
    	return RandomStringUtils.randomNumeric(10);
    }
    
    public String password() {
    	return randomString()+"@"+randomNumber();
    }
}
