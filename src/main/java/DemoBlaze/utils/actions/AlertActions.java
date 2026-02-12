package DemoBlaze.utils.actions;



import DemoBlaze.utils.WaitManager;
import DemoBlaze.utils.logs.LogsManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AlertActions {
    private final WebDriver driver;
    private  final WaitManager waitManager;
    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /**
     * Accepts the alert
     */
    public void acceptAlert()
    {
        waitManager.fluentWait().until(d ->
        {
            try {
                d.switchTo().alert().accept();
                return true;
            }
            catch (Exception e)
            {
                LogsManager.error("Failed to accept alert:" , e.getMessage());
                return  false;
            }
        });
    }

//for multiple alerts appearing
public void acceptMultipleAlerts() {

    while (true) {
        try {
             waitManager.fluentWait()
                    .until(driver -> driver.switchTo().alert());

            driver.switchTo().alert().accept();

        }
        catch (TimeoutException e) {
            break; // No more alerts
        }
    }
}
    /**
     * Dismisses the alert
     */
    public void dismissAlert()
    {
        waitManager.fluentWait().until(d ->
        {
            try {
                d.switchTo().alert().dismiss();
                return true;
            }
            catch (Exception e)
            {
                LogsManager.error("Failed to dismiss alert:" , e.getMessage());
                return  false;
            }
        });
    }
    /**
     * Gets the text from the alert
     * @return the text of the alert
     */
    public String getAlertText()
    {
        return waitManager.fluentWait().until(d ->
        {
            try {
                String text = d.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;
            }
            catch (Exception e)
            {
                LogsManager.error("Failed to get alert text:", e.getMessage());
                return null;
            }
        });
    }
    /**
     * Sets the text in the alert
     * @param text the text to set in the alert
     */
    public void setAlertText(String text)
    {
        waitManager.fluentWait().until(d ->
        {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            }
            catch (Exception e)
            {
                LogsManager.error("Failed to set alert text:", e.getMessage());
                return false;
            }
        });
    }
}

