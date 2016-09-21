package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;





public class FireFoxTest {

	public static void main(String[] args){
		WebDriver driver;
		String baseUrl;
		//System.setProperty("webdriver.firefox.bin", "H:\\Program Files\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		
		baseUrl = "http://www.sogou.com/";
		driver.get(baseUrl+"/");
		driver.findElement(By.id("query")).sendKeys("abc");
		driver.findElement(By.id("stb")).click();
	}
}
