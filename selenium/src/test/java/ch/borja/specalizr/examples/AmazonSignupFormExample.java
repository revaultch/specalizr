package ch.borja.specalizr.examples;

import ch.borja.specalizr.intg.selenium.action.impl.SeleniumValidationConditionResolver;
import ch.borja.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.borja.specalizr.intg.selenium.action.registry.SeleniumPlayer;
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

import static ch.borja.specalizr.api.action.ActionChain.first;
import static ch.borja.specalizr.api.action.ActionChain.play;
import static ch.borja.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.borja.specalizr.api.action.definition.ValidationActionDefinition.validate;
import static ch.borja.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.borja.specalizr.api.action.definition.validation.ExistsValidationCondition.exists;
import static ch.borja.specalizr.api.element.Button.button;
import static ch.borja.specalizr.api.element.Element.an;
import static ch.borja.specalizr.api.element.Field.field;
import static ch.borja.specalizr.api.element.Item.item;
import static ch.borja.specalizr.api.query.AttributeQueryComponent.id;
import static ch.borja.specalizr.api.query.AttributeQueryComponent.with;
import static ch.borja.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.borja.specalizr.api.query.ElementQueryComponent.that;
import static ch.borja.specalizr.api.query.LabelQueryComponent.label;
import static ch.borja.specalizr.api.query.ProximityQueryComponent.above;
import static ch.borja.specalizr.api.query.TextQueryComponent.text;

public class AmazonSignupFormExample {

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
        this.webDriver.navigate().to(new URL("https://www.amazon.com"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }

    @Test
    public void shouldTestSomething() {
        final var seleniumPlayer = new SeleniumPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver(this.webDriver), new SeleniumValidationConditionResolver());
        final var actions =
                first(click(item(with(text("Sell")))))
                        .then(click(item(with(text("Sign up")), above(item(containing(text("More than half the units")))))))
                        .then(click(item(containing(text("Create your Amazon account")))))
                        .then(write("John").into(field(with(label(containing(text("Your name")))))))
                        .then(click(button(with(id("continue")))))
                        .andLastly(validate(that(an(item(with(text("There was a problem"))))), exists()));

        play(actions, with(seleniumPlayer));
    }
}
