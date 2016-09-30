package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.autotest.pageobjects.LoginPage;

public class TestLogin {

	@Test
	public void test() throws InterruptedException {
		WebDriver webDriver = null;
		LoginPage login = new LoginPage(webDriver);
		login.login("chuliuxiang", "a12345678");
	}

}
