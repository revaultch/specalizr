package ch.qarts.specalizr.intg.selenium.locator;

import ch.qarts.specalizr.intg.selenium.common.LocatorTestBase;
import ch.qarts.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.having;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.PlaceholderQueryComponent.placeholder;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByPlaceholderLocatorTest extends LocatorTestBase {


    @Test
    public void shouldFindElementByPlaceholder() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola' placeholder='some placeholder'>hola</div>").build();
        final var element = item(having(placeholder("some placeholder")));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }


    @Test
    public void shouldFindElementByPlaceholder2() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola' placeholder='some placeholder'>hola</div>").build();
        final var element = item(having(placeholder(with(text("some placeholder")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }


    @Test
    public void shouldFindElementByPlaceholderContainingText() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola' placeholder='some placeholder'>hola</div>").build();
        final var element = item(having(placeholder(containing(text("some")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }

}
