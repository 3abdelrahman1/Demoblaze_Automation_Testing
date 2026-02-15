package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.category;
import DemoBlaze.pages.navBar;
import DemoBlaze.pages.productDescribe;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class productDescribeTest extends BaseTest{

    @Test
    public void doubleclickonaddtocart() {
        new category(driver).GoToLaptops().productAddress(testData.getJsonData("Monitors.product2.name"))
                .addToCartDoubleClick()
                .PlaceOrder(testData.getJsonData("name"),"","",testData.getJsonData("credit")
                ,"","");
    }
    @Test
    public void validateCart() {
        new navBar(driver).clickOnCartButton().verifyProductDetailsOnCart(testData.getJsonData("Phones.product1.name"),
                testData.getJsonData("Phones.product1.img"),
                testData.getJsonData("Phones.product1.price")).ValidateTotalAmount();

    }


    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("purchase");
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
