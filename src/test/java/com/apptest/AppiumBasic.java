package com.apptest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumBasic extends BaseTest{
    @Test
    public void wifiSettingsTest() {
        getElementByAccessibilityIdWithScroll("Preference").click();
        getElementByAccessibilityIdWithScroll("3. Preference dependencies").click();
        driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        WebElement textbox = driver.findElement(By.id("android:id/edit"));
        textbox.clear();
        textbox.sendKeys("HelloWifi");
        driver.findElement(By.id("android:id/button1")).click();
    }

    @Test
    public void clipBoardTest() {
        getElementByAccessibilityIdWithScroll("Preference").click();
        getElementByAccessibilityIdWithScroll("3. Preference dependencies").click();
        driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        WebElement textbox = driver.findElement(By.id("android:id/edit"));
        textbox.clear();
        driver.setClipboardText("HelloWifi");
        textbox.sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();
    }

    @Test
    public void keyEventTest() {
        getElementByAccessibilityIdWithScroll("Preference").click();
        getElementByAccessibilityIdWithScroll("3. Preference dependencies").click();
        driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        WebElement textbox = driver.findElement(By.id("android:id/edit"));
        textbox.clear();
        textbox.sendKeys("HelloWifi");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElement(By.id("android:id/button1")).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

    }



    @Test
    public void longPressTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPressAction(peopleNames);
        System.out.println(driver.findElement(By.id("android:id/title")).isDisplayed());
    }

    @Test
    public void scrollTestByAndroidUIAutomator() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        getElementByTextwithScroll("WebView").click();
    }

    @Test
    public void scrollTestByScrollGesture(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        getElementByAccessibilityIdWithScroll("WebView").click();
    }

    @Test
    public void swipeTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        WebElement firstImage = driver.findElement(By.xpath("//android.widget.ImageView[@index='0']"));

        swipe(firstImage,"left",0.1);

        Assert.assertEquals(driver.findElement(By.xpath("//android.widget.ImageView[@index='1']")).getAttribute("focusable"),"true");

    }

    @Test
    public void dragAndDropTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement destination = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        dragAndDrop(source,destination);

        String resultText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(resultText,"Dropped!");
    }
}
