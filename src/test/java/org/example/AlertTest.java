package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertTest extends BaseTestClass {
    @Test
    void simpleAlertCheck() {
        driver.findElement(By.id("alertBtn")).click();

        // Work with alert
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        // Check if the text in alert as expected
        Assertions.assertEquals("I am an alert box!", text);
    }

    @Test
    void confirmationAlertCheck() {
        WebElement confirmBtn = driver.findElement(By.id("confirmBtn"));
        scrollDownToElement(confirmBtn);

        confirmBtn.click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        String confirmText = driver.findElement(By.id("demo")).getText();
        confirmBtn.click();
        driver.switchTo().alert().dismiss();
        String declineText = driver.findElement(By.id("demo")).getText();


        // Check if those texts are as expected
        Assertions.assertEquals("Press a button!", text);
        Assertions.assertEquals("You pressed OK!", confirmText);
        Assertions.assertEquals("You pressed Cancel!", declineText);
    }

    @Test
    void promptAlertCheck() {
        // predefine name for the input (might be changed)
        String name = "Jeremy";
        WebElement alertBtn = driver.findElement(By.id("promptBtn"));
        scrollDownToElement(alertBtn);

        alertBtn.click();
        String text = driver.switchTo().alert().getText();

        // send predefined(might be changed) name from variable name into the alert box
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        String demoText = driver.findElement(By.id("demo")).getText();

        // Check if the text of alert as expected
        Assertions.assertEquals("Please enter your name:", text);
        // Check if our answer to the textbox changes actual site state
        Assertions.assertEquals(String.format("Hello %s! How are you today?", name), demoText);
    }

    @Test
    void promptAlertCheckFail() {
        WebElement alertBtn = driver.findElement(By.id("promptBtn"));
        scrollDownToElement(alertBtn);

        alertBtn.click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
        String demoText = driver.findElement(By.id("demo")).getText();

        // Check if the text of alert as expected
        Assertions.assertEquals("Please enter your name:", text);
        // Check if our answer to the textbox changes actual site state
        Assertions.assertEquals("User cancelled the prompt.", demoText);
    }
}