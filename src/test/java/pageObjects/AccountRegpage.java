package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegpage extends Basepage {

	
	public AccountRegpage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkdpolicy;
	
	@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
	
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgconfirmation;
	
	
	
	
	public void SetFirstName(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	
	
	public void SetLastName(String lname)
	{
		txtlastname.sendKeys(lname);
	}
	
	public void SetEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	
	public void SetTelephone(String number)
	{
		txtTelephone.sendKeys(number);
	}
	
	public void Setpassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	
	public void SetCpassword(String pwd)
	{
		txtconfirmPassword.sendKeys(pwd);
	}
	
	public void Setpolicy()
	{
		chkdpolicy.click();
	}
	
	public void clbtn()
	{
		btncontinue.click();
	
	}
	
	
	public String getconfirmationmsg()
	{
		try {
			return(msgconfirmation.getText());
		}catch(Exception e)
			{
				return(e.getMessage());
			}
		
	}
	
	
}
