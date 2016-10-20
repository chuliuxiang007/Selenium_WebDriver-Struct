package LoginMainTest;

import org.testng.annotations.Test;

import com.autotest.pageobjects.LoginPage;
import com.autotest.pageobjects.MainPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;

public class LoginTest {
	
  public WebDriver driver = null;
	
  @Test
  @Parameters({"username","password"})
  public void testLogin(String userName, String passWord){
	  LoginPage login = new LoginPage(driver);
	  driver=login.webDriver;	  
	  login.login(userName, passWord);
	  try {
		Thread.sleep(5000);
		MainPage main = new MainPage(driver);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  
  }
  
  @Test(dependsOnMethods = {"testLogin"})
  public void testMainPage(){
		MainPage main = new MainPage(driver);
		Assert.assertTrue(main.isMainPage());	
  }


}
