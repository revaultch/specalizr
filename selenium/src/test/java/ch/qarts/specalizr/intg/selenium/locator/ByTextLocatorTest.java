package ch.qarts.specalizr.intg.selenium.locator;

import ch.qarts.specalizr.intg.selenium.common.LocatorTestBase;
import ch.qarts.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByTextLocatorTest extends LocatorTestBase {

    @Test
    public void shouldFindElementByText() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><div id='hola'>hola</div>").build();
        final var element = item(with(text("hola")));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }


    @Test
    public void shouldFindElementContainingText() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><div id='hola'>hola</div>").build();
        final var element = item(containing(text("ho")));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }


}
