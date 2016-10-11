package com.autotest.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.autotest.common.objects.AlarmObject;


public class ValidateObj {
	
	public static Logger logger = Logger.getLogger(ValidateObj.class);

	/**
	 * 告警规则校验
	 * @param alarms
	 */
	public static void validateAlarm(List<AlarmObject> alarmActual,List<AlarmObject> alarmExpect){
		//校验告警规则数量
		boolean flag = false;
		int count=alarmActual.size();
		if(count != alarmExpect.size()){
			logger.info("告警规则数量错误");
			System.out.println("告警规则数量错误");
			Assert.assertTrue(false);
		}else{
			for(AlarmObject obj1 :alarmActual ){
				flag = false;
				for(AlarmObject obj2: alarmExpect){
					if(obj1.alarmName.equals(obj2.alarmName)){
						valDetailAlarm(obj1, obj2);
						flag = true;
					}
				}
				if(!flag){
					Assert.assertTrue(false);
					break;
				}
			}
		}		
	}
	
	/**
	 * 单条 告警规则详细内容校验
	 * @param actual
	 * @param expect
	 */
	public  static void valDetailAlarm(AlarmObject actual,AlarmObject expected){
		if(expected.alarmName.equals(actual.alarmName)){
			//告警规则名称校验
			logger.info("校验告警规则详细内容");
			Assert.assertEquals(actual.alarmName, expected.alarmName);
			Assert.assertEquals(actual.alarmDevices, expected.alarmDevices);
			Assert.assertEquals(actual.level, expected.level);
			Assert.assertEquals(actual.creatime, expected.creatime);
			Assert.assertEquals(actual.alarmMethod, expected.alarmMethod);
			Assert.assertEquals(actual.desc, expected.desc);
		}else{
			Assert.assertTrue(false);
		}
	}
}
