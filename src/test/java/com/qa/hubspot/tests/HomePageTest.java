package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;

public class HomePageTest 
{
	public WebDriver driver;
	BasePage basepage;
	LoginPage loginpage;
	Properties prop;
	HomePage homepage;
	
  @BeforeTest
  public void setUp()
  {
	  basepage=new BasePage();
	  prop=basepage.init_prop();
	  driver=basepage.init_driver(prop);
	  loginpage=new LoginPage(driver);
	  homepage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	  
  }
  
  @Test(priority=2)
  public void verifyHeaderPageTitleTest()
  {
	  Assert.assertEquals(homepage.verifyHomePageTitle(),AppConstants.HOME_PAGE_TITLE,"Home Page Title assertion failed");
  }
  @Test(priority=1)
  public void verifyHeaderPageHeaderTest()
  {
	  Assert.assertEquals(homepage.verifyHomePageHeader(),AppConstants.HOME_PAGE_HEADER,"Home Page header assertion failed");
  }
  @Test(priority=3)
  public void verifyLoggedInUserTest()
  {
	  String loggedInUser=homepage.verifyLoggedInUser();
	  System.out.println(loggedInUser);
	  Assert.assertEquals(loggedInUser,prop.getProperty("accountName"),"Logged In User Name assertion failed");
  }
  @Test(priority=4,dependsOnMethods="verifyLoggedInUserTest")
  public void verifyLoggedInUserEmailTest()
  {
	  String loggedInUserEmail=homepage.getLoggedInUserEmail();
	  System.out.println(loggedInUserEmail);
	  Assert.assertEquals(loggedInUserEmail,prop.getProperty("username"),"Logged In User Email assertion failed");
  }
  
 
  @AfterTest
  public void tearDown()
  {
	  driver.quit();
  }
}
