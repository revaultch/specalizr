package ch.qarts.specalizr.examples;

import ch.qarts.specalizr.api.action.ActionDefinition;
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
import static ch.qarts.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.qarts.specalizr.api.action.definition.ValidationActionDefinition.validate;
import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.action.definition.validation.DoesNotExistValidationCondition.doesNotExist;
import static ch.qarts.specalizr.api.action.definition.validation.ExistsValidationCondition.exists;
import static ch.qarts.specalizr.api.element.Button.button;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.that;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.PlaceholderQueryComponent.placeholder;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;

/**
 * This is an example of what can be accomplished with this framework.<p>
 * Depending on your current context, this may or may not work as is. <p>
 * It will not be necessarily maintained to match website changes. Expect it to fail in the long run <p>
 * It is also by no means an invitation to infringe crawling policies<p>
 * Please, use wisely <p>
 */
public class QartsChExample {

    private WebDriver webDriver;

    final ActionDefinition acceptGdpr = click(button(with(text("Close"))));


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
        this.webDriver.navigate().to(new URL("http://localhost:8080"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }



    @Test
    public void shouldValidateMissingEmail() {
        final var webPlayer = new WebPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));

        final var actions = first(acceptGdpr)
                .then(click("Contact"))
                .then(write("John Doe").into(field(with(placeholder("Your name")))))
                .then(write("Whatever").into(field(with(placeholder("Tell us about your project")))))
                .then(click("Send"))
                .andLastly(validate(that("Please provide a valid email"), exists()));

        play(actions, with(webPlayer));
    }

    @Test
    public void shouldValidateInvalidEmail() {
        final var webPlayer = new WebPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
        final var actions = first(acceptGdpr)
                .then(click("Contact"))
                .then(write("John Doe").into(field(with(placeholder("Your name")))))
                .then(write("John.Doe at dot com").into(field(with(placeholder("Your email")))))
                .then(write("Whatever").into(field(with(placeholder("Tell us about your project")))))
                .then(click("Send"))
                .andLastly(validate(that("Please provide a valid email"), exists()));

        play(actions, with(webPlayer));
    }


    @Test
    public void shouldValidateMissingName() {
        final var webPlayer = new WebPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
        final var actions = first(acceptGdpr)
                .then(click("Contact"))
                .then(write("").into(field(with(placeholder("Your name")))))
                .then(write("John@doe.com").into(field(with(placeholder("Your email")))))
                .then(write("Whatever").into(field(with(placeholder("Tell us about your project")))))
                .then(click("Send"))
                .andLastly(validate(that("Please provide a name"), exists()));

        play(actions, with(webPlayer));
    }

    @Test
    public void shouldOpenJobsPopup() {
        final var webPlayer = new WebPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
        final var actions = first(acceptGdpr)
                .then(click("Jobs"))
                .then(validate(that("Current job offers"), exists()))
                .then(click("Close"))
                .andLastly(validate(that("Current job offers"), doesNotExist()));

        play(actions, with(webPlayer));
    }

}