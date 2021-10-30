import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepImplementation extends BaseTest{

    private static final Logger
            LOGGER = LogManager.getLogger(BaseTest.class);
    protected static FluentWait<AppiumDriver> appiumFluentWait;

    private MobileElement findElement(By by) {

        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 20);
        MobileElement mobileElement = (MobileElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        return mobileElement;
    }

    public MobileElement findElementByXpathKey(String key) {

        MobileElement mobileElement =findElement(By.xpath(key));

        return mobileElement;
    }
    public MobileElement findElementIdByKey(String key) {

        MobileElement mobileElement =findElement(By.id(key));

        return mobileElement;
    }

    @Step("if element exist <key>")
    public boolean doesElementExistByKey(String key) {

        try {
            findElementByXpathKey(key);
            return true;
        } catch (Exception e) {
            LOGGER.info(key + " aranan elementi bulamadı");
            return false;
        }
    }

    @Step({"Wait <second> seconds"})
    public void waitBySecond(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Step("Click element xpath by <key>")
    public void clickXpathByKey(String key) {

        findElementByXpathKey(key).click();
        LOGGER.info(key + "elemente tıkladı");
    }
    @Step("Click element id by <key>")
    public void clickIdByKey(String key) {

        findElementByXpathKey(key).click();
        LOGGER.info(key + "elemente tıkladı");
    }

    @Step("Find element by <key> and send keys <text>")
    public void sendKeysIdByKeyNotClear(String key,String text) {
        findElementIdByKey(key).setValue(text);

    }

    @Step("scroll the screen")
    public void swipeMethod() {
        if (appiumDriver instanceof IOSDriver) {
            Dimension size = appiumDriver.manage().window().getSize();
            int x = size.getWidth() - 1;
            int starty = (int) (size.getHeight() * 0.20);
            int endy = (int) (size.getHeight() * 0.80);

            new TouchAction(appiumDriver).longPress(PointOption.point(x, starty))
                    .moveTo(PointOption.point(x, endy))
                    .release().perform();
        } else {
            new TouchAction(appiumDriver).longPress(PointOption.point(2, 800))
                    .moveTo(PointOption.point(2, 568))
                    .release().perform();
        }

        System.out.println("swipe yapıldı");

        }


    @Step("random <key> product click")
    public void randomProduct(String key) {
    findElementByXpathKey(key).click();

    }

    }

