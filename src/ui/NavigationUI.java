package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']";

    public NavigationUI (AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(By.xpath(MY_LISTS_LINK), "Cannot find navigation button to 'My lists'", 5);
    }
}