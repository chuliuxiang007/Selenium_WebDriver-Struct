package com.autotest.utils;
/**
 * This is for element library
 * @author sliu
 *
 */
public class Locator {
	
	private String element;
	private int waitSec;
	
	/**
	 * create a enum variable for By
	 * 定位 元素By的类型 
	 * @author sliu
	 *
	 */
	public enum ByType{
		xpath,id,linkText,name,className,cssSelector,partialLinkText,tagName
	}
	
	private ByType byType;
	
	public Locator(){
		
	}
	
	/**
	 * default Locator,use id
	 * @param element
	 */
	public Locator(String element){
		this.element=element;
		this.waitSec=3;
		this.byType=ByType.id;
	}
	
	/**
	 * default Locator, use id
	 * wait elment time
	 * @param element
	 * @param waitSec  
	 */
	public Locator(String element,int waitSec){
		this.waitSec=waitSec;
		this.element=element;
		this.byType=ByType.id;
	}
	
	/**
	 * definition Locator
	 * @param element
	 * @param waitSec
	 * @param byType
	 */
	public Locator(String element,int waitSec,ByType byType){
		this.waitSec=waitSec;
		this.element=element;
		this.byType=byType;
	}
	
	/**
	 * 获取页面元素类型的Value
	 * @return
	 */
	public String getElement(){
		return element;
	}
	
	/**
	 * 获取等待时间 
	 * @return
	 */
	public int getWaitSec(){
		return waitSec;
	}
	
	/**
	 * 获取查看元素By类型
	 * @return
	 */
	public ByType getBy(){
		return byType;
	}
	
	/**
	 * 设置搜索元素的类型
	 */
	public void setBy(){
		this.byType=byType;
	}
	
	
	
}
