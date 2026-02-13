package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.aboutUs;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class contactTest extends BaseTest {
    @Test
    public void verifyReturningToNavbar() {
        new navBar(driver).clickOnContactButton().show().clickOnContactButton()
                .noDetailMessage().
                clickOnContactButton().sendMessage(testData.getJsonData("Email"),
                        testData.getJsonData("contactName"),testData.getJsonData("Message"));
    }
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("contact");
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
