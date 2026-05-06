package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.cart;
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
@Story("Cart")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdelrahman")
@UITest
public class cartTest extends BaseTest{

    @Test(priority = 3)
    public void verifyCartItems() {
        new navBar(driver).navigate().gotocategory()
                .GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name")).addToCart().
                clickOnLabel().gotocategory().
                GoToLaptops().productAddress(secTestData.getJsonData("Laptops.product1.name")).addToCart().clickOnLabel().
                clickOnCartButton().
                verifyProductDetailsOnCart(secTestData.getJsonData("Phones.product1.name"),
                        secTestData.getJsonData("Phones.product1.price")).
                verifyProductDetailsOnCart(secTestData.getJsonData("Laptops.product1.name"),
                        secTestData.getJsonData("Laptops.product1.price"))
                   .ValidateTotalAmount().verifyDeleteProduct(secTestData.getJsonData("Phones.product1.name"));

    }
    @Test(priority = 2)
    public void noCredentials() {
        new cart(driver).navigateToCart().noCred();
    }

    @Test(priority = 1)
    public void EmptyCartPurchase() {
        new cart(driver).navigateToCart().emptyCartPurchase(
                testData.getJsonData("name"),
                testData.getJsonData("credit"));
    }
    @Test(priority = 4)
    public void InvalidPurchase() {
        new navBar(driver).navigate().gotocategory()
        .GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name")).addToCart().clickOnLabel().gotocategory().
        GoToLaptops().productAddress(secTestData.getJsonData("Laptops.product1.name")).addToCart().clickOnLabel().gotocategory().
                GoToPhones().productAddress(secTestData.getJsonData("Phones.product2.name")).addToCart().clickOnLabel().clickOnCartButton()
                .PlaceOrder(
                secTestData.getJsonData("name"),secTestData.getJsonData("country"),
                secTestData.getJsonData("city"),secTestData.getJsonData("credit"),
                secTestData.getJsonData("month"),secTestData.getJsonData("year"));

    }
    @Test(priority = 5)
    public void validPurchase() {
        new navBar(driver).navigate().gotocategory()
                .GoToPhones().productAddress(secTestData.getJsonData("Phones.product1.name")).addToCart().clickOnCartButton().verifyProductDetailsOnCart(secTestData.getJsonData("Phones.product1.name"),
                        secTestData.getJsonData("Phones.product1.price")
                )
                .PlaceOrder(
                        testData.getJsonData("name"),testData.getJsonData("country"),
                        testData.getJsonData("city"),testData.getJsonData("credit"),
                        testData.getJsonData("month"),testData.getJsonData("year"));

    }
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("purchase");
        secTestData=new JsonReader("invalidPurchase");
        driver = new GUIDriver();


    }
    /*@BeforeMethod
    public void setUp() {




    }

     */
    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

}
