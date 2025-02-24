package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Testbase.BaseClass;
import pageObjects.AccountRegpage;
import pageObjects.Homepage;

public class TC_001AccountRegistration extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void verify_account_reg()
	{
		
		logger.info("**** Starting TC_001AccountRegistration**** ");
		try
		{
		Homepage hp=new Homepage(driver);
		hp.clickmyaccount();
		logger.info("**** Clicked on my Account link**** ");
		hp.clickRegister();
		
		logger.info("**** Clicked on my Register link**** ");
		AccountRegpage rgpage=new AccountRegpage(driver);
		
		
		logger.info("Providing Customer details ");
		rgpage.SetFirstName(randomeString().toUpperCase());
		rgpage.SetLastName(randomeString().toUpperCase());
		rgpage.SetEmail(randomeString()+"@gmail.com");
		rgpage.SetTelephone(randomeNumber());
		
		String ps=randomeAlphanumeric();
		
		rgpage.Setpassword(ps);
		rgpage.SetCpassword(ps);
		
		rgpage.Setpolicy();
		rgpage.clbtn();	
		
		
		logger.info("Validation expected message.. ");
		String msgc=rgpage.getconfirmationmsg();
		if(msgc.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(msgc, "Your Account Has Been Created!!!");
		}
		catch (Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("**** Finished TC_001AccountRegistration**** ");
		
		}
	
	
	
	
	
}
