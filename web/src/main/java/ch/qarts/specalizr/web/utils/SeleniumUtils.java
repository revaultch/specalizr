package ch.qarts.specalizr.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

@Slf4j
public class SeleniumUtils {


    public static void waitUntilComplete(final WebDriver webDriver) {
        final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) wdriver).executeScript(
                "return document.readyState"
        ).equals("complete"));
    }

    public static WebElement safelyLocate(final WebDriver webDriver, final By by) {
        SeleniumUtils.log.debug("safeLocate of " + by);
        SeleniumUtils.waitUntilComplete(webDriver);
        final Actions actions = new Actions(webDriver);
        WebElement webElement = SeleniumUtils.singlify(webDriver, by);
        final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webElement = wait.until(ExpectedConditions.visibilityOf(webElement));
        actions.moveToElement(webElement).perform();
        return webElement;
    }

    public static WebElement singlify(final SearchContext searchContext, final By by) {
        return
                new FluentWait<>(searchContext)
                        .withTimeout(ofSeconds(10))
                        .pollingEvery(ofMillis(100))
                        .ignoring(java.util.NoSuchElementException.class)
                        .ignoring(NoElementFound.class)
                        .until(searchContext1 -> {
                            var elements = searchContext1.findElements(by).stream().filter(webElement -> webElement.isDisplayed() && webElement.isEnabled()).collect(Collectors.toList());
                            if (elements.size() == 1) {
                                return elements.get(0);
                            } else {
                                throw new NoElementFound(format("Unable to locate single element. Found %d items that match : %s", elements.size(), by.toString()));
                            }
                        });
    }


}
