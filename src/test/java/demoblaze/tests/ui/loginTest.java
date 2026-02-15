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
public class loginTest extends BaseTest{
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Verify user can login with valid credentials")
    @Test
    public void successfulLogIn()
    {
        new UserManagementAPI().LoginAccount(
                        testData.getJsonData("validLogin.username"),
                        testData.getJsonData("validLogIn.password")).verifySuccessfulLogIn();
    }

    @Description("Verify user can login with valid credentials")
    @Test
    public void invalidPassword()
    {
        new UserManagementAPI().LoginAccount(
                testData.getJsonData("inValidPassword.username"),
                testData.getJsonData("inValidPassword.password")).verifyLoginInvalidpassword();
    }
    @Description("Verify user can login with valid credentials")
    @Test
    public void InvalidUsername()
    {
        new UserManagementAPI().LoginAccount(
                testData.getJsonData("inValidUsername.username"),
                testData.getJsonData("inValidUsername.password")).verifyLoginInvalidname();
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login");




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
