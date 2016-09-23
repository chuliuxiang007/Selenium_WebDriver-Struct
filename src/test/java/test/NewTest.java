package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autotest.pageobjects.LoginPage;
import com.autotest.pageobjects.MainPage;

public class NewTest {
  @Test
  public void f() throws InterruptedException {
		WebDriver webDriver = null;
		LoginPage login = new LoginPage(webDriver);
		webDriver=login.webDriver;
		try {
			login.login("chuliuxiang", "a12345678");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
		MainPage main = new MainPage(login.webDriver);
		//Assert.assertTrue(main.isMainPage());		
		System.out.println("the result is "+main.isMainPage());
		main.clickUserCenter();
  }
}
