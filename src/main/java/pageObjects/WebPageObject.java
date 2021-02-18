package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.remote.RemoteWebElement;

public class WebPageObject  {

    @AndroidFindBy(xpath = "//android.view.View[@text='www.epam.com']")
    private RemoteWebElement result;



    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }


}
