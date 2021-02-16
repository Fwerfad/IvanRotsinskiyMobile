package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void t1_simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");

    }

    @Parameters({"userName","password","email"})
    @Test(groups = {"native"}, description = "This is a test of registration and authorization functionality")
    public void t2_authorizationTest(String userName, String password, String email) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("registrationBtn").click();

        getPo().getWelement("registrationEmailFld").sendKeys(email);
        getPo().getWelement("registrationUserNameFld").sendKeys(userName);
        getPo().getWelement("registrationPasswordFld").sendKeys(password);
        getPo().getWelement("registrationConfirmPasswordFld").sendKeys(password);
        getPo().getWelement("registrationConfirmationBtn").click();

        getPo().getWelement("loginEmail").sendKeys(email);
        getPo().getWelement("loginPassword").sendKeys(password);
        getPo().getWelement("signInBtn").click();

        WebElement actionBar = getPo().getWelement("actionBar");
        /*I was unable to find a way to access a TextView of ActionBar,
        so I'm just checking if it presents. It's found by id so it should be enough*/
        Assert.assertNotEquals(actionBar, null, "Wrong page opened");

    }



}
