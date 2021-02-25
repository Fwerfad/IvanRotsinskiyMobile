package setup;

import io.appium.java_client.AppiumDriver;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private String appType;
    IPageObject po;

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName","appType","deviceName","browserName","appPackage", "appActivity", "bundleid", "platformVersion"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName, @Optional("") String browserName, @Optional("") String appPackage, @Optional String appActivity, @Optional String bundleid, @Optional String platformVersion) throws Exception {
        System.out.println("Before: app type - "+appType);
        setAppiumDriver(appType, platformName, deviceName, browserName, appPackage, appActivity, bundleid, platformVersion);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String appType, String platformName, String deviceName, String browserName, @Optional String appPackage, @Optional String appActivity, @Optional String bundleid, String platformVersion){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("platformVersion", platformVersion);

        if(appType.equals("web")) {
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("chromedriverDisableBuildCheck","true");
        }
        else
            if (platformName.equals("iOS")) {
                capabilities.setCapability("bundleid", bundleid);
                System.out.println(bundleid);
            }
            else {
                capabilities.setCapability("appPackage", appPackage);
                capabilities.setCapability("appActivity", appActivity);
            }
        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }


}
