package ch.qarts.specalizr.examples;

import ch.qarts.specalizr.intg.selenium.action.impl.player.SeleniumPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.DefaultByResolver;
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
import static ch.qarts.specalizr.api.action.definition.Key.ENTER;
import static ch.qarts.specalizr.api.action.definition.PressActionDefinition.press;
import static ch.qarts.specalizr.api.action.definition.SelectActionDefinition.select;
import static ch.qarts.specalizr.api.action.definition.ValidationActionDefinition.validate;
import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.action.definition.validation.ContainsTextValidationCondition.containsText;
import static ch.qarts.specalizr.api.element.Button.button;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.element.Select.selector;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.ProximityQueryComponent.*;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;

public class GoogleUnitConverter {

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
        this.webDriver.navigate().to(new URL("https://google.com"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }

    @Test
    public void shouldConvertMilesToKm() {
        final var seleniumPlayer = new SeleniumPlayer(this.webDriver, new DefaultByResolver());
        final var leftField = field(leftOf(item(with(text("=")))), below(selector(with(text("Length")))));
        final var rightField = field(rightOf(leftField), below(selector(with(text("Length")))));
        final var actions = first(click(item(with(text("I agree")))))
                .then(write("unit converter").into(field(above(button(with(text("Google Search")))))))
                .then(press(ENTER))
                .then(select("Mile").from(selector(with(text("Meter")))))
                .then(select("kilometre").from(selector(with(text("Centimeter")))))
                .then(clear(leftField))
                .then(write("50").into(leftField))
                .andLastly(validate(that(rightField), containsText("80.4672")));

        play(actions, with(seleniumPlayer));
    }
}
