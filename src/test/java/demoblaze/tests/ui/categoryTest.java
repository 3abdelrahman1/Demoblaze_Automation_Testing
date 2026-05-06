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
@Epic("DemoBlaze Site")
@Feature("UI User Test")
@Story("Items visibility")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdelrahman")
@UITest
public class categoryTest extends BaseTest {

    @Test
    public void testNextButton() {
        new category(driver).navigateTo().nextButton();

    }
    @Test
    public void testPrevButton() {
        new category(driver).navigateTo().PrevButton();
    }

    @Test
    public void testHomecards() {
        new category(driver).navigateTo().VerifyHomecards();

    }
    @Test
    public void testPhoneDetails() {
        new category(driver).navigateTo().GoToPhones().categoryChoice(secTestData.getJsonData("Phones.product1.name"),secTestData.getJsonData("Phones.product1.price"),secTestData.getJsonData("Phones.product1.text"));

    }
    @Test
    public void testLaptopDetails() {
        new category(driver).navigateTo().GoToLaptops().categoryChoice(secTestData.getJsonData("Laptops.product1.name"),secTestData.getJsonData("Laptops.product1.price"),secTestData.getJsonData("Laptops.product1.text"));

    }
    @Test
    public void testMonitorsDetails() {
        new category(driver).navigateTo().GoToMonitors().categoryChoice(secTestData.getJsonData("Monitors.product1.name"),secTestData.getJsonData("Monitors.product1.price"),secTestData.getJsonData("Monitors.product1.text"));

    }
    @Test
    public void testProductAddress() {
        new category(driver).navigateTo().GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name"));
    }

    @BeforeClass
    protected void preCondition() {
        //testData = new JsonReader("purchase");
         secTestData= new JsonReader("invalidPurchase");
        driver = new GUIDriver();
        // thirdTestData = new JsonReader("monitors");
       // new navBar(driver).navigate();
    }
     /*   @BeforeMethod
        public void setUp() {
            driver = new GUIDriver();


        }

      */
    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
