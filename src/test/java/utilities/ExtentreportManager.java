package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Testbase.BaseClass;

public class ExtentreportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	public void onStart(ITestContext testcontext) {
		
		
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp//
		
			repname="Test-Report-"+timestamp + ".html";
			sparkReporter = new ExtentSparkReporter("C:\\workspaces\\opencartv121\\reports\\"+repname); //specify the location report//
			
			sparkReporter.config().setDocumentTitle("opencart Automatic Report"); //Title report//
			sparkReporter.config().setReportName("open cart Funtional testing"); //Name of the report//
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Submodule", "Customers");
			extent.setSystemInfo("User Name",System.getProperty("User.name"));
			extent.setSystemInfo("Enviroment info", "QA");
			
			
			String os=testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Opearting System", os);
			

			String browser=testcontext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Opearting System", browser);
			
			List<String> includegroups=testcontext.getCurrentXmlTest().getIncludedGroups();
			if(!includegroups.isEmpty()) {
				extent.setSystemInfo("Groups", includegroups.toString());
			}		
			
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in the report
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch(Exception e1) {
			e1.printStackTrace();
		}
				
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testcontext)
	{
		extent.flush();
		
		String pathOfExtentReport="C:\\workspaces\\opencartv121\\reports\\"+repname;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
