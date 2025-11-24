package com.apptest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumBasic {
    @Test
    public void AppiumTest() throws MalformedURLException, URISyntaxException {
        int port = 4724;
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\AkashKumarParida\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(port)
                .build();

        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        String appPath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos-debug.apk";
        options.setApp(appPath);

        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:"+port).toURL(),options);
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.quit();
        service.stop();

    }

}
