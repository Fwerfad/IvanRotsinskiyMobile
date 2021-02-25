package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.Locale;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(groups = "web", description = "Make sure that we've googled company at google", dataProviderClass = DataProviders.class, dataProvider = "web")
    public void googlingTest(String company) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().get("https://www.google.com/search?q=" + company);
        // Make sure that page has been loaded completely

        System.out.println(getPo().getWelement("result").getText());
        Assert.assertTrue(getPo().getWelement("result").getText().toUpperCase(Locale.ROOT).contains(company) &&
                !getPo().getWelement("result").getText().contains("ничего не найдено"),
                "Nothing relevant to " + company + " was found");

    }

}
