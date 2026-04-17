package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {


    @Test
	public void verify_account_registration() {
    	
    	logger.info("************Started Test Case 1***********************");
    	
    	try {
    	HomePage hm= new HomePage(driver);
    	hm.clickMyAccounts();
    	logger.info("Clicked on My Accounts Button");

    	hm.clickRegister();
    	logger.info("Clicked on Register Button");

    	
    	AccountRegistrationPage regPage= new AccountRegistrationPage(driver);
    	String password=randomString()+"@"+randomNumber();
    	
    	logger.info("Providing the customer details");

    	regPage.setFirstName(randomString().toUpperCase());
    	regPage.setLastName(randomString().toUpperCase());
    	regPage.setEmailName(randomNumber()+"@gmail.com");
    	regPage.setPhNumber(randomNumber());
    	regPage.setPassword(password);
    	regPage.setConfirmPass(password);
    	regPage.clickAgree();
    	regPage.clickContinue();
    	logger.info("Validating excepted message");
    	String currentTitle=regPage.getTitle();
    	Assert.assertEquals(currentTitle, "Your Account Has Been Created!",  "Account creation message mismatch");
    	}
    	catch (Exception e) {
             logger.error("Test failed..");
             logger.debug("Debug logs..");
             Assert.fail();
    	}
		
    	}


    }






















