package AlarmRuleModule;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.autotest.common.objects.AlarmObject;
import com.autotest.pageconstant.GlobalConstant;
import com.autotest.pageobjects.LoginPage;
import com.autotest.pageobjects.MainPage;
import com.autotest.pageobjects.UCenterPage;

public class AlarmFunctionTest {
	
	@BeforeSuite(alwaysRun = true)
	public void login(){
		WebDriver webDriver = null;
		LoginPage login = new LoginPage(webDriver);
		login.loginDefault();
		Reporter.log("login the cloud view");
		GlobalConstant.driver=login.webDriver;	
	}
	
	@Test(groups={"checkElement"})
	public void ValAlarmRuleElement(){
		MainPage main = new MainPage(GlobalConstant.driver);
		Assert.assertTrue(main.isMainPage());
		Reporter.log("login success");
		main.clickUserCenter();
		UCenterPage uCenter =new UCenterPage(GlobalConstant.driver);
		Assert.assertTrue(uCenter.existUCenter());
		Reporter.log("enter into the User Center");
		uCenter.clickUCenterBtn(3);
		uCenter.checkAlamPage();
	}
	
	@Test(groups={"alarm_basic_function"})
	public void addAlarmRuleTest(){
		UCenterPage uCenter =new UCenterPage(GlobalConstant.driver);
		//新建告警规则
		List<AlarmObject> expectObj = new  ArrayList<AlarmObject>();
		//加载告警规则数据
		String filePath=GlobalConstant.dataPath+"AlarmRule";
		String fileName="AlarmInfo.xlsx";
		String sheetName="NormalRule";
		expectObj =uCenter.loadAlarmData(filePath, fileName, sheetName);		
		uCenter.addAlarmRule(expectObj);
		uCenter.valAlarmRule(expectObj);
	}
	
	@Test(groups={"alarm_basic_function"})
	public void editAlarmRuleTest(){
		UCenterPage uCenter =new UCenterPage(GlobalConstant.driver);
		//编辑告警规则
		List<AlarmObject> expectObj = new  ArrayList<AlarmObject>();
		//加载告警规则数据
		String filePath=GlobalConstant.dataPath+"AlarmRule";
		String fileName="AlarmInfo.xlsx";
		String sheetName="EditRule";
		expectObj =uCenter.loadAlarmData(filePath, fileName, sheetName);		
		uCenter.editAlarmRule(expectObj);
		uCenter.valAlarmRule(expectObj);		
	}
	
	@Test(groups={"alarm_basic_function"})
	public void deleteAlarmRuleTest(){
		UCenterPage uCenter =new UCenterPage(GlobalConstant.driver);
		//删除告警规则
		List<AlarmObject> expectObj1 = new  ArrayList<AlarmObject>();
		//加载告警规则数据
		String filePath=GlobalConstant.dataPath+"AlarmRule";
		String fileName="AlarmInfo.xlsx";
		String sheetName="DeleteRule";
		expectObj1 =uCenter.loadAlarmData(filePath, fileName, sheetName);
		uCenter.deleteAlarmRule(expectObj1);
		String sheetName2="EditRule";
		List<AlarmObject> expectObj2 = new  ArrayList<AlarmObject>();
		expectObj2 =uCenter.loadAlarmData(filePath, fileName, sheetName2);
		uCenter.valAlarmRule(expectObj2);
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void quit(){
		GlobalConstant.driver.quit();
	}
	

	
}
