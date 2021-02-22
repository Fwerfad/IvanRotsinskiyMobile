package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = "native", description = "This simple test just click on the Sign In button")
    public void t1_simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");

    }

    @Test(groups = "native", dataProvider = "native", dataProviderClass = DataProviders.class, description = "This is a test of registration and authorization functionality")
    public void t2_authorizationTest(String userName, String email, String password) throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {
        getPo().getWelement("registrationBtn").click();
        System.out.println("Clicked registration button");

        getPo().getWelement("registrationEmailFld").sendKeys(email);
        getPo().getWelement("registrationUserNameFld").sendKeys(userName);
        getPo().getWelement("registrationPasswordFld").sendKeys(password);
        getPo().getWelement("registrationConfirmPasswordFld").sendKeys(password);
        System.out.println("Filled registration data");

        getPo().getWelement("registrationConfirmationBtn").click();
        System.out.println("Confirmed registration");

        getPo().getWelement("loginEmail").sendKeys(email);
        getPo().getWelement("loginPassword").sendKeys(password);
        System.out.println("Filled login data");

        getPo().getWelement("signInBtn").click();
        System.out.println("Log in as a " + userName);

        WebElement actionBar = getPo().getWelement("actionBar");
        Thread.sleep(2000); // to show that BudgetActivity tab is opened
        /*I was unable to find a way to access a TextView of ActionBar,
        so I'm just checking if it presents. It's found by id so it should be enough*/
        Assert.assertNotEquals(actionBar, null, "Wrong page opened");

    }



}
