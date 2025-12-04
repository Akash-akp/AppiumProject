package com.apptest;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private int port = 4724;
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
        options.setUdid("emulator-5556");
        String appPath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos-debug.apk";
        options.setApp(appPath);

        driver = new AndroidDriver(new URI("http://127.0.0.1:"+port).toURL(),options);
    }

    public static void longPressAction(WebElement element) {
        driver.executeScript("mobile: longClickGesture", new HashMap<String, Object>() {{
            put("elementId", ((RemoteWebElement)element).getId());
            put("duration", 2000);
        }});
    }

    public static WebElement getElementByTextwithScroll(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
    }

    public static WebElement getElementByAccessibilityIdWithScroll(String id) {
        boolean canScrollMore = true;
        while(canScrollMore){
            try {
                driver.findElement(AppiumBy.accessibilityId(id));
                canScrollMore = false;
            } catch (Exception e) {
                Map<String, Object> params = ImmutableMap.of(
                        "left",100,"top",100,"width",200,"height",200,
                        "direction","down",
                        "percent",3.0
                );
                driver.executeScript("mobile: scrollGesture", params);
            }
        }
        return driver.findElement(AppiumBy.accessibilityId(id));
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
