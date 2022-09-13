package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	
	public static boolean verifyLogin(String userName,String password)
	{
		driver.get(config.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(userName);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
		String expected="dashboard";
		String actual=driver.getCurrentUrl();
		if (actual.contains(expected)) {
			Reporter.log("login success",true);
			return true;
		}
		else
		{
			//capture error message
			String errormessage=driver.findElement(By.xpath(config.getProperty("ObjError"))).getText();
			Reporter.log(errormessage,true);
			return false;
					
		}
	}
	
	

}
