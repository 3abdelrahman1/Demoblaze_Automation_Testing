package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import DemoBlaze.pages.navBar;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("DemoBlaze Site")
@Feature("UI User Test")
@Story("NavBar buttons")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdelrahman")
@UITest
public class navBarTest extends BaseTest{
    @Test
    public void cartButtonTest() {
        new navBar(driver).navigate().clickOnCartButton().cartButton().navigate();
    }
    @Test
    public void logInButtonTest() {
        new navBar(driver).navigate().clickOnLoginButton().show();
    }

    @Test
    public void aboutUsButtonTest() {
        new navBar(driver).navigate().clickOnaboutUsButton().show();
    }
    @Test
    public void contactButtonTest() {
        new navBar(driver).navigate().clickOnContactButton().show();
    }
    /*@BeforeSuite
    public void setUp() {
        driver = new GUIDriver();


    }
   /* @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }*/
}
