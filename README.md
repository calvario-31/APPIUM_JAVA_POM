APK TESTED: saucelabs.apk

to run on command line: mvn clean test -DsuiteName=${suite name}

example: mvn clean test -DsuiteName=regression

if real device giving problems use the following commands on the terminal:

adb uninstall io.appium.uiautomator2.server

adb uninstall io.appium.uiautomator2.server.test