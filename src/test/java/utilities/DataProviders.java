package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


	public class DataProviders {

	// DataProvider
	    @DataProvider(name="LoginData")
	    public String [][] getData() throws IOException 
	    {
	        String path="C:\\workspaces\\opencartv121\\testdata\\Book 4.xlsx"; // Taking x1 file from testData

	        Excelutility xlutil = new Excelutility(path);// Creating an object for XLutility

	        int totalrows=xlutil.getRowCount("Sheet1");
	        int totalcols=xlutil.getCellCount("Sheet1", 1);

	        String logindata[][]=new String[totalrows][totalcols]; // Created two dimensional array

	        for(int i=1;i<=totalrows;i++) // Read the data from xl, storing in array
	        {
	            for(int j=0;j<totalcols;j++) // i=1 rows is 1 col
	            {
	                logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // i=0
	            }
	        }
	        return logindata; // Returning two dimensional array
	    }
	
	
	}	
	
	
	

