package ch.qarts.specalizr.examples;

import ch.qarts.specalizr.api.query.LabelQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import ch.qarts.specalizr.intg.selenium.action.impl.player.SeleniumPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementQueryComponentResolver;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementResolverFacade;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ResolverContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

import static ch.qarts.specalizr.api.action.ActionChain.first;
import static ch.qarts.specalizr.api.action.ActionChain.play;
import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.LabelQueryComponent.label;
import static java.lang.String.format;

/**
 * This is an example of what can be accomplished with this framework.<p>
 * It will not be necessarily maintained to match website changes. Expect it to fail in the long run <p>
 * It is also by no means an invitation to infringe crawling policies<p>
 * Please, use wisely <p>
 */
public class FormSpreeCustomExample {

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
        this.webDriver.navigate().to(new URL("https://formspree.io/register"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }

    /**
     * An example of how to use custom resolvers (here alternative label resolution for formspree)
     */
    @Test
    public void shouldHandleCustomLocators() {
        final var resolverFacade = ElementResolverFacade.createDefault(webDriver);
        resolverFacade.registerElementQueryComponentResolver(CustomLabelResolver.class);
        final var seleniumPlayer = new SeleniumPlayer(this.webDriver, resolverFacade);
        final var actions = first(write("Tom").into(field(with(label("First Name")))))
                .then(write("Hanks").into(field(with(label("Last Name")))))
                .then(write("tom@hanks.com").into(field(with(label("Email")))))
                .then(write("123456").into(field(with(label("Password")))));

        play(actions, with(seleniumPlayer));
    }
}

class CustomLabelResolver extends ElementQueryComponentResolver<LabelQueryComponent> {

    protected CustomLabelResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(LabelQueryComponent elementToBeResolved) {
        if (elementToBeResolved.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[ancestor::label[contains(text(),'%s')]]", this.elementXPath, ((TextQueryComponent) elementToBeResolved.getElementQueryComponent()).getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", elementToBeResolved.getElementQueryComponent().getClass()));
        }
    }
}

