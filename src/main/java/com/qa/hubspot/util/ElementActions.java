package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions
{
	WebDriver driver;
	WebDriverWait wait;
  public ElementActions(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(this.driver,AppConstants.DEFAULT_EXPLICIT_WAIT);
	}
  
  public WebElement getWebElement(By locator)
  {
	  WebElement element=null;
	  try
	  {
		  waitForElementPresent(locator);
		  element=driver.findElement(locator);
		  return element;
	  }
	  catch(Exception e)
	  {
		  System.out.println("Some exception occurred while finding element "+locator);
	  }
	  return element;
  }
  public void doSendKeys(By locator,String value)
  {
	  getWebElement(locator).sendKeys(value);
  }
  public void doClick(By locator)
  {
	  getWebElement(locator).click();
  }
  public boolean doIsDisplayed(By locator)
  {
	  return getWebElement(locator).isDisplayed();
  }
  public String doGetText(By locator)
  {
	  return getWebElement(locator).getText();
  }
  public String doGetPageTitle(String title)
  {
	  waitForPageTitle(title);
	  return driver.getTitle();
  }
  public String doGetAttribute(By locator,String attrName)
  {
	  return getWebElement(locator).getAttribute(attrName);
  }
  public void waitForElementPresent(By locator)
  {
	  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }
  public void waitForElementVisible(By locator ) 
  {
	wait.until(ExpectedConditions.visibilityOf(getWebElement(locator)));  
  }
  public void waitForPageTitle(String title)
  {
	  wait.until(ExpectedConditions.titleIs(title));
  }
  
}
