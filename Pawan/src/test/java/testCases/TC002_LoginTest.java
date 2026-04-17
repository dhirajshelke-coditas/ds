package testCases;
import static org.testng.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;


public class TC002_LoginTest extends BaseClass {
    
	public Properties p;
	
    @Test
	public void verify_successful_login() throws IOException {
    	
    	
    	logger.info("************Started Test Case 2***********************");
    	
    	try {
			
    	FileReader file= new FileReader("./src//test//resources//config.properties");
    	p = new Properties();
    	p.load(file);
    	
      
    		HomePage hm= new HomePage(driver);
    		hm.clickMyAccounts();
    		logger.info("Clicked on My Account Button");

    		LoginPage login= new LoginPage(driver);
    		logger.info("Providing login details");

    		login.setEmail(p.getProperty("email"));
    		login.setPassword(p.getProperty("pass"));
    		login.clickContinue();
    		
    		MyAccountPage acc= new MyAccountPage(driver);
    		boolean isDisplayed=acc.logoutIsDisplayed();
    		Assert.assertTrue(isDisplayed, "Unsuccessful Login");
    	}
    		
       catch (Exception e) {
    	   Assert.fail();
       }
       
		logger.info("----------------------Finished TC_002----------------------------");
		
    	}
    

    }






















