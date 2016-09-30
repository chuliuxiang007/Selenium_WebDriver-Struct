package com.autotest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autotest.utils.Locator;

public class ExtJSPage extends CompositeControl {
	
	
	public WebDriver webDriver=null;
	private String baseUrl="http://10.180.137.61/login/index.html";
	
	public ExtJSPage(WebDriver webDriver){
		super(webDriver);
		this.webDriver=webDriver;
	}
	
	
	public ExtJSPage(WebDriver webDriver,int browserID){
		super(webDriver);
		//0:firefox浏览器
		webDriver = new FirefoxDriver();
		//如果firefox浏览器打不开，则 加上下面内容 ，填写firefox的正确内容；
		//System.setProperty("webdriver.firefox.bin", "H:\\Program Files\\Mozilla Firefox\\firefox.exe");
		webDriver.get(baseUrl);
		webDriver.manage().window().maximize();
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		this.webDriver=webDriver;		
	}
	
	/**
	 * 页面元素：输入文本
	 * @param locator
	 * @param values
	 */
	public void type(Locator locator,String values){
		WebElement e = findElement(webDriver,locator);
		e.sendKeys(values);
	}
	
	/**
	 * 页面元素：点击
	 * @param locator
	 * @throws Exception
	 */
    public  void click(Locator locator) throws Exception {
        WebElement e = findElement(webDriver, locator);
        e.click();
    }
    
    /**
     * 鼠标移动到页面元素上
     * 鼠标点击并且不释放悬停在元素上面
     * @param locator
     * @throws IOException
     */
    public void clickAndHold(Locator locator) throws IOException {
        WebElement e = findElement(webDriver, locator);
        Actions actions = new Actions(webDriver);
        actions.clickAndHold(e).perform();;
    }
	
	/**
	 * 获取页面元素
	 * @param driver
	 * @param locator
	 * @return
	 * @throws IOException
	 */
	public WebElement getElement (WebDriver driver,Locator locator) throws IOException{
		locator=getLocator(locator.getElement());
		WebElement e;
        switch (locator.getBy()) {
        case xpath:
            e = driver.findElement(By.xpath(locator.getElement()));
            break;
        case id:
            e = driver.findElement(By.id(locator.getElement()));
            break;
        case name:
            e = driver.findElement(By.name(locator.getElement()));
            break;
        case cssSelector:
            e = driver.findElement(By.cssSelector(locator.getElement()));
            break;
        case className:
            e = driver.findElement(By.className(locator.getElement()));
            break;
        case tagName:
            e = driver.findElement(By.tagName(locator.getElement()));
            break;
        case linkText:
            e = driver.findElement(By.linkText(locator.getElement()));
            break;
        case partialLinkText:
            e = driver.findElement(By.partialLinkText(locator.getElement()));
            break;
        default:
            e = driver.findElement(By.id(locator.getElement()));
        }
        return e;
	}
	
	/**
	 * 获取多个页面元素
	 * @param driver
	 * @param locator
	 * @return
	 * @throws IOException
	 */
	public List<WebElement> getElements (WebDriver driver,Locator locator) throws IOException{
		locator=getLocator(locator.getElement());
		List<WebElement> listElement= null;
		WebElement e;
        switch (locator.getBy()) {
        case xpath:
        	listElement = driver.findElements(By.xpath(locator.getElement()));
            break;
        case id:
        	listElement = driver.findElements(By.id(locator.getElement()));
            break;
        case name:
        	listElement = driver.findElements(By.name(locator.getElement()));
            break;
        case cssSelector:
        	listElement = driver.findElements(By.cssSelector(locator.getElement()));
            break;
        case className:
        	listElement = driver.findElements(By.className(locator.getElement()));
            break;
        case tagName:
        	listElement = driver.findElements(By.tagName(locator.getElement()));
            break;
        case linkText:
        	listElement = driver.findElements(By.linkText(locator.getElement()));
            break;
        case partialLinkText:
        	listElement = driver.findElements(By.partialLinkText(locator.getElement()));
            break;
        default:
        	listElement = driver.findElements(By.id(locator.getElement()));
        }
        return listElement;
	}
	
	
	/**
	 * 按照locator的属性值寻找元素
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement findElement(WebDriver driver,final Locator locator){
		WebElement element =(new WebDriverWait(driver,locator.getWaitSec())).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver){
				try {
					return getElement(driver,locator);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return null;
				}
			}
		});
		return element;
	}
	
	/**
	 * 获取元素locator属性
	 * @param locatorPostion
	 * @return
	 */
	public Locator getLocator(String locatorPostion){
		Locator locator=null;
        locator = new Locator(locatorPostion);
        return locator;
	}
	
	/**
	 * 校验myLocator元素是否存在
	 * @param driver
	 * @param myLocator
	 * @param timeOut
	 * @return
	 * @throws IOException
	 */
    public boolean isElementPresent(WebDriver driver, Locator myLocator,
            int timeOut) throws IOException {
        final Locator locator = getLocator(myLocator.getElement());
        boolean isPresent = false;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        isPresent = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return findElement(d, locator);
            }
        }).isDisplayed();
        return isPresent;
    }
    
    /**
     * 校验locator元素是否存在
     * @param locator
     * @param timeOut
     * @return
     * @throws IOException
     */
    public boolean isElementPresent(Locator locator, int timeOut)
            throws IOException {
        return isElementPresent(webDriver,locator, timeOut);
    }

}
