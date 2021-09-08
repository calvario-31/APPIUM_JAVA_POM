package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class DriverManager {
    private static String deviceName;
    private static String osVersion;

    public AndroidDriver<AndroidElement> buildDriver() {
        AndroidDriver<AndroidElement> driver;

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
        return driver;
    }

    @Attachment(value = "Screenshot failure", type = "image/png")
    public byte[] getScreenshot(AndroidDriver<AndroidElement> driver) {
        Log.info("Taking screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void assignDriverParameters() {
        String deviceName = System.getProperty("deviceName");
        String osVersion = System.getProperty("osVersion");
        if (deviceName == null) {
            Log.info("Setting default emulator name to mobile_emulator");
            deviceName = "mobile_emulator";
        }
        if(osVersion == null) {
            Log.info("Setting default os version to 11");
            osVersion = "11";
        }
        DriverManager.deviceName = deviceName;
        DriverManager.osVersion = osVersion;
    }

    public static void writeEnvVariables() {
        Log.info("Writing environmental variables to the report");
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", "Android")
                        .put("Platform version", osVersion)
                        .put("Device Name", deviceName)
                        .put("APK", "SauceLabs")
                        .build());
    }
}
