package DemoBlaze.pages;

import DemoBlaze.utils.WaitManager;
import DemoBlaze.utils.logs.LogsManager;
import DemoBlaze.validations.softAssert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.*;

import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
public class cart {

    
        private final String cart = "/cart.html" ;
        private final GUIDriver driver;
        public cart(GUIDriver driver) {
            this.driver=driver;
        }
        WaitManager wait;
        //private final By message= By.cssSelector("textarea[id='message-text']");
        //private final By sendMessage= By.xpath("//button[.='Place Order']");
        private final By name =By.id("name");
        private final By country =By.id("country");
        private final By city =By.id("city");
        private final By card =By.id("card");
        private final By month =By.id("month");
        private final By year =By.id("year");
        private final By purchase= By.xpath("//button[.='Purchase']");
        private final By deletebutton= By.xpath("tr[1]//td[4]");
        private final By thankyouMess= By.cssSelector("div[class*='sweet-alert']");
        private final By okbutton= By.xpath("//button[.='OK']");
        private final By IdOfSuccessfulPurchase = By.xpath("//p/text()[contains(., 'Id')]");
        private final By amountOfSuccessfulPurchase = By.xpath( "//p/text()[contains(., 'Amount:')]");
        private final By cardnumOfSuccessfulPurchase = By.xpath("//p/text()[contains(., 'Card Number')]");
        private final By nameOnsuccesfulPurchase = By.xpath("//p/text()[contains(., 'Name')]");
        private final By currentDate= By.xpath("//p/text()[contains(.,'Date')]");
        private final By total= By.cssSelector("h3[id='totalp']");
        private final By price= By.xpath("//td[3]");
        private final By cartItems= By.cssSelector("tr[class='success']");
        private final By placeOrder= By.xpath("//button[text()='Place Order']");
        private final By totalLabel= By.id("totalm");
        private final By pic(String img){

            return By.xpath("img[src*='"+img+"']");
        }

        private final By title(String name){
            return By.xpath("//td[text()='"+name+"']");

        }
        private final By price(String name){
            return By.xpath("//td[text()='"+name+"']//following::td[1]");
        }
    //td[text()="+name+"]//following::td[1]
        private final By deletebutton(String name){

            return By.xpath("//td[text()='"+name+"']//following::td[2]");
        }

    @Step("Navigate to cart page Page")
    public navBar cartButton() {
            String url=driver.browser().getCurrentUrl();
            driver.validation().assertEquals(url,"https://www.demoblaze.com/cart.html","wrong page");
            return new navBar(driver);
    }
    @Step("Navigate to cart page Page")
    public cart navigateToCart()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+cart);
        return this;
    }

    @Step("Verify Product Details On Cart")
        public cart verifyProductDetailsOnCart (String name,String price)
        {

            String actualTitle = driver.element().getText(title(name));
            String actualPrice = driver.element().getText(price(name));

          //  driver.validation().isElementVisible(pic(img));
            driver.validation().assertEquals(actualTitle,name," title  doesn't not match");
            driver.validation().assertEquals("$"+actualPrice,price," price  doesn't not match");

            return this;
        }

    @Step("Verify Product Details On Cart")
    public cart ValidateTotalAmount ()
    {
        Integer sum=0;
       // List<String> list= new ArrayList<>(); redundant selenium creates list and stores elements in it
        List<String> list=driver.element().getElementsText(price);
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
        int size=0;
        try {
            List<String> list2 = driver.element().getElementsText(cartItems);
            size=list2.size();
        } catch (Exception e) {
            size=0;
        }
        driver.validation().assertTrue(list.size()>=size,"item deleted");
        return this;
    }

    @Step("validate purchase without credentials")
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
        driver.element().click(okbutton);
        String amount = driver.element().getText(amountOfSuccessfulPurchase);
        driver.validation().isElementVisible(thankyouMess);
        driver.validation().assertFalse(Objects.equals(amount, "0"),"should not allow purchase cart empty");
       /* try{

        catch(AssertionError | Exception e)
        {
            LogsManager.info("unexpected behaviour");
            driver.element().click(okbutton);
            throw e;
        }
*/
        return new navBar(driver);


    }




    @Step("placing order")
    public navBar PlaceOrder (String Name, String Country, String City, String Credit, String Month, String Year){
        //String Total=driver.element().getText(total);
        driver.element().click(placeOrder);
        String Total=driver.element().findElement(totalLabel).getText().replaceAll("[^0-9]", "");
            driver.element().type(name,Name).
                    type(country,Country).type(city,City).type(card,Credit).
                    type(month,Month).type(year,Year).click(purchase);
       String amount = driver.element().getText(amountOfSuccessfulPurchase).replaceAll("[^0-9]", "");
       String names = driver.element().getText(nameOnsuccesfulPurchase).split(": ")[1].replaceAll("\\s+", "");
        String cardnum = driver.element().getText(cardnumOfSuccessfulPurchase).split(": ")[1].replaceAll("\\s+", "");
        driver.validation().isElementVisible(thankyouMess);
        driver.validation().isElementVisible(IdOfSuccessfulPurchase);
        driver.element().click(okbutton);
        //driver.validation().assertEquals(Total,String.valueOf(total),"label shows wrong total price");
        driver.validation().assertEquals(names,Name,"wrong name");
        wait.fluentWait();
        driver.validation().assertEquals(cardnum,Credit,"wrong credit card number");
        wait.fluentWait();
        driver.validation().assertEquals(amount,Total,"wrong amount");

           return new navBar(driver);
    }


    }


