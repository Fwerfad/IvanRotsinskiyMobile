package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    @Parameters({"company"})
    @Test(groups = {"web"}, description = "Make sure that we've googled company at google")
    public void googlingTest(String company){
        getDriver().get("https://www.google.com/search?q=" + company); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        Assert.assertEquals(((WebDriver) getDriver()).getTitle().contains(company), "Wrong site is opened");

    }

}
