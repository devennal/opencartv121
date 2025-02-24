package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccount extends Basepage {

	public Myaccount(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgheading;
	
	@FindBy(xpath="//aside[@id='column-right']//a[normalize-space()='Logout']") WebElement btnlogout;
	
	
	public boolean isMyaccountexisting()
	{
		try {
		return msgheading.isDisplayed();
		
		}
		catch(Exception e)
		{
			return false;
		}
		
	
	}
	
		
	public void clicklogout()
	{
		btnlogout.click();
			
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
}
