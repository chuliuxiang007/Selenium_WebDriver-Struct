package TagModule;

import org.testng.annotations.Test;

import com.autotest.common.objects.TagObject;
import com.autotest.pageconstant.GlobalConstant;
import com.autotest.pageobjects.LoginPage;
import com.autotest.pageobjects.UCenterPage;

import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

public class TagFunctionTest {
  @Test(groups={"tag_basic_function"})
  public void addTagTest() {
		UCenterPage uCenter =new UCenterPage(GlobalConstant.driver);
		//新建标签
		List<TagObject> expectObj = new  ArrayList<TagObject>();
		//加载告警规则数据
		String filePath=GlobalConstant.dataPath+"TagInfo";
		String fileName="TagInfo.xlsx";
		String sheetName="tagInfo";
		expectObj =uCenter.loadTagData(filePath, fileName, sheetName);	
		
		uCenter.addAlarmRule(expectObj);
		uCenter.valAlarmRule(expectObj);
	  
  }
  @BeforeSuite
  public void beforeSuite() {
		WebDriver webDriver = null;
		LoginPage login = new LoginPage(webDriver);
		login.loginDefault();
		Reporter.log("login the cloud view");
		GlobalConstant.driver=login.webDriver;
  }

  @AfterSuite
  public void afterSuite() {
	  GlobalConstant.driver.quit();
  }

}
