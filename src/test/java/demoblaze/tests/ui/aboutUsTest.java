package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import DemoBlaze.pages.aboutUs;
import DemoBlaze.pages.navBar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class aboutUsTest extends BaseTest{

    @Test
    public void verifyaboutUsVideo() {
        new navBar(driver).
                clickOnaboutUsButton().
                playvid().
                clickonMuteButton().
                SliderControl();
    }

    @Test
    public void verifyReturningToNavbar() {
        new aboutUs(driver).closeAboutUsPage();
    }

    //Configurations

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new navBar(driver).navigate();

    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
