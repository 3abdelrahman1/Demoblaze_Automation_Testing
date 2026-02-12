package demoblaze.tests;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.WebDriverProvider;
import DemoBlaze.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;


import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.WebDriverProvider;
import DemoBlaze.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;




    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
