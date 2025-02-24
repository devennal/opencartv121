package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Testbase.BaseClass;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.Myaccount;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass {

    @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("*** Started TC_003LoginDDT ***");
    
        Homepage hp = new Homepage(driver);
        hp.clickmyaccount();
        hp.clicklogin();
        
        Loginpage lp = new Loginpage(driver);
        
        // ✅ FIX: Use direct parameters instead of properties file
        lp.setEmail(email);
        lp.setpwd(pwd);
        lp.clickbtn();
        
        Myaccount myacc = new Myaccount(driver);
        boolean targetpage = myacc.isMyaccountexisting();
    
        if (exp.equalsIgnoreCase("valid")) {
            if (targetpage) {
                myacc.clicklogout();	
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false, "Login should be successful, but it failed.");
            }
        } else { // Handling "Invalid" case
            if (targetpage) {
                myacc.clicklogout();	
                Assert.assertTrue(false, "Login should have failed, but it succeeded.");
            } else {
                Assert.assertTrue(true);
            }
        }
    }
}

	
			
	
	
		

	
	
	
	

