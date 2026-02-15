package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import DemoBlaze.utils.logs.LogsManager;

public class category {
   
    
        private final GUIDriver driver;
        //private final String categ = "#";
        public category(GUIDriver driver) {

            this.driver = driver;
        }
        private final By Phones = By.xpath("//a[.='Phones']");
        private final By Laptops = By.xpath("//a[.='Laptops']");
        private final By Monitors = By.xpath("//a[.='Monitors']");
        private final By previousButton = By.cssSelector("button[id='prev2']");
        private final By nextButton = By.cssSelector("button[id='next2']");
        private final By img = By.cssSelector("div[id='tbodyid'] img");
        private final By homeCards = By.className("card h-100");

//dynamic locator
        private By category(String productname) {
           return By.xpath("//a[.="+productname+"]");
        }
        private By cardTitle(String productname) {

            return  By.xpath("//a[text()="+productname+"]");
        }

        private By cardText(String productname) {

            return By.xpath( "//a[text()="+productname+"]//following::p']");
        }

        private By price(String productname) {
            return By.xpath( "//a[text()="+productname+"]//following::h5']");
        }
    @Step("navigate to phones section")
    public category GoToPhones()
    {
        driver.element().click(Phones);
        return this;
    }
    @Step("navigate to laptops section")
    public category GoToLaptops()
    {
        driver.element().click(Laptops);
        return this;
    }
    @Step("navigate to monitors section")
    public category GoToMonitors()
    {
        driver.element().click(Monitors);
        return this;
    }

    @Step("testing next and prev buttons")
    public category nextButton()
    {
        List<String> list1= new ArrayList<>();
        List<String> list2= new ArrayList<>();
        driver.element().click(Phones);
        list1=driver.element().getElementsText(homeCards);
        driver.element().click(nextButton);
        list2=driver.element().getElementsText(homeCards);
        driver.validation().assertFalse(list1.equals(list2),"next button does not respond");
        return this;
    }
    @Step("testing next and prev buttons")
    public category PrevButton()
    {
        List<String> list1= new ArrayList<>();
        List<String> list2= new ArrayList<>();
        driver.element().click(Phones);
        list1=driver.element().getElementsText(homeCards);
        driver.element().click(previousButton);
        list2=driver.element().getElementsText(homeCards);
        driver.validation().assertFalse(list1.equals(list2),"next button does not respond");
        return this;
    }

        @Step("test homeCards after clicking on next and prev buttons")
        public category VerifyHomecards()
        {
            List<String> list1= new ArrayList<>();
            List<String> list2= new ArrayList<>();
            list1=driver.element().getElementsText(homeCards);
            driver.element().click(nextButton).click(previousButton);
            list2=driver.element().getElementsText(homeCards);
            driver.validation().assertTrue(list1.equals(list2),"list changed unexpectedly");
            return this;
        }

        @Step("verify product details from category")
        public category categoryChoice(String categ,String productname, String Price,String pText){
            driver.element().click(category(categ));
            String actualProductName = driver.element().getText(cardTitle(productname));
            String actualProductPrice = driver.element().getText(price(productname));
            String actualProductTxt= driver.element().getText(cardText(productname));
            LogsManager.info("actual product name:", actualProductName, "actual price:", actualProductPrice,"actual price:",actualProductTxt);

            driver.validation().assertEquals(actualProductPrice, "$"+Price, "Product Price Verification Failed");
            driver.validation().assertEquals(actualProductTxt, pText, "Product text Verification Failed");
            driver.validation().isElementVisible(cardTitle(productname));
            driver.validation().isElementVisible(cardText(productname));
            driver.validation().isElementVisible(price(productname));
            return this;
        }

        @Step("verify correct address")
        public productDescribe productAddress( String pname){
            driver.element().click(category(pname));
            String address=driver.element().findElement(cardTitle(pname)).getDomProperty("href");
            driver.element().click(cardTitle(pname));
            String currentUrl=driver.browser().getCurrentUrl();
            driver.validation().assertEquals(PropertyReader.getProperty("baseUrlWeb")+address,currentUrl,"wrong address");

            return new productDescribe(driver);

        }



    }

