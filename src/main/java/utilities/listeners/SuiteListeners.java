package utilities.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.DriverManager;
import utilities.Log;

public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Log.info("Beginning: " + suite.getName());
        DriverManager.assignDriverParameters();
    }

    @Override
    public void onFinish(ISuite suite) {
        Log.info("Ending: " + suite.getName());
        DriverManager.writeEnvVariables();
    }
}
