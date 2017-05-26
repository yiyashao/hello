package TechoSoft.TEST;

import org.testng.annotations.Test;

import TechoSoft.Helper.TestHelper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

/*
 * For TC: http://104.129.56.123:8080/browse/TC-14
 * 
 */
public class TC901AdminLogin extends TestHelper{
	
	//TEST DETAIL
	String TestURL = BASE_URL + "EworkForTreat/Login"; 
	String exePath = "C:\\Users\\david\\Downloads\\chromedriver_win32\\chromedriver.exe";
	String testCaseId = "TC-14";
	//TEST DATA BELOW
	String wrongUser = "wronguser";
	String wrongPsw = "wrongpassword";
	String correctUser = "admin";
	String correctPsw = "admin";
	String errorMessage = "用户名或密码错误";
	
	public WebDriver driver;
	
	/*
	 * empty user name then click login, return error message
	 */
	@Test(enabled = true)
	public void Step01() {
		
		driver.findElement(By.xpath("//input[@value='登录']")).click();
		checkAlertMessage(driver, errorMessage);
	}
	/*
	 * fill user name & empty password, then click login, return error message
	 */
	@Test(enabled = true)
	public void Step02() {
		WebElement element = driver.findElement(By.name("txtUserName"));
		element.sendKeys(wrongUser);
		driver.findElement(By.xpath("//input[@value='登录']")).click();
		checkAlertMessage(driver, errorMessage);
	}
	/*
	 * wrong user name / password login, then click login, return error message
	 */
	@Test(enabled = true)
	public void Step03() {
		WebElement element = driver.findElement(By.name("txtUserName"));
		element.sendKeys(wrongUser);
		element = driver.findElement(By.name("txtUserPass"));
		element.sendKeys(wrongPsw);
		driver.findElement(By.xpath("//input[@value='登录']")).click();
		checkAlertMessage(driver, errorMessage);
	}
	/*
	 * correct user name /password login, return error message
	 */
	@Test
	public void Step04() {
		WebElement element = driver.findElement(By.name("txtUserName"));
		element.sendKeys(correctUser);
		element = driver.findElement(By.name("txtUserPass"));
		element.sendKeys(correctPsw);
		driver.findElement(By.xpath("//input[@value='登录']")).click();

		mySleep();
		assertEquals(driver.getTitle(), "主界面");
		element = driver.findElement(By.id("button_exit"));
		element.click();
		mySleep();
		assertEquals(driver.getTitle(), "登录界面");
	
	}
  

	@BeforeMethod
	public void beforeMethod() {
		driver.get(TestURL);
		assertEquals(driver.getTitle(), "登录界面");
	}

  @AfterMethod
  public void afterMethod() {
	  
  }

  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		System.out.println("Start Testing TC: " + testCaseId);
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
	  System.out.println("Finish Testing TC: " + testCaseId);
  }
  
//  public void checkAlertMessage(String alertMessage){
//	//wait for alert
//			WebDriverWait wait = new WebDriverWait(driver, 5); 
//			try {
//				wait.until(ExpectedConditions.alertIsPresent());
//				Alert alert = driver.switchTo().alert();
//				String s = alert.getText();
//				
//				assertEquals(s, alertMessage);
//				System.out.println("ALERT ERROR Message: " + s);
//				alert.accept();
//			} catch (Exception e) {
//				System.out.println("didnt see the alert!");
//			}
//  }
//  
//  public void mySleep(){
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//  }

}
