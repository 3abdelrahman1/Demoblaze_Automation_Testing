package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.category;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class categoryTest extends BaseTest {


    @Test
    public void testHomecards() {
        new category(driver).VerifyHomecards();

    }
    @Test
    public void testNextPrevButton() {
        new category(driver).nextButton().PrevButton();

    }
    @Test
    public void testNextPrevButton() {
        new category(driver).nextButton().PrevButton();

    }

        @BeforeMethod
        public void setUp() {
            driver = new GUIDriver();
            new navBar(driver).navigate();

        }
    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
