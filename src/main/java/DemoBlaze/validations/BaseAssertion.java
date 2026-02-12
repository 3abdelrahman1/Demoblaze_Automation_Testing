package DemoBlaze.validations;



import DemoBlaze.FileUtils;
import DemoBlaze.utils.WaitManager;
import DemoBlaze.utils.actions.ElementActions;
import DemoBlaze.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static DemoBlaze.utils.report.AllureConstants.USER_DIR;
import static java.nio.file.Paths.*;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementActions;

    protected BaseAssertion() {

    }

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);

    protected BaseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element is not visible: " + locator);
    }

    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }



    // Verify that file exists
    public void assertFileExists(String fileName, String message) {
        boolean fileExists = FileUtils.isFileExist(fileName,3);
        assertTrue(FileUtils.isFileExists(fileName), message);
    }
    //assert PIP is now active
    public void assertPIP(String message) {

        Boolean inPip = (Boolean)((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.pictureInPictureElement != null;");
        assertTrue(inPip,message);
    }

}
