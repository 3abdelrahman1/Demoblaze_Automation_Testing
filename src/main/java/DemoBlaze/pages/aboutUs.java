package DemoBlaze.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import DemoBlaze.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import DemoBlaze.utils.dataReader.PropertyReader;
public class aboutUs {

    private GUIDriver driver;

    public aboutUs(GUIDriver driver) {
        this.driver = driver;
    }
    private final By aboutUsModal= By.cssSelector("div[class='modal fade show']");
    private final By Playvid = By.xpath("//span[.='Play']");
    private final By quit= By.xpath("//h5[.='Log in']//following::button[@class='close'][2]");
    private final By pauseButton= By.cssSelector("button[title='Pause']");
    private final By playButton= By.cssSelector("button[title='Play']");
    private final By vidProgSlider= By.cssSelector("div[class*='progress-control']");
    private final By mutebutton= By.cssSelector("button[class*='mute-control']");
    private final By isMuted= By.cssSelector("button[ title='Unmute']");
    private final By progValue= By.cssSelector("div[class*='progress-control'] div[aria-valuetext]");
    private final By inactive= By.cssSelector("#example-video[class*='user-inactive'");
    private final By active= By.cssSelector("#example-video[class*='user-active']");
    private final By EnterPipMode= By.cssSelector("button[title='Picture-in-Picture']");
    private final By ExitPipMode= By.cssSelector("button[title='Exit Picture-in-Picture']");
    private final By Video= By.id("example-video");
    private final By pipLoadingSpinner =By.className("vjs-loading-spinner");






    @Step("validate play video button works")
    public aboutUs playvid() {
        driver.element().findElement(active);
        driver.element().click(Playvid).findElement(inactive);
        return this;
    }

    @Step("validate mute button works")
    public aboutUs clickonMuteButton() {
        driver.element().click(mutebutton).findElement(isMuted);
        return this;
    }
    @Step("validate player slider works")
    public aboutUs SliderControl() {
        String beforeClick=driver.element().findElement(progValue).getDomProperty("aria-valuetext");
        driver.element().click(vidProgSlider);
        String afterClick=driver.element().findElement(progValue).getDomProperty("aria-valuetext");
        driver.validation().assertEquals(beforeClick,afterClick,"Slider doesn't work");

        return this;
    }
    @Step("assert clicking on button opens pip mode")
    public aboutUs pipMode() {
        driver.element().click(EnterPipMode).findElement(ExitPipMode);

        return this;
    }
    @Step("Enter pip and assert video opened in it")
    public aboutUs videoPipMode() {
        driver.element().Enter_PIP_JS(Video);
        Boolean pipvideo= driver.element().assertPIP_Element(Video);
        driver.validation().assertTrue(pipvideo,"pip opened but not video");
        return this;
    }
    @Step("test functionality")
    public aboutUs PipFunctionality() {
        driver.element().click(pipLoadingSpinner).Exit_PIP_JS(Video);
        return this;
    }
    //assert False
    @Step("validating close button")
    public navBar closeAboutUsPage() {
       boolean modalDisplay = driver.element().click(quit).findElement(aboutUsModal).isDisplayed();
        driver.validation().assertFalse(modalDisplay,"close button not working");
        return new navBar(driver);
    }
}
