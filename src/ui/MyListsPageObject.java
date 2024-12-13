package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(By.xpath(folderNameXpath), "Cannot find folder by name " + nameOfFolder, 5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article still present with title " + articleTitle, 5);
    }

    public void waitForArticleToAppearByTitle(String articleTitleJavascript) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitleJavascript);
        this.waitForElementPresent(By.xpath(articleXpath), "Cannot find saved article by title " + articleTitleJavascript, 5);
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);

        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeToTheLeftElement(By.xpath(articleXpath), "Cannot find saved article");

        this.waitForArticleToDisappearByTitle(articleTitle);
    }
}