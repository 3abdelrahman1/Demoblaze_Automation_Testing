package demoblaze.tests.ui;
import DemoBlaze.apis.UserManagementAPI;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.navBar;
import DemoBlaze.utils.TimeManager;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class signupTest extends BaseTest{
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Verify user can't signup with same username twice'")
    @Test
    public void SignupUsedAccount()
    {
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("validUser.username")+timestamp,testData.getJsonData("validUser.password")).
                verifyUserAlreadySignedUp();
    }

    @Description("Verify user create account with valid credentials")
    @Test
    public void SignupNewAccount()
    {
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("validUser.username"),
                        testData.getJsonData("validUser.password")).verifyUserSignedSuccessfuly();
    }
    @Description("Verify user can't signup with invalid credentials")
    @Test
    public void InvalidSignup()
    {
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("invalidUser.username"),
                testData.getJsonData("validUser.password")).verifySignupInvalidname();
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("signup");




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
