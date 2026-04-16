package testBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC001_AccountRegistrationTest extends BaseClass {


    @Test
	public void verify_account_registration() {
    	
    	logger.info("************Started Test Case 1***********************");
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
    	regPage.setPhNumber(randomNumber()+"@gmail.com");
    	regPage.setPassword(password);
    	regPage.setConfirmPass(password);
    	regPage.clickAgree();
    	regPage.clickContinue();
    	logger.info("Validating excepted message");
    	String confirmationText="Your Account Has Been Created!";
    	String currentTitle=regPage.getTitle();
    	Assert.assertEquals(confirmationText, currentTitle, "Your account has been created successfully");
    	
		
	}


}






















