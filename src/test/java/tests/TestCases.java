package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCases {

    public AndroidDriver driver;
    public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    @Test
    public void verifyUserCanDislikeThePerson() {
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String closeButtonXPath = "//android.widget.Image[@text=\"close\"]";
        String dislikeButtonXPath = "//android.widget.Button[@text=\"Dislike\"]";

        driver.findElementByXPath(takeATourButtonXPath).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showMeTextViewXPath)));

        List<AndroidElement> buttons = (List<AndroidElement>) driver.findElements(By.className(buttonClassName));
        AndroidElement closeButtonElement = buttons.get(1);
        closeButtonElement.click();

        driver.findElementByXPath(closeButtonXPath).click();

        driver.findElementByXPath(dislikeButtonXPath).click();
        // TODO: Put assert that disliked person shoulf no longer appear!(But we have not unique element found for the person name)
    }

    @Test
    public void verifyUserCanSeeTheAppVersion() {
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String settingsOptionXPath = "//android.widget.Button[@text=\"Settings\"]";
        String aboutButtonXPath = "//android.view.View[@text=\"About\"]";
        String versionTextViewXPath = "//android.view.View[contains(@text, 'Version')]";

        driver.findElementByXPath(takeATourButtonXPath).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showMeTextViewXPath)));

        List<AndroidElement> buttons = (List<AndroidElement>) driver.findElements(By.className(buttonClassName));
        AndroidElement closeButtonElement = buttons.get(1);
        closeButtonElement.click();

        AndroidElement menuElement = (AndroidElement) driver.findElements(By.className(buttonClassName)).get(0);
        menuElement.click();

        driver.findElementByXPath(settingsOptionXPath).click();

        driver.findElementByXPath(aboutButtonXPath).click();

        AndroidElement versionTextView = (AndroidElement) driver.findElementByXPath(versionTextViewXPath);

        Assert.assertTrue(versionTextView.isDisplayed(), "Application version did not display!");

        System.out.println("Application version is: " + versionTextView.getText());
    }

    @Test
    public void verifyUserCanSeeTheLikedPersons() {
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String likeButtonXPath = "//android.widget.Image[@text=\"iconAgreeThumb\"]";
        String likeButtonXPathOnConfirmationDialog = "//android.widget.Button[@text='Like']";
        String iconContactsXPath = "//android.widget.Image[@text=\"iconContacts\"]";
        String closeButtonXPath = "//android.widget.Button[@text='Close']";
        String likesIconXPath = "//android.widget.Image[@text=\"thumbs up\"]";
        String iLikeButtonXPath = "//android.widget.ToggleButton[@text=\"I LIKE\"]";

        driver.findElementByXPath(takeATourButtonXPath).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showMeTextViewXPath)));

        List<AndroidElement> buttons = (List<AndroidElement>) driver.findElements(By.className(buttonClassName));
        AndroidElement closeButtonElement = buttons.get(1);
        closeButtonElement.click();

        driver.findElementByXPath(likeButtonXPath).click();
        driver.findElementByXPath(likeButtonXPathOnConfirmationDialog).click();
        driver.findElementByXPath(iconContactsXPath).click();
        driver.findElementByXPath(closeButtonXPath).click();

        driver.findElementByXPath(likesIconXPath).click();
        driver.findElementByXPath(closeButtonXPath).click();

        driver.findElementByXPath(iLikeButtonXPath).click();
        driver.findElementByXPath(closeButtonXPath).click();
    }

    @BeforeTest
    public void setUpPage() throws MalformedURLException {
        String filePath = new File("src/test/resources/justbaby.apk").getAbsolutePath();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "c4e3f3cd");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, filePath);
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
