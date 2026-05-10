package demoblaze.tests.ui;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.drivers.UITest;
import demoblaze.tests.BaseTest;
import DemoBlaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import DemoBlaze.pages.aboutUs;
import DemoBlaze.pages.navBar;
import org.testng.annotations.*;


public class aboutUsTest extends BaseTest{

    @Test
    public void verifyaboutUsVideo() {
        new navBar(driver).navigate().
                clickOnaboutUsButton().
                playvid().
                clickonMuteButton().
                SliderControl();
    }



    //Configurations
/*
    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();


    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

 */
}
