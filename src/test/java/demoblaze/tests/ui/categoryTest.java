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
    public void testNextPrevButton() {
        new category(driver).nextButton().PrevButton();

    }

    @Test
    public void testHomecards() {
        new category(driver).VerifyHomecards();

    }
    @Test
    public void testCategoryDetails() {
        new category(driver).categoryChoice(secTestData.getJsonData("category"),secTestData.getJsonData("product1.name"),secTestData.getJsonData("product1.price"),secTestData.getJsonData("product1.text"));

    }
    @Test
    public void testLaptopDetails() {
        new category(driver).categoryChoice(testData.getJsonData("category"),testData.getJsonData("product1.name"),testData.getJsonData("product1.price"),testData.getJsonData("product1.text"));

    }
    @Test
    public void testMonitorsDetails() {
        new category(driver).categoryChoice(thirdTestData.getJsonData("category"),thirdTestData.getJsonData("product1.name"),thirdTestData.getJsonData("product1.price"),thirdTestData.getJsonData("product1.text"));

    }
    @Test
    public void testProductAddress() {
        new category(driver).prodAddress(testData.getJsonData("category"),testData.getJsonData("product1.name"));
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("laptops");
         secTestData= new JsonReader("phones");
         thirdTestData = new JsonReader("monitors");

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
