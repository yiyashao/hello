package TechoSoft.TEST;

import org.testng.annotations.Test;

import junit.framework.Assert;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
    }

    /**
     * Rigourous Test :-)
     */
    @Test(priority = 2)
	public void testApp()
    {
    	String exePath = "C:\\Users\\david\\Downloads\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://stage.techocloud.com:8080/Ework/InsertTreatUserInfoServlet");
		// Storing Page Source in String variable
		String pageSource = driver.getPageSource();
		 
		// Storing Page Source length in Int variable
		int pageSourceLength = pageSource.length();
		


		WebElement element = driver.findElement(By.name("ework_username"));
		element.sendKeys("this is david");

		//driver.findElement(By.tagName("input")).getText().contains("Submitted");
		driver.findElement(By.xpath("//input[@value='提交']")).click();

		//wait for alert
		WebDriverWait wait = new WebDriverWait(driver, 5); 
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String s = alert.getText();
			System.out.println(s);
			alert.accept();
		} catch (Exception e) {
			System.out.println("didnt see the alert!");
		}
		// Printing length of the Page Source on console
		System.out.println("Total length of the Pgae Source is : " + pageSourceLength);
		driver.close();
        //AssertJUnit.assertTrue( true );
    }
    
    @Test(priority = 1)
    public void anotherTest(){
    	//Assert.fail();
    	assertTrue(false);
    }
    
    @Test(priority = 0)
    public void anotherATest(){
    	assertTrue(true);
    }
}
