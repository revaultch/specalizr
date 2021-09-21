package ch.qarts.specalizr.intg.web.common;

import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolverFacade;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class LocatorTestBase {

    protected WebDriver webDriver;

    protected ElementResolverFacade elementResolverFacade;

    protected WebElement find(final By by) {
        return this.webDriver.findElement(by);
    }

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        this.webDriver = new ChromeDriver(chromeOptions);
        this.elementResolverFacade = ElementResolverFacade.createDefault(webDriver);
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
