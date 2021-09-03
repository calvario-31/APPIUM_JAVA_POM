package pageobjects.shopping;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageobjects.Page;
import utilities.Log;

public class ItemDetailPage extends Page {
    private final By buttonBackToProducts = MobileBy.AccessibilityId("test-BACK TO PRODUCTS");
    private final String buttonAddToCart = "test-ADD TO CART";
    private final String price = "test-Price";

    public ItemDetailPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Add item: {0} to cart with price: {1}")
    public void addToCart(String itemName, double itemPrice){
        waitPageToLoad();
        Log.info("Verifying item name is displayed");
        Log.debug("Item name: " + itemName);
        $text(itemName);
        Log.info("Verifying price matches the example data");
        String priceText = $description(price).getText();
        Log.debug("priceText: " + priceText);
        Assert.assertEquals(Double.parseDouble(priceText.substring(1)), itemPrice);
        Log.info("Clicking on add to cart");
        $description(buttonAddToCart).click();
        Log.info("Clicking on back to products");
        $(buttonBackToProducts).click();
    }

    @Override
    protected void waitPageToLoad() {
        $$(buttonBackToProducts);
    }
}
