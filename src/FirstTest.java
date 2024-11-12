import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:/GitHub/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia_2.7.50449-r-2023-07-31_Apkpure.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Невозможно найти элемент", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java", "Невозможно найти элемент", 15);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 15);
    }

    @Test
    public void cancelSearchTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Невозможно найти элемент", 10);
        waitForElementPresent(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Невозможно найти элемент", 15);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Элемент остался", 15);
    }

    @Test
    public void compareArticleTitleTest() throws InterruptedException {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
//        WebElement titleElement = waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Невозможно найти элемент", 40);
//        String articleTitle = titleElement.getText();
        assertElementHasText(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Java (programming language)", "Элемент по данному локатору не содержит ожидаемый текст", 15);
        //Assert.assertEquals("Некорректный заголовок", "Java (programming language)", articleTitle);

    }

    @Test
    public void searchFieldHasTextTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search Wikipedia", "Элемент по данному локатору не содержит ожидаемый текст", 15);
    }

    private WebElement waitForElementPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String err_msg) {
        return waitForElementPresent(by, err_msg, 15);
    }

    private WebElement waitForElementAndClick(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String text, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        String articleTitle = element.getText();
        Assert.assertEquals(err_msg, text, articleTitle);
    }
}