package demoblaze.tests.ui;

import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.pages.navBar;
import DemoBlaze.utils.dataReader.JsonReader;
import demoblaze.tests.BaseTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class logOutTest extends BaseTest {
    @Test(priority = 1)
    public void logOut() {
        new navBar(driver).clickOnLogoutButton();

    }
    @AfterSuite
    public void tearDown() {
        driver.quitDriver();
    }
}
