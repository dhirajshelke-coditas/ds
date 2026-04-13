package constants;

import pages.HomePage;
import pages.Login;

import pages.RegisterPage;

import java.io.File;

public class Constants {

	public static final String extentReportsPath = System.getProperty("user.dir")+ File.separator + "reports"+ File.separator + "ExtentReport.html";
	
     public static final String configPropPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +File.separator + "resources" + File.separator + "config.properties";

     public static final String url = "https://ecommerce-playground.lambdatest.io/";

     protected static Login login;
     protected static HomePage homePage;
 //    protected static HomePage homePage;
     protected static RegisterPage registerPage;
     
}
