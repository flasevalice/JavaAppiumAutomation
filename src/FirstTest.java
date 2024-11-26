import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        assertElementHasText(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Java (programming language)", "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //Assert.assertEquals("Некорректный заголовок", "Java (programming language)", articleTitle);

    }

    @Test
    public void searchFieldHasTextTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search Wikipedia", "Элемент по данному локатору не содержит ожидаемый текст", 15);
    }

    @Test
    public void findTitlesAndCancelSearchTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Невозможно найти элемент", 15);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"), "Невозможно найти элемент", 15);
    }

    @Test
    public void swipeArticleTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Невозможно найти элемент", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']"), "Невозможно найти элемент", 20);
        swipeUpToFindElement(By.xpath("//*[contains(@text, 'View article in browser')]"), "Невозможно найти статью", 20);
    }

    @Test
    public void saveFirstArticleToMyListTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //waitForElementAndClick(By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"), "Невозможно найти элемент", 15);
        //waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        //waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button'][@text='Got it']"),"Невозможно найти элемент 'Got it'", 5);
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),"Новый список","Невозможно вести название списка",5);
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']" + "[@text='Description (optional)']"),"Описание списка","Невозможно вести описание списка",5);
        waitForElementAndClick(By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),"Невозможно найти элемент - кнопку OK", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']"),"Невозможно найти элемент - кнопку 'VIEW LIST'",5);
        swipeToTheLeftElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Java (programming language)']"),"Невозможно найти статью 'Java (programming language)' в списке 'Новый список'");
        waitForElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" + "[@text='Java (programming language)']"),"Статья осталась в списке", 5);
    }

    @Test
    public void amongOfNotEmptySearchTest() {
        String searchLine = "Linkin Park Discography";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anything by request " + searchLine,15);

        int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));

        Assert.assertTrue("We found too few results",amountOfSearchResults > 0
        );
    }

    @Test
    public void amongOfEmptySearchTest() {
        String searchLine = "rgehrthdsdfhfhdh";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String emptyResultLabel = "//*[@text='No results']";
        waitForElementPresent(By.xpath(emptyResultLabel),"Cannot find empty result label by the request " + searchLine,15);

        assertElementNotPresent(By.xpath(searchResultLocator),"We've find some results by the request '" + searchLine + "'");
    }

    @Test
    public void changeScreenOrientationOnSearchResultsTest() {
        String searchLine = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='" + articleDescription+"']"), "Невозможно найти элемент", 30);


        String searchResultLocator = "//*[@resource-id='pcs-edit-section-title-description']";

        WebElement titleBeforeLandscapeRotation = waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        //String titleBeforeLandscapeRotation = waitForElementAndGetAttribute(By.xpath(searchResultLocator),"text","Cannot find title of article",30);
        rotateLandscape();
        WebElement titleAfterLandscapeRotation = waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        //String titleAfterLandscapeRotation = waitForElementAndGetAttribute(By.xpath(searchResultLocator),"text","Cannot find title of article",30);

        Assert.assertEquals( "Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterLandscapeRotation);

        rotatePortrait();

        WebElement titleAfterSecondRotation = waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        Assert.assertEquals( "Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterSecondRotation);
    }


    @Test
    public void changeSearchArticleInBackgroundTest() {
        String searchLine = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='"+articleDescription+"']";
        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find element", 15);
        driver.runAppInBackground(15);
        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find element after returning from background ", 15);
    }


    //не работает, поэтому заменено отображением
    private String waitForElementAndGetAttribute(By by, String attribute, String err_msg, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void rotateLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private void rotatePortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    private void assertElementNotPresent(By by, String err_msg) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }


    protected void swipeToTheLeftElement(By by, String err_msg){
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

    private void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    private void swipeUpQuick() {
        swipeUp(200);
    }

    private void swipeUpToFindElement(By by, String err_msg, int maxSwipes) {
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