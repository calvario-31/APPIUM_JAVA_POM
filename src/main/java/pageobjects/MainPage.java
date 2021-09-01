package pageobjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.Log;

public class MainPage extends Page{
    By inputUsername = MobileBy.AccessibilityId("test-Username");
    By inputPassword = MobileBy.AccessibilityId("test-Password");
    By buttonLogin = MobileBy.AccessibilityId("test-LOGIN");
    String lockedOutUser = "test-locked_out_user";
    By errorMessage = MobileBy.AccessibilityId("test-Error message");

    public MainPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Login into the app with username {0} and password {1}")
    public void login(String username, String password){
        Log.info("Filling username");
        Log.debug("Username: " + username);
        $$(inputUsername).sendKeys(username);
        Log.info("Filling password");
        Log.debug("Password: " + password);
        $(inputPassword).sendKeys(password);
        Log.info("Clicking on login button");
        $(buttonLogin).click();
    }

    @Step("Login with locked out user")
    public void loginLockedOutUser(){
        Log.info("Waiting to button login to appear");
        $$(buttonLogin);
        Log.info("Scrolling into locked out user and click");
        $description(lockedOutUser).click();
        Log.info("Scrolling to top");
        toTop();
        Log.info("Clicking on login button");
        $(buttonLogin).click();
    }

    @Step("Verifying error message is displayed")
    public boolean errorMessageIsDisplayed(){
        Log.info("Verifying error message is displayed");
        return elementIsDisplayed(errorMessage);
    }
}
