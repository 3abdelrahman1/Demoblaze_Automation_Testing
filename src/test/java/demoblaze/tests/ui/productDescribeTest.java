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

@Epic("DemoBlaze Site")
@Feature("UI User Test")
@Story("Product description")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdelrahman")
@UITest
public class productDescribeTest extends BaseTest{

    @Test
    public void validateProductDescription() {
        new category(driver).navigateTo().GoToLaptops().productAddress(testData.getJsonData("Laptops.product1.name"))
                .verifyProductDetails(testData.getJsonData("Phones.product1.name")
                        ,testData.getJsonData("Phones.product1.price"),testData.getJsonData("Phones.product1.text"))
                .addToCart().clickOnCartButton().PlaceOrder(testData.getJsonData("name"),"","",testData.getJsonData("credit")
                ,"","");
    }



    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("invalidPurchase");
       // new navBar(driver).navigate();



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
