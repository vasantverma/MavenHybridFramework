package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest 
{
  WebDriver driver;
  BasePage basepage;
  Properties prop;
  LoginPage loginpage;
  HomePage homepage;
  ContactsPage contactpage;
  
  @BeforeTest
  public void SetUp()
  {
	  basepage=new BasePage();
	  prop=basepage.init_prop();
	  driver=basepage.init_driver(prop);
	  loginpage=new LoginPage(driver);
	  homepage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	  contactpage=homepage.goToContacts();
  }
  @Test(priority=1)
  public void verifyContactPageTitle() 
  {
	  String title=contactpage.getContactsPageTitle();
	  Assert.assertEquals(title, AppConstants.CONTACTS_PAGE_TITLE);
  }
  @Test(priority=2)
  public void verifyContactPageHeader()
  {
	  String header=contactpage.getContactsPageHeader();
	  Assert.assertEquals(header, AppConstants.CONTACTS_PAGE_HEADER);
  }
  @DataProvider
  public Object[][] getContactsData()
  {
	  Object data[][]=ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
	  return data;
  }
  
  
  @Test(priority=3,dataProvider="getContactsData")
  public void verifyCreateContact(String email,String firstname,String lastname,String jobtitle,String phone)
  {
	  contactpage.createContact(email,firstname,lastname,jobtitle,phone);
	String title=contactpage.getCreatedContactPageTitle(firstname,lastname);
	  Assert.assertEquals(title,firstname+" "+lastname);
	  homepage.goToContacts();
  }
  @AfterTest
  public void tearDown()
  {
	 driver.quit();
  }
}
