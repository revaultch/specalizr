package ch.borja.specalizr.intg.selenium.action.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        SeleniumUtils.log.info("safeLocate of " + by);
        SeleniumUtils.waitUntilComplete(webDriver);
        final Actions actions = new Actions(webDriver);
        final var webElementList = webDriver.findElements(by).stream().filter(webElement -> webElement.isDisplayed() && webElement.isEnabled()).collect(Collectors.toList());
        if (webElementList.size() != 1) {
            throw new IllegalStateException(format("Unable to locate single element. Found %d items that match : %s", webElementList.size(), by.toString()));
        }
        final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        final WebElement webElement = wait.until(ExpectedConditions.visibilityOf(webElementList.get(0)));
        actions.moveToElement(webElement).perform();
        return webElement;
    }


}
