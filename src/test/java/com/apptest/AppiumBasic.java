package com.apptest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumBasic extends BaseTest{
    @Test
    public void WifiSettingsTest() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        WebElement textbox = driver.findElement(By.id("android:id/edit"));
        textbox.clear();
        textbox.sendKeys("HelloWifi");
        driver.findElement(By.id("android:id/button1")).click();
    }

    @Test
    public void LongPressTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPressAction(driver,peopleNames);
        System.out.println(driver.findElement(By.id("android:id/title")).isDisplayed());
    }

}
