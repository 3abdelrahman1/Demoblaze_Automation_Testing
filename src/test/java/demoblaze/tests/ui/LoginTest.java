package demoblaze.tests.ui;

import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.pages.navBar;
import DemoBlaze.utils.dataReader.JsonReader;
import demoblaze.tests.BaseTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(priority = 4)
    public void loginSuccessful() {
        new navBar(driver).navigate().clickOnLoginButton().loginsucccess(testData.getJsonData("validUser.username"),testData.getJsonData("validUser.password"))
                .userNameIcon();

    }
    @Test(priority = 3)
    public void InvalidUsername() {
        new navBar(driver).navigate().clickOnLoginButton().loginWrongUsername(testData.getJsonData("invalidUsername.username"),testData.getJsonData("invalidUsername.password"));
    }
    @Test(priority = 2)
    public void InvalidPassword() {
        new navBar(driver).navigate().clickOnLoginButton().loginWrongPassword(testData.getJsonData("invalidPassword.username"),testData.getJsonData("invalidPassword.password"));
    }
    @Test(priority = 1)
    public void empty() {
        new navBar(driver).navigate().clickOnLoginButton().empty(testData.getJsonData("empty.username"),testData.getJsonData("empty.password"));
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login");

    }

    @BeforeSuite
    public void setUp() {
        driver = new GUIDriver();


    }

}
