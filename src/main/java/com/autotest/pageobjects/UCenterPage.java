package com.autotest.pageobjects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.Module.AlarmRule;

import com.autotest.common.ValidateObj;
import com.autotest.common.objects.AlarmObject;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;

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
	 * 个人信息：1，消息管理：2，告警规则：3，标签管理：4，用户反馈：5
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
	 * 告警规则新建
	 * @param alarm
	 */
	public void addAalrmRule(List<AlarmObject> alarm){
		AlarmRule alarmRule = new AlarmRule(webDriver);
		Iterator<AlarmObject> iter1 = alarm.iterator();
		while(iter1.hasNext()){
			alarmRule.addAlarmRule(iter1.next());
		}
		
	}
	
	/**
	 * 告警规则的校验
	 * @param expectd
	 */
	public void valAalarmRule(List<AlarmObject> expectd){
		List<AlarmObject> actual = new LinkedList<AlarmObject>();
		AlarmRule alarmRule = new AlarmRule(webDriver);
		actual = alarmRule.getAlarmMessages();
		ValidateObj.validateAlarm(actual, expectd);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
