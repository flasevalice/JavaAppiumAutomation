package ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            //TITLE = "//*[@resource-id='pcs']//*",
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[contains(@text, 'View article in browser')]",
            SAVE_BUTTON = "//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']",
            MY_LIST_OK_BUTTON = "//*[@resource-id='android:id/button1'][@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            TITLE_WITH_NAME_TPL = "//*[@text='{TITLE}']",
            TITLE_ARTICLE_TPL = "//*[@resource-id='pcs']//*[@text='{TITLE}']",
            SEARCH_RESULT_LOCATOR = "//*[@resource-id='pcs-edit-section-title-description']",
            MY_LIST_NAME = "//*[@text='Learning programming']",
            MY_OLD_LIST_ELEMENT = "//*[@resource-id='org.wikipedia:id/item_title_container']",
            HEADER = "org.wikipedia:id/page_header_view";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultArticleTitle(String article_title) {
        return TITLE_WITH_NAME_TPL.replace("{TITLE}", article_title);
    }

    private static String getTitle(String article_title) {
        return TITLE_ARTICLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 15);
    }

    public WebElement waitForArticleTitleElement(String article_title) {
        String searchResultXpath = getTitle(article_title);
        return this.waitForElementPresent(By.xpath(searchResultXpath), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
        //String searchResult =
        //return getResultArticleTitle(searchResult);
        //WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        //return titleElement.getText();
        //Assert.assertEquals(err_msg, text, articleTitle);
    }

    public void assertElementHasTextByXpath(String expectedText, String err_msg) {
        String actualText = waitForArticleTitleElement(expectedText).getText();
        Assert.assertEquals(
                err_msg,
                expectedText,
                actualText);
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyNewList(String nameOfFolder) {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON), "Cannot find button 'Save'",5);

        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Cannot find button 'Add to list'", 5);

        this.waitForElementAndSendKeys(By.xpath(MY_LIST_NAME_INPUT), nameOfFolder, "Cannot put text into article input", 10);

        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Cannot press 'OK' button",5);
    }

    public void addArticleToMyOldList() {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON), "Cannot find button 'Save'",5);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Cannot find button 'Add to list'", 5);
        this.waitForElementAndClick(By.xpath(MY_OLD_LIST_ELEMENT), "Cannot find old list",5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Cannot close article, cannot find X link", 5);
    }
}