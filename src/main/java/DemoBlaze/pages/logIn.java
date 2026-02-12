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

        @Step("successful login")
        public NavigationBar loginsucccess(String name,String pass)
        {
            driver.element().type(username,name).type(password,pass);

            String logout_text=driver.element().click(loginbutton).findElement(logoutButtonNavbar).getText();
            driver.verification().assertEquals(logout_text,"Log out","login fail");
            return new NavigationBar(driver);
        }
        @Step("login fail")
        public NavigationBar loginfail(String name,String pass)
        {
            driver.element().type(username,name).type(password,pass).click(loginbutton);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();
            driver.element().click(closebutton);
            driver.verification().assertEquals(alertText,"User does not exist.","login fail");
            return new NavigationBar(driver);
        }
    }

}
