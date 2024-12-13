package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    //не работает из-за версии верстки, поэтому заменено отображением
    public String waitForElementAndGetAttribute(By by, String attribute, String err_msg, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void rotateLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotatePortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    public void assertElementPresent(By by, String err_msg)
    {
        int amountOfTitles = getAmountOfElements(by);
        if (amountOfTitles < 1) {
            String defaultMessage = "An element '" + by.toString() + "' not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public void assertElementNotPresent(By by, String err_msg) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }


    public void swipeToTheLeftElement(By by, String err_msg){
        WebElement element = waitForElementPresent(by, "Невозможно найти элемент до которого свайпать. \n"
                + err_msg, 5);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String err_msg, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if(alreadySwiped>maxSwipes) {
                waitForElementPresent(by, "Невозможно найти элемент \n"+ err_msg, 0);
                return;
            }
            swipeUpQuick();
            ++ alreadySwiped;
        }
    }

    public WebElement waitForElementPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String err_msg) {
        return waitForElementPresent(by, err_msg, 15);
    }

    public WebElement waitForElementAndClick(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String text, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        String articleTitle = element.getText();
        Assert.assertEquals(err_msg, text, articleTitle);
    }
}