package ch.qarts.specalizr.intg.web.locator;

import ch.qarts.specalizr.intg.web.common.LocatorTestBase;
import ch.qarts.specalizr.intg.web.common.Page;
import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.having;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.IdQueryComponent.id;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByIdLocatorTest extends LocatorTestBase {


    @Test
    public void shouldFindElementById() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola'>hola</div>").build();
        final var element = item(having(id("hola")));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }

    @Test
    public void shouldFindElementById2() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola'>hola</div>").build();
        final var element = item(having(id(with(text("hola")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("id"));
    }


    @Test
    public void shouldFindElementByIdContainingText() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input id='hola que tal'>hola</div>").build();
        final var element = item(having(id(containing(text("que tal")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola que tal", webElement.getAttribute("id"));
    }

}
