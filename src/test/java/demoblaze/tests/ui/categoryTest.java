package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.pages.category;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
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
        new category(driver).productAddress(testData.getJsonData("category"),testData.getJsonData("product1.name"));
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("laptops");
         secTestData= new JsonReader("phones");
         thirdTestData = new JsonReader("monitors");
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
