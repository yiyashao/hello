package TechoSoft.TEST;

import org.testng.annotations.Test;

import TechoSoft.Helper.TestHelper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;


/*
 * For TC: http://104.129.56.123:8080/browse/TC-16
	客户姓名：李四
	客户电话：13475942549
	客户地址：中国大连高新园区龙头分园盛龙街1号
	备注：85平米
 */
public class TC903UserEntry extends TestHelper {
	
	//TEST DETAIL
	String TestURL = "http://175.170.130.20:9107/EworkForTreat/InsertTreatUserInfo";
	String testCaseId = "TC-16";
	//TEST DATA BELOW
	String wrongUser = "wronguser";
	String wrongPsw = "wrongpassword";
	String correctUser = "admin";
	String correctPsw = "admin";
	String errorMessage_emptyUser = "客户姓名不能为空!";
	String errorMessage_emptyPhone = "号码不能为空";
	String errorMessage_emptyAddress = "地址不能为空!";
	String errorMessage_wrongPhone = "号码有误";
	String errorMessage_userExist = "已经存在此用户";
	String alertMessage_userAdded = "添加成功";
	String name = "加拿大李四";
	String phone = "13475942549";
	String address = "中国大连高新园区龙头分园盛龙街1号"; 
	String comments = "85平米";
	int batchAddNum = 10;
	
	public WebDriver driver;
	String exePath = "C:\\Users\\david\\Downloads\\chromedriver_win32\\chromedriver.exe";
	
	WebElement field_name;
	WebElement field_phone;
	WebElement field_address;
	WebElement field_comments;
	WebElement submitButton;
	
	
	/*
	 * get to URL without login, redirect to login page, and then login to main page
	 */
	@Test(enabled = true)
	public void Step01() {
		assertEquals(driver.getTitle(), "登录界面");
		WebElement element = driver.findElement(By.name("txtUserName"));
		element.sendKeys(correctUser);
		element = driver.findElement(By.name("txtUserPass"));
		element.sendKeys(correctPsw);
		driver.findElement(By.xpath("//input[@value='登录']")).click();

		mySleep();
		assertEquals(driver.getTitle(), "主界面");
	}
	
	/*
	 * empty client name | empty client phone | empty address, submit and check alert message
	 */
	@Test(enabled = true)
	public void Step02() {
		//assertEquals(driver.getTitle(), "客户录入");
		field_name = driver.findElement(By.name("ework_username"));
		field_phone = driver.findElement(By.name("user_phone"));
		field_address = driver.findElement(By.name("user_address"));
		field_comments = driver.findElement(By.name("comments"));
		submitButton = driver.findElement(By.id("button_submit"));
		
		//empty client name, then submit
		clearElements(field_name, field_phone, field_address, field_comments);
		field_phone.sendKeys(phone);
		field_address.sendKeys(address);
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_emptyUser);
		//empty phone, then submit
		clearElements(field_name, field_phone, field_address, field_comments);
		field_name.sendKeys(name);
		field_address.sendKeys(address);
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_emptyPhone);		
		//empty address, then submit
		clearElements(field_name, field_phone, field_address, field_comments);
		field_name.sendKeys(name);
		field_phone.sendKeys(phone);;
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_emptyAddress);	
		//mySleep();
	}
	
	/*
	 * using wrong format phone numbers, return error message
	 */
	@Test(enabled = true)
	public void Step03() {		
		//assertEquals(driver.getTitle(), "客户录入");
		WebElement field_name = driver.findElement(By.name("ework_username"));
		WebElement field_phone = driver.findElement(By.name("user_phone"));
		WebElement field_address = driver.findElement(By.name("user_address"));
		WebElement field_comments = driver.findElement(By.name("comments"));
		WebElement submitButton = driver.findElement(By.id("button_submit"));
		//longer than 11 digits
		clearElements(field_name, field_phone, field_address, field_comments);
		field_name.sendKeys(name);
		field_phone.sendKeys("12345678901234"); //longer than 11
		field_address.sendKeys(address);
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_wrongPhone);	
		//shorter than 11 digits
		clearElements(field_name, field_phone, field_address, field_comments);
		field_name.sendKeys(name);
		field_phone.sendKeys("1234567"); //longer than 11
		field_address.sendKeys(address);
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_wrongPhone);	
		//not only numbers
		clearElements(field_name, field_phone, field_address, field_comments);
		field_name.sendKeys(name);
		field_phone.sendKeys("123456AVC01"); //longer than 11
		field_address.sendKeys(address);
		field_comments.sendKeys(comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_wrongPhone);	
	}
	
	/*
	 * add correct client information, system takes them fine
	 */
	@Test(enabled = true)
	public void Step04() {		
		String randPhoneNum = "";
		//assertEquals(driver.getTitle(), "客户录入");
		field_name = driver.findElement(By.name("ework_username"));
		field_phone = driver.findElement(By.name("user_phone"));
		field_address = driver.findElement(By.name("user_address"));
		field_comments = driver.findElement(By.name("comments"));
		WebElement submitButton = driver.findElement(By.id("button_submit"));
		//correct input then use it to input again
		randPhoneNum = generatePhoneNum();
		fillUserInfo(name, randPhoneNum, address, comments);
		submitButton.click();
		checkAlertMessage(driver, alertMessage_userAdded);	
		clearElements(field_name, field_phone, field_address, field_comments);
		fillUserInfo(name, randPhoneNum, address, comments);
		submitButton.click();
		checkAlertMessage(driver, errorMessage_userExist);	
		//now add some more users and check : 10 more for now.
		for(int i = 0; i < batchAddNum; i ++){
			randPhoneNum = generatePhoneNum();
			fillUserInfo(name, randPhoneNum, address, comments);
			submitButton.click();
			checkAlertMessage(driver, alertMessage_userAdded);
			System.out.println("BATCH USER: " + i + " -> phone: " + randPhoneNum + " added!");
		}
	}

@BeforeMethod
  public void beforeMethod() {
	  driver.get(TestURL);
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
  }
  
  public void clearElements(WebElement element1, WebElement element2, WebElement element3, WebElement element4){
	  element1.clear();
	  element2.clear();
	  element3.clear();
	  element4.clear();
  }
  public void fillUserInfo(String name, String phone, String address, String comments){
	  if(field_name == null){
		  System.out.println("Field_Name is not initized, please check your code!");
		  return;
	  }else{
			clearElements(field_name, field_phone, field_address, field_comments);
			field_name.sendKeys(name);
			field_phone.sendKeys(phone); //longer than 11
			field_address.sendKeys(address);
			field_comments.sendKeys(comments);
	  }
	  
	  
  }
  

}
