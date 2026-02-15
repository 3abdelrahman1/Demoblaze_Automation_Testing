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
    public void verifyCartItems() {
        new navBar(driver).clickOnHomeButton().gotocategory()
                .GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name")).addToCart().clickOnHomeButton().gotocategory().
                GoToLaptops().productAddress(secTestData.getJsonData("Laptops.product1.name")).addToCart().clickOnHomeButton().gotocategory().
                GoToMonitors().productAddress(secTestData.getJsonData("Monitors.product1.name")).addToCart().clickOnHomeButton().clickOnCartButton().
                verifyProductDetailsOnCart(secTestData.getJsonData("Phones.product1.name"),secTestData.getJsonData("Phones.product1.img"),
                        secTestData.getJsonData("Phones.product1.price")
                     ).
                verifyProductDetailsOnCart(secTestData.getJsonData("Laptops.product1.name"),
                        secTestData.getJsonData("Laptops.product1.img"),
                                secTestData.getJsonData("Laptops.product1.price")
                       ).verifyProductDetailsOnCart(secTestData.getJsonData("Monitors.product1.name")
                ,secTestData.getJsonData("Monitors.product1.img"),secTestData.getJsonData("Monitors.product1.price")
                       )
                .ValidateTotalAmount().verifyDeleteProduct(secTestData.getJsonData("Laptops.product1.name"));
    }
    @Test
    public void noCredentials() {
        new cart(driver).noCred();
    }

    @Test
    public void EmptyCartPurchase() {
        new cart(driver).navigateToCart().emptyCartPurchase(
                testData.getJsonData("name"),
                testData.getJsonData("cardNumber"));
    }
    @Test
    public void InvalidPurchase() {
        new navBar(driver).clickOnHomeButton().gotocategory()
        .GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name")).addToCart().clickOnHomeButton().gotocategory().
        GoToLaptops().productAddress(secTestData.getJsonData("Laptops.product1.name")).addToCart().clickOnHomeButton().gotocategory().
                GoToMonitors().productAddress(secTestData.getJsonData("Monitors.product1.name")).addToCart().clickOnHomeButton().clickOnCartButton()
                .PlaceOrder(
                secTestData.getJsonData("name"),secTestData.getJsonData("country"),
                secTestData.getJsonData("city"),secTestData.getJsonData("credit"),
                secTestData.getJsonData("month"),secTestData.getJsonData("year"));

    }
    @Test
    public void validPurchase() {
        new navBar(driver).clickOnHomeButton().gotocategory()
                .GoToPhones().productAddress(testData.getJsonData("Phones.product1.name")).addToCart().clickOnHomeButton().gotocategory().
                GoToLaptops().productAddress(testData.getJsonData("Laptops.product1.name")).addToCart().clickOnHomeButton().gotocategory().
                GoToMonitors().productAddress(testData.getJsonData("Monitors.product1.name")).addToCart().clickOnHomeButton().clickOnCartButton()
                .PlaceOrder(
                        testData.getJsonData("name"),testData.getJsonData("country"),
                        testData.getJsonData("city"),testData.getJsonData("credit"),
                        testData.getJsonData("month"),testData.getJsonData("year"));

    }
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("purchase");
        secTestData=new JsonReader("invalidPurchase");
        new navBar(driver).navigate();


    }
    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();


    }
    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

}
