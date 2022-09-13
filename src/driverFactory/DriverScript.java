package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilites.ExcelFileUtil;

public class DriverScript extends AppUtil {
	
	String inputpath="G:\\selenium\\DDT_Framework\\DataTables\\LoginData.xlsx";
	String outputpath="G:\\selenium\\DDT_Framework\\TestResults\\DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void Login() throws Throwable
	{
		report=new ExtentReports("./Report/DataDriven.html");
		//create object for excelfile util
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows
		int rc=xl.rowCount("Login");
		Reporter.log("no of row are::"+rc,true);
		for (int i = 1; i <=rc; i++) {
			test=report.startTest("validate login");
			String user=xl.getCellData("Login", i,0);
			String pass=xl.getCellData("Login", i, 1);
			
			//call login method
			boolean res=FunctionLibrary.verifyLogin(user, pass);
			if (res) {
				//write login success into result cell
				xl.setCellData("Login", i, 2, "Login success", outputpath);
				xl.setCellData("Login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS,"login success");
			}
			else
			{
				//capure screenshot
				File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen,new File("./screens/iteration/"+i+"Loginpage.png"));
				xl.setCellData("Login", i, 2, "Login fail", outputpath);
				xl.setCellData("Login", i, 3, "fail", outputpath);
				test.log(LogStatus.FAIL,"Loginfail" );
				report.flush();
			}
		}
		
	}

}
