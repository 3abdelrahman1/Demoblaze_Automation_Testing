package DemoBlaze.drivers;



import DemoBlaze.utils.actions.AlertActions;
import DemoBlaze.utils.actions.BrowserActions;
import DemoBlaze.utils.actions.ElementActions;
import DemoBlaze.utils.actions.FrameActions;
import DemoBlaze.utils.dataReader.PropertyReader;
import DemoBlaze.utils.logs.LogsManager;
import DemoBlaze.validations.softAssert;
import DemoBlaze.validations.hardAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final String browser = PropertyReader.getProperty("browserType");
    private  ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public GUIDriver()
    {
        LogsManager.info("Initializing GUIDriver with browser: " + browser);
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        LogsManager.info("Starting driver for browser: " + browserType);
        AbstractDriver abstractDriver = browserType.getDriverFactory(); //local
        WebDriver driver = ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementActions element() {
        return new ElementActions(get());
    }
    public BrowserActions browser() {
        return new BrowserActions(get());
    }

    public AlertActions alert() {
        return new AlertActions(get());
    }
    //soft assertions
    public softAssert validation() {
        return new softAssert(get());
    }
    // hard assertions
    public hardAssert verification() {
        return new hardAssert(get());
    }
    public WebDriver get() {
        return driverThreadLocal.get();
    }

    public  void quitDriver() {
        driverThreadLocal.get().quit();
    }
}

