package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.*;

import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;

import java.util.ArrayList;
import java.util.List;
public class cart {

    
        private final String cart = "/cart.html" ;
        private final GUIDriver driver;
        public cart(GUIDriver driver) {
            this.driver=driver;
        }

        //private final By message= By.cssSelector("textarea[id='message-text']");
        //private final By sendMessage= By.xpath("//button[.='Place Order']");
        private final By name =By.id("name");
        private final By country =By.id("country");
        private final By city =By.id("city");
        private final By card =By.id("card");
        private final By month =By.id("month");
        private final By year =By.id("year");
        private final By purchase= By.cssSelector("//button[.='Purchase']");
        private final By deletebutton= By.xpath("tr[1]//td[4]");
        private final By thankyouMess= By.cssSelector("div[class*='sweet-alert']");
        private final By okbutton= By.xpath("//button[.='OK']");
        private final By IdOfSuccessfulPurchase = By.xpath("//p[contains(text(),'Id')]");
        private final By amountOfSuccessfulPurchase = By.xpath( "//p[contains(text(),'Amount')]");
        private final By cardnumOfSuccessfulPurchase = By.xpath("//p[contains(text(),'Card Number')]");
        private final By nameOnsuccesfulPurchase = By.xpath("//p[contains(text(),'Name')]");
        private final By currentDate= By.xpath("//p[contains(text(),'Date')]");
        private final By total= By.cssSelector("h3[id='totalp']");
        private final By price= By.cssSelector("//td[3]");
        private final By cartItems= By.cssSelector("tr[class='success']");
        private final By placeOrder= By.xpath("//button[text()='Place Order']");
        private final By totalLabel= By.xpath("//label[contains(text(),'Total')]");
        private final By pic(String img){

            return By.xpath("img[src*="+img+"]");
        }

        private final By title(String name){
            return By.xpath("//td[text()="+name+"]");

        }
        private final By price(String name){
            return By.xpath("//td[text()="+name+"]//parent::tr[3]");
        }
        private final By deletebutton(String name){

            return By.xpath("//td[text()="+name+"]//parent::tr[4]");
        }


    @Step("Navigate to cart page Page")
    public cart navigateToCart()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"+cart));
        return this;
    }

    @Step("Verify Product Details On Cart")
        public cart verifyProductDetailsOnCart (String name,String img,String price)
        {

            String actualTitle = driver.element().getText(title(name));
            String actualPrice = driver.element().getText(price(name));

            driver.validation().isElementVisible(pic(img));
            driver.validation().assertEquals(actualTitle,name," title  doesn't not match");
            driver.validation().assertEquals(actualPrice,price," price  doesn't not match");

            return this;
        }

    @Step("Verify Product Details On Cart")
    public cart ValidateTotalAmount ()
    {
        double sum=0;
        List<String> list= new ArrayList<>();
        list=driver.element().getElementsText(price);
        String Total=driver.element().getText(total);
        for(String item :list) {
            sum+=Integer.parseInt(item);
        }
        driver.validation().assertEquals(Total,String.valueOf(sum),"total price is incorrect");
        return this;


    }

    @Step("Verify Delete product")
    public cart verifyDeleteProduct (String name){
        List<String> list=driver.element().getElementsText(cartItems);
        driver.element().click(deletebutton(name));
        List<String> list2=driver.element().getElementsText(cartItems);

        driver.validation().assertTrue(list.size()>list2.size(),"item deleted");
        return this;
    }

    @Step("no credentials")
    public cart noCred(){
        driver.element().click(placeOrder).click(purchase);

        String alertText=driver.alert().getAlertText();
        driver.alert().acceptAlert();
        driver.validation().assertEquals(alertText,"Please fill out Name and Creditcard.","allowed purchase without creds");
        return this;
    }

    @Step("Empty cart purchase")
    public navBar emptyCartPurchase(String Name,String Credit){
        navigateToCart();
        driver.element().click(placeOrder);
        driver.element().type(name,Name).type(card,Credit).click(purchase);
        String amount = driver.element().getText(amountOfSuccessfulPurchase);
        driver.validation().isElementVisible(thankyouMess);
        driver.validation().assertFalse(Objects.equals(amount, "0"),"should not allow purchase cart empty");
        return new navBar(driver);


    }




    @Step("placing order")
    public navBar PlaceOrder (String Name,String Country,String City,String Credit,String Month,String Year){String amount = driver.element().getText(amountOfSuccessfulPurchase);
        navigateToCart();
        String names = driver.element().getText(nameOnsuccesfulPurchase);
        String cardnum = driver.element().getText(cardnumOfSuccessfulPurchase);
        String Total=driver.element().getText(total);
            driver.element().click(placeOrder);
            String TotalLabel=driver.element().findElement(totalLabel).getText().replaceAll("[^0-9.]", "");
            driver.element().type(name,Name).
                    type(country,Country).type(city,City).type(card,Credit).
                    type(month,Month).type(year,Year).click(purchase).click(okbutton);
            driver.validation().isElementVisible(IdOfSuccessfulPurchase);
            driver.validation().isElementVisible(thankyouMess);
            driver.validation().assertEquals(TotalLabel,String.valueOf(total),"label shows wrong total price");
        driver.validation().assertEquals(names,Name,"wrong name");
        driver.validation().assertEquals(cardnum,Credit,"wrong credit card number");
        driver.validation().assertEquals(amount,Total,"wrong amount");
           return new navBar(driver);
    }


    }


