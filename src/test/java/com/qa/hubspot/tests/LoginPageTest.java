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

public class LoginPageTest 
{
	WebDriver driver;
	Properties prop;
	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	
	
    @BeforeTest
    public void setUP()
    {
    	basepage=new BasePage();
    	prop=basepage.init_prop();
    	driver=basepage.init_driver(prop);
    	loginpage=new LoginPage(driver);
    	
    }
    
    @Test(priority=2)
    public void verifyLoginPageTitleTest()
    {
    	Assert.assertEquals(loginpage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE,"Login Page Title Assertion failed");
    }
    @Test(priority=1)
    public void verifySignUpLinkTest()
    {
    	Assert.assertTrue(loginpage.verifySignUpLink());
    }
    @Test(priority=3)
    public void verifyDontHaveAccountLabelTest()
    {
    	Assert.assertTrue(loginpage.verifyDontHaveAnAccountLabel());
    }
    @Test(priority=4)
    public void verifyForgotPasswordLinkTest()
    {
    	Assert.assertTrue(loginpage.verifyforgotPasswordLink());
    }
    @Test(priority=5)
    public void verifyPasswordIsMasked()
    {
    	Assert.assertEquals(loginpage.verifyPasswordEnteredIsMasked(),"password");
    }
    @Test(priority=6)
    public void verifySignInWithGoogleLinkTest()
    {
    	Assert.assertTrue(loginpage.verifySignInWithGoogleLink());
    }
    @Test(priority=7)
    public void verifySSOLinkTest()
    {
    	Assert.assertTrue(loginpage.verifySSOLink());
    }
    @Test(priority=8)
    public void verifyShowPasswordLinkTest()
    {
    	Assert.assertEquals(loginpage.verifyShowPassword(),"text");
    }
    @Test(priority=9)
    public void verifyHidePasswordLinkTest()
    {
    	Assert.assertEquals(loginpage.verifyHidePassword(),"password");
    }
    
    @Test(priority=10)
    public void verifyCopyRightInfoTest()
    {
    	Assert.assertTrue(loginpage.getCopyRightInfo().contains(AppConstants.COPY_RIGHT_INFO));
    }

    @Test(priority=11)
    public void verifyloginTest()
    {
    	homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    	System.out.println(homepage.verifyHomePageHeader());
    	Assert.assertEquals(homepage.verifyHomePageHeader(), AppConstants.HOME_PAGE_HEADER);
    	System.out.println(homepage.verifyHomePageTitle());
    	Assert.assertEquals(homepage.verifyHomePageTitle(),AppConstants.HOME_PAGE_TITLE);
    	
    }
    
    @AfterTest
    public void tearDown()
    {
    	driver.quit();
    }
}
