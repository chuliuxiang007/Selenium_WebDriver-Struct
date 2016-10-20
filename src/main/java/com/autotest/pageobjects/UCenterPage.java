package com.autotest.pageobjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.autotest.ExtJSPage;
import com.autotest.Module.AlarmRule;
import com.autotest.Module.TagManage;
import com.autotest.common.ValidateObj;
import com.autotest.common.objects.AlarmObject;
import com.autotest.common.objects.TagObject;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.ReadExcelUtil;

public class UCenterPage extends ExtJSPage {
	
	public static Button personInfoBtn;
	public static Button messageManBtn;
	public static Button alarmRuleBtn;
	public static Button tagManBtn;
	public static Button adviceBackBtn;
	
	private String componentText="-inputEl";

	public UCenterPage(WebDriver webDriver) {
		super(webDriver);
		getButton();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 校验是否跳转到用户中心界面
	 * @return
	 */
	public boolean existUCenter(){
		getButton();
		boolean flag=false;
		if(personInfoBtn.existButton() && messageManBtn.existButton()
				&& alarmRuleBtn.existButton() && tagManBtn.existButton() && adviceBackBtn.existButton()){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 获取用户中心各个模块的按钮
	 */
	public void getButton(){
		personInfoBtn= new Button(UCenterPageConstant.PersonInfo, webDriver);
		messageManBtn=new Button(UCenterPageConstant.MessageManage, webDriver);
		alarmRuleBtn=new Button(UCenterPageConstant.AlarmRule, webDriver);
		tagManBtn= new Button(UCenterPageConstant.TagManage, webDriver);
		adviceBackBtn= new Button(UCenterPageConstant.AdviceBack, webDriver);
	}
	
	/**
	 * 跳转用户中心各个模块
	 * 
	 * 
	 * 个人信息：1； 消息管理：2 ；告警规：3；标签管理：4；用户反馈：5
	 * @param btnID
	 */
	public void clickUCenterBtn(int btnID){
		getButton();
		switch(btnID){
			case 1:
				personInfoBtn.click();
				break;
			case 2:
				messageManBtn.click();
				break;
			case 3:
				alarmRuleBtn.click();
				break;
			case 4:
				tagManBtn.click();
				break;
			case 5:
				adviceBackBtn.click();
				break;
			default:
				System.out.println("the btn id is false");
				break;			
		}
	}
	
	//告警规则处理
	/**
	 * 告警数据加载处理
	 * @param filePath
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public List<AlarmObject> loadAlarmData(String filePath, String fileName, String sheetName){
		List<AlarmObject> list = new ArrayList<AlarmObject>();
		Object[][] readData=null;
		try {
			readData = ReadExcelUtil.readExcel(filePath, fileName, sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ; i < readData.length;i++){
			int j = 0;
			AlarmObject listChild = new AlarmObject();
			listChild.alarmName=readData[i][j++].toString();
			listChild.alarmDevices=readData[i][j++].toString();
			listChild.level=readData[i][j++].toString();
			listChild.alarmMethod=readData[i][j++].toString();
			listChild.desc=readData[i][j++].toString();
			listChild.snList=readData[i][j++].toString();
			listChild.alarmType=readData[i][j++].toString();
			listChild.alarmType_SecType=readData[i][j++].toString();
			listChild.alarmTime_Number=(int)Double.parseDouble(readData[i][j++].toString());;
			listChild.alarmTime_Type=readData[i][j++].toString();
			listChild.alarmRegex=readData[i][j++].toString();
			listChild.alarmType_Value=(int)Double.parseDouble(readData[i][j++].toString());
			list.add(listChild);
			}
		return list;
	}
	
	/**
	 * 校验告警规则页面的正确性
	 */
	public void checkAlamPage(){
		AlarmRule alarmRule = new AlarmRule(webDriver);
		CommonOperations.waitTime(3);
		alarmRule.check_AlarmTable();
	}
	
	/**
	 * 告警规则新建
	 * @param alarm
	 */
	public void addAlarmRule(List<AlarmObject> alarm){
		AlarmRule alarmRule = new AlarmRule(webDriver);
		for(int i = 0 ; i < alarm.size(); i++){
			alarmRule.addAlarm_Rule(alarm.get(i));
		}
		
	}
	
	/**
	 * 告警规则编辑
	 * @param alarm
	 */
	public void editAlarmRule(List<AlarmObject> alarm){
		AlarmRule alarmRule = new AlarmRule(webDriver);
		for(int i = 0 ; i < alarm.size(); i++){
			alarmRule.editAlarm_Rule(alarm.get(i));
		}
	}
	
	/**
	 * 告警规则删除
	 * @param alarm
	 */
	public void deleteAlarmRule(List<AlarmObject> alarm){
		AlarmRule alarmRule = new AlarmRule(webDriver);
		for(int i = 0 ; i < alarm.size(); i++){
			alarmRule.deleteAlarm_Rule(alarm.get(i));;
		}
	}
	
	/**
	 * 告警规则的校验
	 * @param expectd
	 */
	public void valAlarmRule(List<AlarmObject> expectd){
		List<AlarmObject> actual = new LinkedList<AlarmObject>();
		AlarmRule alarmRule = new AlarmRule(webDriver);
		actual = alarmRule.getAlarmMessages();
		ValidateObj.validateAlarm(actual, expectd);
	}
	
	/**
	 * 添加标签
	 * @param tag
	 */
	public void addTag(List<TagObject> tag){
		TagManage aObj = new TagManage();
		for(int i = 0 ; i < tag.size(); i++){
			aObj.add_Tag(tag.get(i));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
