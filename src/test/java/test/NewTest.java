package test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autotest.common.objects.AlarmObject;
import com.autotest.pageobjects.LoginPage;
import com.autotest.pageobjects.MainPage;
import com.autotest.pageobjects.UCenterPage;
import com.autotest.utils.ReadExcelUtil;

public class NewTest {
  @Test
  public void f() throws InterruptedException, IOException {
		WebDriver webDriver = null;
		LoginPage login = new LoginPage(webDriver);
		webDriver=login.webDriver;
		Object[][] readData=ReadExcelUtil.readExcel(".//src/test/resources/TestData/UserInfo", 
				"UserInfo.xlsx", "UserLogin");
		String userName = readData[0][0].toString();
		String passWord = readData[0][1].toString();
		System.out.println(userName);
		try {
			login.login(userName, passWord);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
		MainPage main = new MainPage(login.webDriver);
		Assert.assertTrue(main.isMainPage());		
		System.out.println("the result is "+main.isMainPage());
		main.clickUserCenter();
		UCenterPage uCenter =new UCenterPage(webDriver);
		Assert.assertTrue(uCenter.existUCenter());	
		uCenter.clickUCenterBtn(3);
		//新建告警规则
		List<AlarmObject> abc = new  ArrayList<AlarmObject>();
		//加载告警规则数据
		String filePath=".//src/test/resources/TestData/AlarmRule";
		String fileName="AlarmInfo.xlsx";
		String sheetName="NormalRule";
		abc =uCenter.loadAlarmData(filePath, fileName, sheetName);		
		uCenter.addAalrmRule(abc);
		uCenter.valAalarmRule(abc);
		
		
		
  }
}
