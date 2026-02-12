package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import DemoBlaze.drivers.GUIDriver;
import DemoBlaze.utils.dataReader.PropertyReader;
import DemoBlaze.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
public class navBar {
    
   
        private final GUIDriver driver;
        private String navEndpoint = "/index.html";
        public navBar(GUIDriver driver) {

            this.driver = driver;
        }
        private final By productStoreLabel =By.cssSelector("a[class='navbar-brand']");
        private final By homeButton = By.xpath("//a[href='index.html']");
        private final By contactButton = By.cssSelector("a[.='Contact']");
        private final By aboutUsButton = By.xpath("//a[.='About us']");
        private final By cartButton = By.xpath("//a[.='Cart']");
        private final By loginButton = By.xpath("//a[.='Log in']");
        private final By logoutButton = By.xpath("//a[.='Log out']");
        private final By signupButton = By.xpath("//a[.='Sign up']");
        private final By welcome = By.cssSelector("a[id='nameofuser']");
        private final By footer =By.xpath("//h4[@class='grrrr']//following::p");
        private final By slideIndicator1 = By.cssSelector("li[data-slide-to='0']");
        private final By slideIndicator2 = By.xpath("//li[@data-slide-to='1']");
        private final By activeSlide1=By.cssSelector("li[data-slide-to='0' and @class='active']]");
        private final By activeSlide2=By.cssSelector("li[data-slide-to='1' and @class='active']]");
        private final By prevIcon = By.className("carousel-control-prev-icon");
        private final By nextIcon = By.className("carousel-control-next");
        private final By imgDisplay1 =By.xpath("//img[@src='Samsung1.jpg']//parent::div[@class='carousel-item active']");
        private final By imgDisplay2 =By.xpath("//img[@src='nexus1.jpg']//parent::div[@class='carousel-item active']");
        private By footer(int order){
            return  By.xpath("//h4[@class='grrrr']//following::p["+order+"]");
        }
//label productstore

        @Step("Navigate to Home Page")
        public navBar navigate()
        {
            driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
            return this;
        }
        @Step("Click on Home Button")
        public navBar clickOnHomeButton ()
        {
            driver.element().click(homeButton);
            return this;
        }
        @Step("Click on Products Button")
        public contact contactButton()
        {
            driver.element().click(contactButton);
            return new contact(driver);
        }
        @Step("Click on Cart Button")
        public cart clickOnCartButton()
        {
            driver.element().click(cartButton);
            return new cart(driver);
        }
        @Step("Click on Logout Button")
        public navBar clickOnLogoutButton()
        {
            driver.element().click(logoutButton);
            return this;
        }
        @Step("Click on Signup/Login Button")
        public aboutUs clickOnaboutUsButton()
        {
            driver.element().click(aboutUsButton);
            return new aboutUs(driver);
        }
        @Step("Click on Signup/Login Button")
        public logIn clickOnLoginButton()
        {
            driver.element().click(loginButton);
            return new logIn(driver);
        }
        @Step("Click on Signup/Login Button")
        public signup clickOnSignupButton()
        {
            driver.element().click(signupButton);
            return new signup(driver);
        }



        @Step("Verify footer displayed")
        public  navBar verifyFooterDisplayed()
        {

            driver.validation().isElementVisible(footer(1));
            driver.validation().isElementVisible(footer(2));
            driver.validation().isElementVisible(footer(3));
            driver.validation().isElementVisible(footer(4));
            return this;
        }

        @Step("Verify User Label")
        public navBar verifyUserLabel (String expectedName)
        {
            String actualName = driver.element().getText(welcome);
            LogsManager.info("Verifying user label: " + actualName);
            driver.verification().assertEquals(actualName,expectedName, "User label does not match. Expected: " + expectedName + ", Actual: " + actualName);
            return this;

        }
        @Step("Verify success logout")
        public navBar logout (){

            driver.element().click(logoutButton);
            driver.validation().isElementVisible(loginButton);
            return this;
        }

        @Step("Verify success logout")
        public navBar nextIcon(){
            driver.element().click(slideIndicator1).click(nextIcon);
            driver.validation().isElementVisible(imgDisplay2);
            driver.validation().isElementVisible(activeSlide2);
            return this;

        }
        @Step("Verify success logout")
        public navBar previousIcon () {
            driver.element().click(prevIcon);
            driver.validation().isElementVisible(imgDisplay1);
            driver.validation().isElementVisible(activeSlide1);
            return this;
        }
    }


