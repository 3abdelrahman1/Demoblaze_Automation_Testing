package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.aboutUs;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;


public class contactTest extends BaseTest {
    @Test(priority = 1)
    public void sendMessageWithNoDetails() {
        new navBar(driver).navigate().clickOnContactButton()
                .noDetailMessage();

    }
    @Test(priority = 2)
    public void sendMessage() {
        new navBar(driver).navigate().clickOnContactButton().sendMessage(testData.getJsonData("Email"),
                        testData.getJsonData("contactName"),testData.getJsonData("Message"));
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("contact");


    }
   /* @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new navBar(driver).navigate();}
*/

   /* @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

    */
}
