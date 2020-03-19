package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import com.qa.hubspot.util.JavascriptUtil;

public class ContactsPage 
{
    WebDriver driver;
    ElementActions elementAction;
    JavascriptUtil jsUtil;
    
	//Constructor
	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		elementAction=new ElementActions(driver);
		jsUtil=new JavascriptUtil(driver);
	}
	
	//Page locators or Page Factory
	By contactsPageHeader=By.xpath("//i18n-string[text()='Contacts']");
	By createContactLink=By.xpath("//button[@data-onboarding='new-object-button']");
	By email=By.xpath("//input[@data-field='email']");
	By fname=By.xpath("//input[@data-field='firstname']");
	By lname=By.xpath("//input[@data-field='lastname']");
	By jobTitle=By.xpath("//input[@data-field='jobtitle']");
	By phone=By.xpath("//input[@data-field='phone']");
	By createConactBtn=By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']");
	
	
	//Page Actions
	public String getContactsPageTitle()
	{
		elementAction.waitForPageTitle(AppConstants.CONTACTS_PAGE_TITLE);
		return elementAction.doGetPageTitle(AppConstants.CONTACTS_PAGE_TITLE);
	}
	
	public String getContactsPageHeader()
	{
		elementAction.waitForPageTitle(AppConstants.CONTACTS_PAGE_HEADER);
		return elementAction.doGetPageTitle(AppConstants.CONTACTS_PAGE_HEADER);
	}
	
	public void createContact(String email,String firstname,String lastname,String jobtitle,String phone)
	{
		elementAction.waitForElementPresent(createContactLink);
		//elementAction.doClick(createContactLink);
		jsUtil.clickElementByJS(elementAction.getWebElement(createContactLink));
		elementAction.waitForElementPresent(this.email);
		elementAction.doSendKeys(this.email, email);
		elementAction.doSendKeys(fname, firstname);
		elementAction.doSendKeys(lname, lastname);
		elementAction.waitForElementPresent(jobTitle);
		elementAction.doSendKeys(jobTitle, jobtitle);
		elementAction.waitForElementPresent(this.phone);
		elementAction.doSendKeys(this.phone,phone);
		elementAction.waitForElementPresent(createConactBtn);
		elementAction.doClick(createConactBtn);
	}
	public String getCreatedContactPageTitle(String firstname,String lastname)
	{
		elementAction.waitForPageTitle(firstname+" "+lastname);
		return elementAction.doGetPageTitle(firstname+" "+lastname);
	}
	 public String getContactName()
	 {
		elementAction.waitForElementPresent(fname);
		return elementAction.doGetText(fname)+elementAction.doGetText(lname);
	 }
	 
}
	
	
	
	
	
	

