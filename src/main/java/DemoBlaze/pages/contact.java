package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import DemoBlaze.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class contact {
    
    
        private final GUIDriver driver;
        public contact(GUIDriver driver) {
            this.driver = driver;
        }
        private final By Email = By.cssSelector("input[id='recipient-email']");
        private final By name = By.cssSelector("input[id='recipient-name']");
        private final By message= By.cssSelector("textarea[id='message-text']");
        private final By sendMessage= By.xpath("//button[.='Send message']");
        private final By closebutton = By.xpath("//div[@id='exampleModal']//button[.='Close']");
        private final By quit= By.xpath("//h5[.='New message']//following::button[@class='close'][1]");
        private final By modal= By.xpath("//div[@id='exampleModal' and @class='modal fade show']");

    @Step("contact modal show up")
    public navBar show() {
        driver.validation().isElementVisible(modal);
        driver.element().click(quit);
        return new navBar(driver);
    }
    @Step("click on send message without entering any detail")
    public navBar noDetailMessage(){

        driver.element().type(Email,"").type(name,"")
                .type(message,"")
                .click(sendMessage);
        String alertText=driver.alert().getAlertText();
        driver.alert().acceptAlert();
        driver.verification().assertFalse(alertText.equals("Thanks for the message!!"),"signup error wrong message");
        return new navBar(driver);
    }

        @Step("send message")
        public navBar sendMessage(String email,String Name,String Mess){
            driver.element().type(Email,email).type(name,Name).type(message,Mess).click(sendMessage);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();

            driver.verification().assertEquals(alertText,"Thanks for the message!!","signup error wrong message");
            return new navBar(driver);

        }




    }


