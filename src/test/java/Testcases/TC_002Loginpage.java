package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Testbase.BaseClass;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.Myaccount;


public class TC_002Loginpage extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_loginpage()
	{
		logger.info("*** Started TC_002Loginpage***");
	try {
		Homepage hp=new Homepage(driver);
		
		hp.clickmyaccount();
		hp.clicklogin();
		
		Loginpage lp=new Loginpage(driver);
		
		lp.setEmail(p.getProperty("email"));
		lp.setpwd(p.getProperty("pwd"));
		lp.clickbtn();
		
		Myaccount myacc=new Myaccount(driver);
		boolean targetpage=myacc.isMyaccountexisting();
		
		Assert.assertTrue(targetpage);
	}
	catch(Exception e)
	{
		Assert.fail();
	}
		logger.info("*** Finished TC_002Loginpage***");
		
	}
				
	
	
}
