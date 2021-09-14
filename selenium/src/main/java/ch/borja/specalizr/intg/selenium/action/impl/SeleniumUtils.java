package ch.borja.specalizr.intg.selenium.action.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Collectors;

import static java.lang.String.format;

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
        WebElement webElement = SeleniumUtils.singlify((SearchContext) webDriver, by);
        final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webElement = wait.until(ExpectedConditions.visibilityOf(webElement));
        actions.moveToElement(webElement).perform();
        return webElement;
    }

    public static WebElement singlify(final SearchContext searchContext, final By by) {
        final var webElementList = searchContext.findElements(by).stream().filter(webElement -> webElement.isDisplayed() && webElement.isEnabled()).collect(Collectors.toList());
        if (webElementList.size() != 1) {
            throw new IllegalStateException(format("Unable to locate single element. Found %d items that match : %s", webElementList.size(), by.toString()));
        }
        return webElementList.get(0);
    }


}
