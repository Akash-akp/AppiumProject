package com.apptest;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class ChromeBaseTest {
    private int port = 4725;
    protected AppiumDriverLocalService service;
    protected static AndroidDriver driver;

    @BeforeMethod
    public void setup() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\AkashKumarParida\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(port)
                .build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium_Phone_API_36.1");
        options.setUdid("emulator-5554");
//        options.setAppPackage("com.android.chrome");
//        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.setChromedriverExecutable("C:\\Users\\AkashKumarParida\\Downloads\\chromedriver_win32");
//        options.setCapability("browserName","Chrome");


//        String appPath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos-debug.apk";
//        options.setApp(appPath);

        driver = new AndroidDriver(new URI("http://127.0.0.1:"+port).toURL(),options);
        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent","com.android.chrome/com.google.android.apps.chrome.Main"));
//        DeviceRotation landScape = new DeviceRotation(0,0,90);
//        driver.rotate(landScape);


    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
        if(service!=null){
            service.stop();
        }
    }
}
