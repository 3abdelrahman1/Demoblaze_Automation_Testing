package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.cart;
import DemoBlaze.pages.category;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class cartTest extends BaseTest{

    @Test
    public void EmptyCartPurchase() {
        new cart(driver).navigateToCart().emptyCartPurchase(testData.getJsonData("name"),
                testData.getJsonData("country"),
                testData.getJsonData("city"),
                testData.getJsonData("cardNumber"),
                testData.getJsonData("month"),
                testData.getJsonData("year"));

    }
    @Test
    public void validateInfo() {
        new cart(driver).validateInfo(testData.getJsonData("name"),testData.getJsonData("cardNumber"));
    }
    @Test
    public void InvalidPurchase() {
        new cart(driver).
    }
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("purchase");


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
