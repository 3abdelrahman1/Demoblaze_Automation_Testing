package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
public class cart {

    
        private final String cart = "/cart.html" ;
        private final GUIDriver driver;
        public cart(GUIDriver driver) {
            this.driver=driver;
        }

        private final By message= By.cssSelector("textarea[id='message-text']");
        private final By sendMessage= By.xpath("//button[.='Place Order']");
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
        private final By ID1= By.xpath("//p[contains(text(),'Id')]");
        private final By amount1= By.xpath( "//p[contains(text(),'Amount')]");
        private final By cardnum1= By.xpath("//p[contains(text(),'Card Number')]");
        private final By names1= By.xpath("//p[contains(text(),'Name')]");
        private final By Date1= By.xpath("//p[contains(text(),'Date')]");
        private final By total= By.cssSelector("h3[id='totalp']");
        private final By price= By.cssSelector("//td[3]");
        private final By cartItems= By.cssSelector("tr[class='success']");
        private final By placeOrder= By.xpath("//button[text()='Place Order']");
        private final By totalLabel= By.xpath("//label[contains(text(),'Total')]");
        private final By pic(String name){
            return By.xpath("//td[text()="+name+"]//parent::tr[1]");
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

    @Step("Empty cart purchase")
    public cart emptyCartPurchase(String Name,String Country,String City,String Credit,String Month,String Year){
        driver.element().click(placeOrder);
        String TotalLabel=driver.element().findElement(totalLabel).getText().replaceAll("[^0-9.]", "");
        driver.element().type(name,Name).
                type(country,Country).type(city,City).type(card,Credit).
                type(month,Month).type(year,Year).click(purchase);
        String amount = driver.element().getText(amount1);
        driver.validation().isElementVisible(thankyouMess);
        driver.validation().assertEquals(TotalLabel,String.valueOf(total),"label shows wrong total price");
        driver.validation().assertEquals(amount,"0","should not allow purchase cart empty");
        return this;


        }




        @Step("Verify Product Details On Cart")
        public cart verifyProductDetailsOnCart (String name,String pic,String title,String price)
        {
            String actualPic = driver.element().getText(pic(name));
            String actualTitle = driver.element().getText(title(name));
            String actualPrice = driver.element().getText(price(name));

            driver.validation().assertEquals(actualPic,pic," Pic doesn't not match");
            driver.validation().assertEquals(actualTitle,title," title  doesn't not match");
            driver.validation().assertEquals(actualPrice,price," price  doesn't not match");

            return this;
        }
        @Step("Verify Product Details On Cart")
        public cart verifyProductDetailsOnCarts ()
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
        public cart verifyProductDetailsOnCart (){
            List<String> list=driver.element().getElementsText(cartItems);
            driver.element().click(deletebutton);
            List<String> list2=driver.element().getElementsText(cartItems);

            driver.validation().assertTrue(list.size()>list2.size(),"item deleted");
            return this;
        }

    @Step("placing order")
    public cart PlaceOrder (String Name,String Country,String City,String Credit,String Month,String Year){
            driver.element().click(placeOrder);
            String TotalLabel=driver.element().findElement(totalLabel).getText().replaceAll("[^0-9.]", "");
            driver.element().type(name,Name).
                    type(country,Country).type(city,City).type(card,Credit).
                    type(month,Month).type(year,Year).click(purchase);
            driver.validation().isElementVisible(thankyouMess);
            driver.validation().assertEquals(TotalLabel,String.valueOf(total),"label shows wrong total price");
            return this;
    }

    @Step("complete purchase")
    public navBar purchase (String iD,String Amount,String CardNumber,String Name,String date) {
            String ID=driver.element().getText(ID1);
        String amount = driver.element().getText(amount1);
        String names = driver.element().getText(names1);
        String Date = driver.element().getText(Date1);
        String cardnum = driver.element().getText(cardnum1);
        driver.element().click(okbutton);
            driver.validation().assertEquals(ID,iD,"");
        driver.validation().assertEquals(amount,Amount,"");
        driver.validation().assertEquals(names,Name,"");
        driver.validation().assertEquals(cardnum,CardNumber,"");
        driver.validation().assertEquals(Date,date,"");
        return new navBar(driver);

    }
    }


