package ch.qarts.specalizr.examples;

import ch.qarts.specalizr.intg.web.action.impl.player.WebPlayer;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolverFacade;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

import static ch.qarts.specalizr.api.action.ActionChain.first;
import static ch.qarts.specalizr.api.action.ActionChain.play;
import static ch.qarts.specalizr.api.action.definition.ClearActionDefinition.clear;
import static ch.qarts.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.qarts.specalizr.api.action.definition.Key.RETURN;
import static ch.qarts.specalizr.api.action.definition.PressActionDefinition.press;
import static ch.qarts.specalizr.api.action.definition.ValidationActionDefinition.validate;
import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.action.definition.validation.ExistsValidationCondition.exists;
import static ch.qarts.specalizr.api.element.Button.button;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.that;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;

/**
 * This is an example of what can be accomplished with this framework.<p>
 * Depending on your current context, this may or may not work as is. <p>
 * It will not be necessarily maintained to match website changes. Expect it to fail in the long run <p>
 * It is also by no means an invitation to infringe crawling policies<p>
 * Please, use wisely <p>
 */
public class LinkedInJobSearchExample {

    private WebDriver webDriver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    @SneakyThrows
    public void setup() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        this.webDriver = new ChromeDriver(chromeOptions);
        this.webDriver.navigate().to(new URL("https://linkedin.com"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }



    @Test
    public void shouldFindAJob() {
        final var webPlayer = new WebPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));

        final var searchBox = field(with(text("Engineering")));

        var actions = first(click(button(with(text("Accept cookies")))))
                .then(click(item(with(text("Engineering")))))
                .then(clear(searchBox))
                .then(write("test engineer").into(searchBox))
                .then(press(RETURN))
                .andLastly(validate(that(item(containing(text("Test Engineer Jobs in")))), exists()));

        play(actions, with(webPlayer));
    }
}