package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;
import utilities.listeners.SuiteListeners;
import utilities.listeners.TestListeners;

import java.io.File;
import java.net.URL;

@Listeners({TestListeners.class, SuiteListeners.class})
public abstract class Base {
    protected AndroidDriver<AndroidElement> driver;

    protected void setup() {
        String APPIUM_URL_SERVER = "http://localhost:4723/wd/hub";

        File fileAPK = new File("src/main/resources/apk/sauceLabs.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "mobile_emulator");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.APP, fileAPK.getAbsolutePath());

        try {
            driver = new AndroidDriver<>(new URL(APPIUM_URL_SERVER), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            driver = null;
        }
    }

    protected void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
