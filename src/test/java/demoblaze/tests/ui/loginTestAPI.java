package demoblaze.tests.ui;
import DemoBlaze.apis.UserManagementAPI;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.TimeManager;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

public class loginTestAPI extends BaseTest{
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Validate user can login with valid credentials")
    @Test(priority =1)
    public void successfulLogIn()
    {
        new UserManagementAPI().LoginAccount(
                        testData.getJsonData("validLogin.username"),
                        testData.getJsonData("validLogin.password")).LoginCheck().verifySuccessfulLogIn("validLogin.token");
    }

    @Description("Validate error message when signing in with invalid password")
    @Test
    public void invalidPassword()
    {
        new UserManagementAPI().LoginAccount(
                testData.getJsonData("inValidPassword.username"),
                testData.getJsonData("inValidPassword.password")).verifyLoginInvalidpassword();
    }
    @Description("Validate error message when signing in with invalid username")
    @Test
    public void InvalidUsername()
    {
        new UserManagementAPI().LoginAccount(
                testData.getJsonData("inValidUsername.username"),
                testData.getJsonData("inValidUsername.password")).verifyLoginInvalidUsername();
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("loginAPI");

    }
/*
   @BeforeSuite
    public void setUp() {
        driver = new GUIDriver();


    }


    @AfterSuite
    public void tearDown() {
        driver.quitDriver();
    }
*/

}
