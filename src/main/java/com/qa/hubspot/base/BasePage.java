package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.AppConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage 
{
	  public  WebDriver driver;
	  public Properties prop;
	   /**
	    * This is method is used to initialize the WebDriver on the basis of browser name
	    * @param prop
	    * @return WebDriver instance
	    */
	   public WebDriver init_driver(Properties prop)
       {
    	   String browserName=prop.getProperty("browser");
    	   boolean isHeadless=Boolean.parseBoolean(prop.getProperty("headless"));
    	 if(browserName.equalsIgnoreCase("chrome"))
    	 {
    		 if(isHeadless)
    		 {
    			 ChromeOptions co=new ChromeOptions();
    			 co.addArguments("--headless");
    			 WebDriverManager.chromedriver().setup();
       		     driver=new ChromeDriver(co);
    		 }
    		 else
    		 {
    		 WebDriverManager.chromedriver().setup();
    		  driver=new ChromeDriver();
    		 }
    	 }
    	 else if(browserName.equalsIgnoreCase("firefox"))
    	 {
    		 if(isHeadless)
    		 {
    			 FirefoxOptions fo=new FirefoxOptions();
    			 WebDriverManager.firefoxdriver().setup();
       		     driver=new FirefoxDriver(fo);
    		 }
    		 else
    		 {
    			 WebDriverManager.firefoxdriver().setup();
       		     driver=new FirefoxDriver();
    		 }
    	 }
    	 else if(browserName.equalsIgnoreCase("safari")) 
    	 {
    		 WebDriverManager.getInstance(SafariDriver.class).setup();
    		 driver=new SafariDriver();
    	 }
    	 else if(browserName.equalsIgnoreCase("ie"))
    	 {
    		 WebDriverManager.iedriver().setup();
   		     driver=new InternetExplorerDriver();
    	 }
    	 else
    	 {
    		 System.out.println(browserName+" was not found.Please specify the proper browser name.");
    	 }
    	 driver.get(prop.getProperty("url"));
    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	 driver.manage().window().maximize();
    	 driver.manage().deleteAllCookies();
    	 return driver;
       }
       /**
        * This method is used to initialize the Properties
        * @return an  instance of Properties
        */
       public Properties init_prop()
       {
    	   
    	   prop=new Properties();
    	   try 
    	   {
    		   FileInputStream ip=new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			   prop.load(ip);
		    } 
    	   catch (FileNotFoundException e) 
    	   {
			System.out.println("config.properties file was not found");
			e.printStackTrace();
		   }
    	   catch (IOException e) {
			System.out.println("Exception occurred while loading config.properties file");
			e.printStackTrace();
		   }
    	   return prop;
       }
}
