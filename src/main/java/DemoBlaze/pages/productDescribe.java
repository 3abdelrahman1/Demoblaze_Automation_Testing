package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import DemoBlaze.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.net.URL;

public class productDescribe {
    private GUIDriver driver;

        public productDescribe(GUIDriver driver) {
            this.driver = driver;
        }

        //vars

        private final By addToCartButton= By.xpath("//a[.='Add to cart']");
        private final By price= By.className("price-container");
        private final By name= By.className("name");
        private final By description = By.cssSelector("div[class='tab-content'] p");
        private final By img = By.cssSelector("div[class='item active'] img");

        @Step("verify current url")
        public productDescribe verifyProductDetails(String Url){
            String currentURL=driver.browser().getCurrentUrl();
            driver.validation().assertEquals(currentURL, Url,"WRONG product describtion");
            return this;
        }

        @Step("verify product description")
        public productDescribe verifyProductDetails(String prodname,String prodprice,String prodText){
            String actualProductName = driver.element().getText(name);
            String actualProductPrice = driver.element().getText(price).replaceAll("[^0-9.]", "");
            String actualProductTxt= driver.element().getText(description);
            LogsManager.info("actual product name:", actualProductName, "actual price:", actualProductPrice,"actual price:",actualProductTxt);
            driver.validation().assertEquals(actualProductName, prodname, "Product Name Verification Failed");
            driver.validation().assertEquals(actualProductPrice, prodprice, "Product Price Verification Failed");
            driver.validation().assertEquals(actualProductTxt, prodText, "Product text Verification Failed");
            driver.validation().isElementVisible(img);
            return this;
        }

        @Step("add to cart")
        public productDescribe addToCart(){
            driver.element().click(addToCartButton);
            String alertText=driver.alert().getAlertText();
            driver.alert().acceptAlert();
            driver.verification().assertEquals(alertText,"Product added","add product button not responsive");
            return this;

        }
    @Step("double click on add to cart")
    public productDescribe addToCartDoubleClick(){
    driver.element().DoubleClick(addToCartButton);
    driver.alert().acceptMultipleAlerts();
    return this;
    }

    }


