package TechoSoft.TEST;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class VerifyTitle {
	
	String exePath = "C:\\Users\\david\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;
	
	
	@BeforeClass
	public void init(){
		
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
	}
	
  @Test
  public void f() {
	  driver.get("http://stage.techocloud.com:8080/Ework/InsertTreatUserInfoServlet");
	assertEquals(driver.getTitle(), "EWORK");
  }
  @BeforeMethod
  public void beforeMethod() {
	  
	  System.out.println("lets to test");
  }
  
  @AfterClass
  public void finalize(){
	  driver.quit();
  }
}
