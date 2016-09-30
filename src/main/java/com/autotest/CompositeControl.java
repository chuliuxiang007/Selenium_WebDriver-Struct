package com.autotest;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;


public abstract class CompositeControl extends Control {
	protected List<Control> children;
	

	
	public CompositeControl(WebDriver webDriver){
		super(webDriver);
		children=new ArrayList<Control>();
	}
	
	public void addChild(Control control){
		this.children.add(control);
		control.parent=this;
	}
    
}
