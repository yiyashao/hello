package TechoSoft.Helper;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {

	
	
	public void mySleep(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  public String generatePhoneNum() {
		String result = "139";
		Random rand = new Random();
		for(int i=0; i < 8; i ++){
			int ran = rand.nextInt(10);
			result += ran;
		}
		System.out.println("system ramdom digits are: " + result);
		return result;
	}
	public void checkAlertMessage(WebDriver driver, String alertMessage){
		//wait for alert
		WebDriverWait wait = new WebDriverWait(driver, 5); 
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String s = alert.getText();
			
			assertEquals(s, alertMessage);
			System.out.println("ALERT ERROR Message: " + s);
			alert.accept();
		} catch (Exception e) {
			System.out.println("didnt see the alert!");
		}
	}
}
