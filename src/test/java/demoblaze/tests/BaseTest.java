package demoblaze.tests;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.WebDriverProvider;
import DemoBlaze.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.WebDriverProvider;
import DemoBlaze.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
    protected static GUIDriver driver;
    protected JsonReader testData;
    protected JsonReader secTestData;




    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
    @BeforeClass
    public void refresh() {
        driver.browser().refreshPage();
    }


    @BeforeSuite
    public void setUp() {
        driver = new GUIDriver();

    }
    @AfterSuite
    public void tearDown() {
        driver.quitDriver();
    }
}
