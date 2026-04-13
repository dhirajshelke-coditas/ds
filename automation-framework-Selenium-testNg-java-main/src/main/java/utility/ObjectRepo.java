package utility;

import DriverRepo.ConfigDriver;
import pages.HomePage;
import pages.Login;
import pages.RegisterPage;

public class ObjectRepo {

   public static ThreadLocal<Login> loginPage = new ThreadLocal<>();
    public static ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    public static ThreadLocal<RegisterPage> registerPage = new ThreadLocal<>();

    public static Login initilizeLoginPage() {
        try {
            if(loginPage.get() == null) {
                loginPage.set(new Login(ConfigDriver.getDriver()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return loginPage.get();
    }

    public static HomePage initilizeHomePage() {
        try {
            if(homePage.get() == null) {
                homePage.set(new HomePage(ConfigDriver.getDriver()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return homePage.get();
    }
    public static RegisterPage initializeRegisterPage() {
        try {
            if (registerPage.get() == null) {
                registerPage.set(new RegisterPage(ConfigDriver.getDriver()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return registerPage.get();
    }

}
