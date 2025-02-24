package Testbase;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;   //log4j//
public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		
		
		//loading config.propertyfile//
		
		FileReader file=new FileReader("C:\\workspaces\\opencartv121\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
				
		logger=LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
					{
				cap.setPlatform(Platform.MAC);
			}
			
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			
			switch(br.toLowerCase())
			{
			case "chrome":cap.setBrowserName("chrome"); break;
			case "edge":cap.setBrowserName("MicrosoftEdge"); break;
			default : System.out.println("Invalid browser name"); return;
			
			}
			
			//driver=new RemoteWebDriver(new URL("http://192.168.0.78:4444/wd/hub"),cap);
			
			driver=new RemoteWebDriver(URI.create("http://192.168.0.78:4444/wd/hub").toURL(),cap);
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			default : System.out.println("Invalid browser name"); return;
			
			}
		
		}
		
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appurl")); // reading url from properties file//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass (groups={"Sanity","Regression","Master"})
	public void teardown() 
	{
	    
	  driver.quit();
	    
	}
	
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
				
	}
	
	public String randomeNumber()
	{
		String generatednum=RandomStringUtils.randomNumeric(10);
		return generatednum;
				
	}
	
	public String randomeAlphanumeric()
	{
		
		String generatedalpha=RandomStringUtils.randomAlphabetic(5);
		String generatednum=RandomStringUtils.randomAlphanumeric(5);
		return (generatedalpha+"@"+generatednum);
				
	}

	
	public String captureScreen(String tname) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesscreenshot=(TakesScreenshot) driver;
		File sourcefile=takesscreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath= "C:\\workspaces\\opencartv121\\screenshots\\"+tname+"-"+timestamp+".png";
		File targetfile=new File(targetFilePath);
		
		sourcefile.renameTo(targetfile);
		return targetFilePath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
