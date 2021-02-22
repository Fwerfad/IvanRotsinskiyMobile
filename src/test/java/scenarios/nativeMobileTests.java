package scenarios;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void t1_simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest native test done");
        if (getDriver().getPlatformName().equals("iOS"))
            getPo().getWelement("iOSPopUp").click();
    }

    @Parameters({"userName","password","email"})
    @Test(groups = {"native"}, description = "This is a test of registration and authorization functionality")
    public void t2_authorizationTest(String userName, String password, String email) throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {
        getDriver().hideKeyboard();
        getPo().getWelement("registrationBtn").click();
        System.out.println("Clicked registration button");


        getPo().getWelement("registrationEmailFld").sendKeys(email);
        getPo().getWelement("registrationUserNameFld").sendKeys(userName);
        getDriver().hideKeyboard(); //keybord is actually hiding registration confirmation button and test get stucks
        getPo().getWelement("registrationPasswordFld").sendKeys(password);
        getPo().getWelement("registrationConfirmPasswordFld").sendKeys(password);
        System.out.println("Filled registration data");

        getPo().getWelement("registrationConfirmationBtn").click();
        System.out.println("Confirmed registration");

        getDriver().hideKeyboard();
        getPo().getWelement("loginEmail").sendKeys(email);
        getPo().getWelement("loginPassword").sendKeys(password);
        System.out.println("Filled login data");

        getPo().getWelement("signInBtn").click();
        System.out.println("Log in as a " + userName);

        WebElement actionBar = getPo().getWelement("actionBar");
        Thread.sleep(2000); // to show that BudgetActivity tab is opened
        Assert.assertTrue(actionBar.getText().contains("Budge"), "Wrong page opened");

    }



}
