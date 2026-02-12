package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
public class signup {
    
    
        private final GUIDriver driver;
        public signup(GUIDriver driver) {
            this.driver =driver;
        }
        private final By username = By.cssSelector("input[id='sign-username']");
        private final By password = By.cssSelector("input[id='sign-password']");
        private final By Signupbutton = By.xpath("//button[.='Sign up']");
        private final By closebutton = By.xpath("//div[@id='signInModal']//button[.='Close']");


        @Step("successful signup")
        public navBar signupsuccess(String name,String pass)
        {
            driver.element().type(username,name).type(password,pass);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();
            driver.element().click(closebutton);
            driver.verification().assertEquals(alertText,"Sign up successful.","signup fail");
            return new navBar(driver);
        }
        @Step("signup with same account")
        public navBar signupfail(String name,String pass){
            driver.element().type(username,name).type(password,pass).click(Signupbutton);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();
            driver.element().click(closebutton);
            driver.verification().assertEquals(alertText,"This user already exist.","signup error wrong message");
            return new navBar(driver);

        }

    }


