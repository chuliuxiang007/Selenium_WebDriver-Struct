package com.autotest.Module;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.autotest.common.objects.TagObject;
import com.autotest.component.Button;
import com.autotest.pageconstant.GlobalConstant;
import com.autotest.utils.CommonOperations;

public class TagManage {
	public static Logger logger = Logger.getLogger(TagManage.class);
	private WebDriver driver=null;
	private String tempJS=null;
	private String result=null;
	
	/**
	 * 标签对象初始化
	 * @param driver
	 */
	public void TagManage(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 验证是否进入标签弹窗
	 * @return
	 */
	public boolean checkTagPanel(){
		String tagHead = CommonOperations.getWindowHead(driver);
		if(!tagHead.equals("添加标签")){
			logger.error("not enter into the tag panel");
			Assert.assertTrue(false);
			return false;
		}
		return true;
	}
	
	/**
	 * 新建标签
	 * @param obj
	 */
	public void add_Tag(TagObject obj){
		//点击新建按钮
		logger.info("新建告警规则： "+obj.tagName);
		Button addTagBtn = new Button(GlobalConstant.newAdd,driver);
		addTagBtn.click();
		checkTagPanel();
		//输入 标签名称
		CommonOperations.inputText("标签", obj.tagName, driver);
		
	}
	

}
