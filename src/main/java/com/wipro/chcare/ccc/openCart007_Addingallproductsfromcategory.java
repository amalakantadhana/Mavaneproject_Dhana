package com.wipro.chcare.ccc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ReadExcel.ReadingExcel;

public class openCart007_Addingallproductsfromcategory
{
private WebDriver driver;
private static int WAIT_TIME = 10000 ;
RegistrationPage RP;

@BeforeClass
public void initialize()
{
	System.setProperty("webdriver.chrome.driver","D:\\Selenium_Jars\\Jars\\chromedriver_win32\\chromedriver.exe");
	driver= new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.MILLISECONDS);
}

@Test(description = "Launch openCart application")
public void openApp()
{
	 driver.get("http://10.207.182.108:81/opencart/");
	  	  System.out.println("opencart app launched");

}

//Data Provider
@DataProvider(name="Mydatprovide")
public static  Object[][] ReadingData() throws IOException{
Object[][] obj=ReadingExcel.readExcel("LoginPage","D:\\SELENIUM_PRACTISE\\TestData.xlsx");
return obj;	
}	
@DataProvider(name="Billingdetails")
public static  Object[][] ReadingBillingData() throws IOException{
Object[][] obj=ReadingExcel.readExcel("TC04_billing","D:\\SELENIUM_PRACTISE\\TestData.xlsx");
return obj;	
}	

@Test (dataProvider="Mydatprovide",dependsOnMethods = "openApp") 	 
public void loginApp(String Username,String Password, String changequantity, String Faultycomment) throws IOException
{
	 RP = new RegistrationPage(driver);
	 RP.login.click();
	  // driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(Username);
	   RP.username.sendKeys(Username);
	  System.out.println(Username);
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
		RP.password.sendKeys(Password);
		System.out.println(Password);
		RP.loginbutton.click();
		//driver.findElement(By.xpath("//input[@class='button']")).click();
		   
		   System.out.println("Logged in successfully");
		/*=====================
		 * Check point: User's first name as a link
		 */
			String e=driver.findElement(By.linkText("dhana")).getText();
				Assert.assertEquals("dhana", e);
		System.out.println("Validation is passed");
}
   @Test(dependsOnMethods = "loginApp")
   public void addingAllProducts() throws InterruptedException
   {
	   ArrayList<String> prodMessage = new ArrayList<String>();
	   driver.findElement(By.linkText("Phones & PDAs")).click();
	 List<WebElement> Prod = driver.findElements(By.xpath("//div[@id='content']/div[4]/div"));
	   		  int psize= Prod.size();
	   		  System.out.println(psize);
	   		  
	   		 for(int i=1;i<=psize;i++)
	   		 {
	   			 driver.findElement(By.xpath("//*[@id='content']/div[4]/div["+ i+ "]/div[1]/div[1]/input")).click();
	   			 System.out.println(" Clicked on the product" + i );
	   			 Thread.sleep(2000);
	   			prodMessage.add(driver.findElement(By.xpath(".//*[@id='notification']/div")).getText());
	   			Thread.sleep(2000);
	   			System.out.println("The success message is : " + prodMessage);
	   		 	driver.findElement(By.xpath("//div[@id='notification']/div/img")).click();
	   		 }
	   		System.out.println("The success message is : " + prodMessage.get(0));
	   		driver.findElement(By.linkText("Shopping Cart")).click();
	   		System.out.println(" Shopping cart link is clicked");
	   		driver.findElement(By.linkText("Checkout")).click();
	   		System.out.println(" Checkout button is clicked");
	   		driver.findElement(By.xpath("//div[@id='notification']/div/img")).click();
	   		System.out.println("Notification is clicked");
	   		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/a/img")).click();
	 		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/input[1]")).clear();
	   		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[2]/td[4]/input[1]")).clear();
	 		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[3]/td[4]/input[1]")).clear();
	   		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/input[1]")).sendKeys("1");
	   		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[2]/td[4]/input[1]")).sendKeys("1");
	   		driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[3]/td[4]/input[1]")).sendKeys("1");
	   		driver.findElement(By.linkText("Checkout")).click();
	   		System.out.println(" Checkout button is clicked");
   }
      
   
}
