package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import DemoBlaze.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class logIn {

        private final GUIDriver driver;
        public logIn(GUIDriver driver) {
            this.driver =driver;
        }
        private final By username = By.cssSelector("input[id='loginusername']");
        private final By password = By.cssSelector("input[id='loginpassword']");
        private final By loginbutton = By.xpath("//button[.='Log in']");
        private final By logoutButtonNavbar = By.xpath("//a[.='Log out']");
        private final By quit= By.xpath("//h5[.='Log in']//following::button[@class='close'][1]");
        private final By closebutton = By.xpath("//div[@id='logInModal']//button[.='Close']");
        private final By barLogIn = By.id("login2");
        private final By logInModal= By.id("logInModal");
    ;
    @Step("successful login")
    public navBar show(){
        driver.validation().isElementVisible(logInModal);
        driver.element().click(quit);
        return new navBar(driver);
    }

        @Step("successful login")
        public navBar loginsucccess(String name,String pass)
        {
            driver.element().type(username,name).type(password,pass).click(loginbutton);
            return new navBar(driver);
        }
        @Step("Wrong username")
        public navBar loginWrongUsername(String name,String pass)
        {
            driver.element().type(username,name).type(password,pass).click(loginbutton);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();
            driver.verification().assertEquals(alertText,"User does not exist.","Wrong username");
            return new navBar(driver);
        }
    @Step("Wrong password")
    public navBar loginWrongPassword(String name,String pass)
    {
        driver.element().type(username,name).type(password,pass).click(loginbutton);
        String alertText=driver.alert().getAlertText();
        driver.alert().acceptAlert();
        driver.verification().assertEquals(alertText,"Wrong password.","Wrong password");
        return new navBar(driver);
    }
    @Step("Empty username or password")
    public navBar empty(String name,String pass)
    {
        driver.element().type(username,name).type(password,pass).click(loginbutton);
        String alertText=driver.alert().getAlertText();
        driver.alert().acceptAlert();
        driver.verification().assertEquals(alertText,"Please fill out Username and Password.","No credintials");
        return new navBar(driver);
    }


    }


