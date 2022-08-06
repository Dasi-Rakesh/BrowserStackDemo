package extensions;

import managers.FileReaderManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.locators.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class Wait extends BasePage {

    public Wait(WebDriver driver) {
        super(driver);
    }

    private static final int DEFAULT_TIMEOUT = 5;

    public static void untilJqueryIsDone(WebDriver driver) {
        untilJqueryIsDone(driver, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
    }

    public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
        until(driver, (d) -> {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone)
                System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    public static void untilPageLoadComplete(Long timeoutInSeconds) {
        until(driver, (d) -> {
            Boolean isPageLoaded = ((JavascriptExecutor) driver).executeScript("return document.readyState")
                    .equals("complete");
            if (!isPageLoaded)
                System.out.println("Document is loading");
            return isPageLoaded;
        }, timeoutInSeconds);
    }

    public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition) {
        until(driver, waitCondition, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
    }

    public static void pageRefresh() throws InterruptedException {
        Wait.mediumSleep();
        driver.navigate().refresh();
    }

    public static boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static List<WebElement> getElements(By elementLocator) {
        try {
            List<WebElement> elements = driver.findElements(elementLocator);
            return elements;
        } catch (WebDriverException exception) {
            throw new WebDriverException(
                    "Element with locator : " + elementLocator + " was not displayed and unable to get the count",
                    exception);
        }
    }

    public static void waitForElementClickableAndClick(WebElement element)
            throws Exception {
        for (int i = 0; i <= 5; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            } catch (WebDriverException exception) {
                throw new WebDriverException("Element with locator : " + element + " was not displayed", exception);
            }
        }
    }

    public static void waitForElementClickableAndClick(By element) throws Exception {
        for (int i = 0; i <= 5; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
                driver.findElement(element).click();
                break;
            } catch (WebDriverException exception) {
                throw new WebDriverException("Element with locator : " + element + " was not displayed", exception);
            }
        }
    }

    public static void waitForElementVisibleAndClick(By element) throws StaleElementReferenceException, NoSuchElementException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
          //  js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
            //        driver.findElement(element));
            driver.findElement(element).click();
        } catch (StaleElementReferenceException exception) {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(element)));
            driver.findElement(element).click();
        } catch (TimeoutException exception) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + element + " was not displayed", exception);
        }

    }




    public static void waitForElementToBeStale(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(element))));
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + element + " was not displayed", exception);
        }

    }

    public static void waitForElementPresentAndClick(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
             //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                  //  driver.findElement(elementLocator));
            driver.findElement(elementLocator).click();
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + elementLocator + " was not displayed", exception);
        }

    }

    public static String GetElementText(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            return driver.findElement(elementLocator).getText();
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + elementLocator + " was not displayed", exception);
        }
    }

    public static void implicitWait() {
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void staticWait() {
        try {
            Thread.sleep(GlobalVairables.staticSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void shortSleep() {
        try {
            Thread.sleep(GlobalVairables.shortSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mediumSleep() {
        try {
            Thread.sleep(GlobalVairables.mediumSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void longSleep() {
        try {
            Thread.sleep(GlobalVairables.longSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void veryLongSleep() {
        try {
            Thread.sleep(GlobalVairables.VeryLongSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void waitForElementLocatorAndSetValue(By elementLocator, String value) throws NoSuchElementException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
          //  js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
          //          driver.findElement(elementLocator));
            driver.findElement(elementLocator).clear();
            driver.findElement(elementLocator).sendKeys(value);

        } catch (StaleElementReferenceException exception) {
            throw new StaleElementReferenceException("Element with locator : " + elementLocator + " was not displayed",
                    exception);
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + elementLocator + " was not displayed", exception);
        }

    }
    public static Boolean sendKeysWithDelay(By elementLocator, By elementDisplayed, String value)
            throws NoSuchElementException {
        Boolean isReslut = false;
        try {

            driver.findElement(elementLocator).clear();
            for (int i = 0; i < value.length(); i++) {
                driver.findElement(elementLocator).sendKeys(value.substring(i, i + 1));
                Wait.mediumSleep();
                isReslut = IsElementDisplayed(elementDisplayed);
            }
            return isReslut;
        } catch (StaleElementReferenceException exception) {
            throw new StaleElementReferenceException("Element with locator : " + elementLocator + " was not displayed",
                    exception);
        } catch (WebDriverException exception) {
            throw new WebDriverException("Element with locator : " + elementLocator + " was not displayed", exception);
        }

    }

    public static boolean IsElementDisplayed(By elementLocator) {
        boolean result;
        try {
            result = driver.findElement(elementLocator).isDisplayed();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
