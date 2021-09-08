package dummyfails;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.credentials.MainPage;
import pageobjects.shopping.ShoppingPage;
import utilities.Base;

public class DummyFailTest extends Base {
    private MainPage mainPage;
    private ShoppingPage shoppingPage;

    @BeforeMethod(alwaysRun = true, description = "setting up the driver")
    public void setUp() {
        setup();
    }

    @Test(groups = {"failed"})
    @Description("Verify the screenshot")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("8dvc3IEV")
    public void failedTest() {
        mainPage = new MainPage(driver);
        mainPage.login("hola-mundo", "123456");

        shoppingPage = new ShoppingPage(driver);
        Assert.assertTrue(shoppingPage.shoppingPageIsDisplayed(), "failed as expected");
    }

    @AfterMethod(alwaysRun = true, description = "tearing down the driver")
    public void tearDown() {
        teardown();
    }
}
