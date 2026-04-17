package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) throws IOException {
		
		
		FileReader file= new FileReader("./src//test//resources//config.properties");
		
		p= new Properties();
		p.load(file);
		//Loading config.property file
		
		logger= LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase()) {
		case("chrome"): driver= new ChromeDriver(); break;
		case("edge"): driver= new EdgeDriver(); break;
		case("firefox"): driver= new FirefoxDriver(); break;
		default : throw new IllegalArgumentException("Invalid browser: " + browser);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("url"));  //reading url from property file
	}
	
//	@AfterClass
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
