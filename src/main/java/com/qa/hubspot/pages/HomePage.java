package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import com.qa.hubspot.util.JavascriptUtil;

public class HomePage extends BasePage
{
	public WebDriver driver;
	public ElementActions elementAction;
	JavascriptUtil jsutil;
	//Constructor of HomePage
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		elementAction=new ElementActions(driver);
		jsutil=new JavascriptUtil(driver);
		
	}
	
    //Page Locators
	By homePageHeader=By.className("private-page__title");
	By loggedInUser=By.cssSelector("div.user-info-name");
	By loggedInUserEmail=By.cssSelector("div.user-info-email");
	By userPanel=By.id("account-menu");
	By parentContactsLink=By.id("nav-primary-contacts-branch");
	By childContactsLink=By.id("nav-secondary-contacts");
	
	//Page Actions
	public String verifyHomePageTitle()
	{
		return elementAction.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
	
	}
	public String verifyHomePageHeader()
	{
		if(elementAction.doIsDisplayed(homePageHeader))
		{
			return elementAction.doGetText(homePageHeader);
		}
		return null;
	}
	public String verifyLoggedInUser()
	{
		     
		     if(elementAction.doIsDisplayed(userPanel))
		     { 	 
		    	 elementAction.doClick(userPanel);
		     if(elementAction.doIsDisplayed(loggedInUser))
		     {
		    	 return elementAction.doGetText(loggedInUser);
		     }
		     }
			return null;
		     
	}
	public String getLoggedInUserEmail()
	{         
		if(elementAction.doIsDisplayed(loggedInUserEmail))
	{
		return elementAction.doGetText(loggedInUserEmail);
	}
		    	 return null;
	}
	
	public ContactsPage goToContacts()
	{
		clickOnContacts();
		return new ContactsPage(this.driver);
	}
	public ContactsPage clickOnContacts()
	{
		elementAction.waitForElementPresent(parentContactsLink);
		elementAction.doClick(parentContactsLink);
		elementAction.waitForElementPresent(childContactsLink);
		elementAction.doClick(childContactsLink);
		return new ContactsPage(this.driver);
	}
}
