package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        //mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),"Новый список","Невозможно вести название списка",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']" + "[@text='Description (optional)']"),"Описание списка","Невозможно вести описание списка",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),"Невозможно найти элемент - кнопку OK", 5);

        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']"),"Невозможно найти элемент - кнопку 'VIEW LIST'",5);
        //mainPageObject.swipeToTheLeftElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Java (programming language)']"),"Невозможно найти статью 'Java (programming language)' в списке 'Новый список'");
        //mainPageObject.waitForElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" + "[@text='Java (programming language)']"),"Статья осталась в списке", 5);

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.skipMainScreen();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = "Java (programming language)";
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertElementHasTextByXpath(articleTitle, "Actual text isn't expected");

        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyNewList(nameOfFolder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOneOfThem() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //поиск первой
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        // mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //сохранение первой
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        // в новый список
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),"Новый список","Невозможно вести название списка",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']" + "[@text='Description (optional)']"),"Описание списка","Невозможно вести описание списка",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),"Невозможно найти элемент - кнопку OK", 5);
        //поиск второй
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_toolbar_button_search"), "Невозможно найти элемент", 30);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']"), "Невозможно найти элемент", 20);
        //mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Appium']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //сохранение второй
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        //в существующий список
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title_container']"), "Невозможно добавление в существующий список", 10);
        //просмотр списка
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']"),"Невозможно найти элемент - кнопку 'VIEW LIST'",5);
        //удаление одной из
        //mainPageObject.swipeToTheLeftElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Java (programming language)']"),"Невозможно найти статью 'Java (programming language)' в списке 'Новый список'");
        //вторая осталась
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Appium']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //и заголовок
        //mainPageObject.assertElementHasText(By.xpath("//*[@resource-id='pcs']//*[@text='Appium']"), "Appium", "Элемент по данному локатору не содержит ожидаемый текст", 20);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.skipMainScreen();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = "Java (programming language)";
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertElementHasTextByXpath(articleTitle, "Actual text isn't expected");

        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyNewList(nameOfFolder);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.addArticleToMyOldList();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeArticleToDelete(articleTitle);

        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.waitForArticleTitleElement("Appium");
    }
}