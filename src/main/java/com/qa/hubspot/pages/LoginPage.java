package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import com.qa.hubspot.util.JavascriptUtil;

public class LoginPage extends BasePage
{
	public WebDriver driver;
	ElementActions elementActions;
	JavascriptUtil jsutil;
	//Constructor of LoginPage class
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementActions=new ElementActions(driver);
		jsutil=new JavascriptUtil(driver);
	}
	
   //Locators or Object Repository
	By username=By.id("username");
	By password=By.id("password");
	By loginBtn=By.id("loginBtn");
	By forgotPwdLink=By.linkText("Forgot my password");
	By rememberMeText=By.xpath("//i18n-string[text()='Remember me']");
	By showPasswordLink=By.xpath("//span[text()='Show Password']");
	By hidePasswordLink=By.xpath("//span[text()='Hide Password']");
	By rememberPwdCheckBox=By.xpath("//span[@class='private-checkbox__icon private-checkbox__dash']");
	By signInWithGoogleBtn=By.xpath("//span[@class='buttonText' and text()='Sign in with Google']");
	By ssoBtn=By.id("ssoBtn");
	By signUpLink=By.xpath("//i18n-string[text()='Sign up']");
	By dontHaveAnAccountText=By.xpath("//div[@class='signup-link']//child::i18n-string[@data-key='login.signupLink.text']");
	By footerText=By.className("copyright");

	
	//Page Actions/methods
	public String  getLoginPageTitle()
	{
		return elementActions.doGetPageTitle(AppConstants.LOGIN_PAGE_TITLE);
	}
	public boolean verifyDontHaveAnAccountLabel() 
	{
		return elementActions.doIsDisplayed(dontHaveAnAccountText);
	}
	public boolean verifySignUpLink()
	{
		return elementActions.doIsDisplayed(signUpLink);
	}
	public boolean verifyRememberMeLabel()
	{
		return elementActions.doIsDisplayed(rememberMeText);
	}
	/*public void verifyRememberPassword()
	{
		 driver.findElement(rememberPwdCheckBox).click();
	}*/
	public String verifyPasswordEnteredIsMasked()
	{
		 return elementActions.doGetAttribute(password,"type");
	}
	public void enterPassword(String pwd)
	{
		
	}
	public String verifyShowPassword()
	{
		 elementActions.doIsDisplayed(showPasswordLink);
		 elementActions.doClick(showPasswordLink);
		 return elementActions.doGetAttribute(password,"type");
	}
	public String verifyHidePassword()
	{
		elementActions.doIsDisplayed(hidePasswordLink);
		 elementActions.doClick(hidePasswordLink);
		 return elementActions.doGetAttribute(password,"type");
	}
	public boolean verifyHidePasswordLink()
	{
		return elementActions.doIsDisplayed(hidePasswordLink);
	}
	public boolean verifyforgotPasswordLink()
	{
		return elementActions.doIsDisplayed(forgotPwdLink);
	}
	public HomePage doLogin(String userName,String pwd)
	{
		elementActions.doSendKeys(username, userName);
		elementActions.doSendKeys(password, pwd);
		elementActions.doClick(loginBtn);
		return new HomePage(driver);
		
	}
	public boolean verifySignInWithGoogleLink()
	{
		return elementActions.doIsDisplayed(signInWithGoogleBtn);
	}
	public boolean verifySSOLink()
	{
		return elementActions.doIsDisplayed(ssoBtn);
	}
	public String getCopyRightInfo()
	{
		return elementActions.doGetText(footerText);
	}
	
	
}
