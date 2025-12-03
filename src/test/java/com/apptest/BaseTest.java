package com.apptest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private int port = 4724;
    protected AppiumDriverLocalService service;
    protected AndroidDriver driver;

    @BeforeClass
    public void setup() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\AkashKumarParida\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(port)
                .build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        String appPath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos-debug.apk";
        options.setApp(appPath);

        driver = new AndroidDriver(new URI("http://127.0.0.1:"+port).toURL(),options);
    }

    public static void longPressAction(AndroidDriver driver,  WebElement element) {
        driver.executeScript("mobile: longClickGesture", new HashMap<String, Object>() {{
            put("elementId", element.getAttribute("elementId"));
            put("duration", 2000);
        }});
    }

    @AfterClass
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
        if(service!=null){
            service.stop();
        }
    }
}
