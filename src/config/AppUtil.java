package config;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
	public static Properties config;
	@BeforeTest
	public static void setUp() throws Throwable
	{
		config= new Properties();
		config.load(new FileInputStream("G:\\selenium\\DDT_Framework\\PropertyFile\\Environment.properties"));
		if (config.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			
		}
		else
			
		{
			Reporter.log("browser value not matching",true);
		}
	}
		@AfterTest
		public static void tearDown()
		{
			driver.close();
		
	}
	

}