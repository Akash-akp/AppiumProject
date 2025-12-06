package com.apptest;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChromeTest extends ChromeBaseTest{
    @Test
    public void test() {
        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement hotelIcon = driver.findElement(By.id("menu_item_HTL"));
        new WebDriverWait(driver,Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(hotelIcon));
        hotelIcon.click();
//        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
