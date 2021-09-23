package ch.qarts.specalizr.examples;

import ch.qarts.specalizr.api.element.Clickable;
import ch.qarts.specalizr.api.element.ElementBase;
import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import ch.qarts.specalizr.intg.web.action.impl.player.WebPlayer;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.*;
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
import static ch.qarts.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.qarts.specalizr.api.action.definition.Key.ENTER;
import static ch.qarts.specalizr.api.action.definition.PressActionDefinition.press;
import static ch.qarts.specalizr.api.action.definition.ValidationActionDefinition.validate;
import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.action.definition.validation.ExistsValidationCondition.exists;
import static ch.qarts.specalizr.api.element.Button.button;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.that;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.PlaceholderQueryComponent.placeholder;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static ch.qarts.specalizr.examples.CustomButton.customButton;
import static java.lang.String.format;

/**
 * This is an example of what can be accomplished with this framework.<p>
 * Depending on your current context, this may or may not work as is. <p>
 * It will not be necessarily maintained to match website changes. Expect it to fail in the long run <p>
 * It is also by no means an invitation to infringe crawling policies<p>
 * Please, use wisely <p>
 */
public class StackOverflowCustomComponentExample {

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
        this.webDriver.navigate().to(new URL("https://stackoverflow.com/jobs"));
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }



    @Test
    public void shouldClickCustomButton() {

        final var elementResolverFacade = ElementResolverFacade.createDefault(webDriver);
        elementResolverFacade.registerElementResolver(CustomButtonResolver.class);
        elementResolverFacade.registerElementQueryComponentResolver(CustomTextResolver.class);
        final var techno = "sometechno";
        final var webPlayer = new WebPlayer(this.webDriver, elementResolverFacade);
        final var actions = first(click(button(with(text("Accept all cookies")))))
                .then(click(customButton(with(text("Tech")))))
                .then(write(techno).into(field(with(placeholder(containing("e.g. html"))))))
                .then(press(ENTER))
                .then(write("test engineer").into(field(with(placeholder("Search all jobs")))))
                .then(press(ENTER))
                .then(validate(that(techno), exists()));

        play(actions, with(webPlayer));
    }

}

class CustomButton extends ElementBase implements Clickable {

    CustomButton(ElementQueryComponent[] elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    static CustomButton customButton(final ElementQueryComponent... elementQueryComponentList) {
        return new CustomButton(elementQueryComponentList);
    }
}

class CustomButtonResolver extends ElementResolver<CustomButton> {

    CustomButtonResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(CustomButton element) {
        return merge("//button[contains(@class, 's-btn__dropdown')]", element.getElementQueryComponentList());
    }

}

class CustomTextResolver extends TextQueryComponentResolver {

    CustomTextResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(TextQueryComponent textQueryComponent) {
        if (resolverContext.getElement() instanceof CustomButton) {
            return By.xpath(format("%s[./div[normalize-space(text()) = '%s']]", elementXPath, textQueryComponent.getText()));
        } else {
            return super.resolve(textQueryComponent);
        }
    }
}


