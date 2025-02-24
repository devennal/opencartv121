package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {
	
	
	
	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	

	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkmyaccount;
	

	@FindBy(xpath="//div[@id='top-links']//a[normalize-space()='Register']") WebElement linkregister;
	
	@FindBy(linkText="Login") WebElement linklogin;
	
	
	public void clickmyaccount()
	{
		linkmyaccount.click();
	}
	
	
	public void clickRegister()
	{
		linkregister.click();
	}
	
	public void clicklogin()
	{
		linklogin.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
