package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestCases {

    public AndroidDriver driver;
    public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    public WebDriverWait wait;

    @Test
    public void verifyUserCanDislikeThePerson() {
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour ']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String closeButtonXPath = "//android.widget.Image[@text=\"close \"]";
        String dislikeButtonXPath = "//android.widget.Button[@text=\"Dislike\"]";
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(takeATourButtonXPath)));
        driver.findElementByXPath(takeATourButtonXPath).click();

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
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour ']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String settingsOptionXPath = "//android.widget.Button[@text=\"Settings \"]";
        String aboutButtonXPath = "//android.view.View[@text=\"About\"]";
        String versionTextViewXPath = "//android.view.View[contains(@text, 'Version')]";
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(takeATourButtonXPath)));
        driver.findElementByXPath(takeATourButtonXPath).click();

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
    public void verifyUserCanSeeTheLikedPersons() throws InterruptedException {
        String takeATourButtonXPath = "//android.widget.Button[@text='Take a tour ']";
        String showMeTextViewXPath = "//android.view.View[@text='Show me...']";
        String buttonClassName = "android.widget.Button";
        String likeButtonXPath = "//android.widget.Image[@text=\"iconAgreeThumb\"]";
        String likeButtonXPathOnConfirmationDialog = "//android.widget.Button[@text='Like ']";
        String iconContactsXPath = "//android.widget.Image[@text=\"iconContacts\"]";
        String closeButtonXPath = "//android.widget.Button[@text='Close ']";
        String likesIconXPath = "//android.widget.Image[@text=\"thumbs up\"]";
        String iLikeButtonXPath = "//android.widget.ToggleButton[@text=\"I LIKE\"]";

        System.out.println(driver.getPageSource());
        WebDriverWait wait = new WebDriverWait(driver, 40);

        Set contextHandles = driver.getContextHandles();
        System.out.println(contextHandles);

        System.out.println(driver.getPageSource());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(takeATourButtonXPath)));
        driver.findElementByXPath(takeATourButtonXPath).click();

        System.out.println(driver.getPageSource());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showMeTextViewXPath)));

        List<AndroidElement> buttons = (List<AndroidElement>) driver.findElements(By.className(buttonClassName));
        AndroidElement closeButtonElement = buttons.get(1);
        closeButtonElement.click();

        driver.findElementByXPath(likeButtonXPath).click();

        Thread.sleep(2000);
        System.out.println(driver.getPageSource());

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
//        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "c4e3f3cd");
//        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
//        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
//        desiredCapabilities.setCapability(MobileCapabilityType.APP, filePath);
//        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

//        // BROWSER STACK
//        String userName = "siye1";
//        String accessKey = "b6LstPhqLBqK15SddYDz";
//        String appId = "b73b686dfe62bbe2013fa64bfff379d1181c18af";
//
//        desiredCapabilities.setCapability("device", "Google Pixel");
//        desiredCapabilities.setCapability("os_version", "7.1");
//        desiredCapabilities.setCapability("app", "bs://"+appId);
//        desiredCapabilities.setCapability("browserstack.debug", true);
//
//
//        driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * This method will always execute after each test case, This will quit the WebDriver instance called at the last
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(final ITestResult result) throws IOException {
        String fileName = result.getTestClass().getName() + "_" + result.getName();
        System.out.println("Test Case: [" + fileName + "] executed..!");
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            takeScreenshot(result.getMethod().getMethodName().toLowerCase() + "_" + System.currentTimeMillis());
        }
    }

    /**
     * This method used to take the screenshots of failures screen(Only visible at AWS Device Farm Screenshot tab)
     * @param name
     * @return
     */
    public boolean takeScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

    // Maven command to generate the zip file for AWS Device Farm: mvn clean package -DskipTests=true
}
