package ch.qarts.specalizr.intg.selenium.locator;

import ch.qarts.specalizr.intg.selenium.common.LocatorTestBase;
import ch.qarts.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.having;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.NameQueryComponent.name;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByNameLocatorTest extends LocatorTestBase {


    @Test
    public void shouldFindElementById() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input name='hola'>hola</div>").build();
        final var element = item(having(name("hola")));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("name"));
    }

    @Test
    public void shouldFindElementById2() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input name='hola'>hola</div>").build();
        final var element = item(having(name(with(text("hola")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola", webElement.getAttribute("name"));
    }


    @Test
    public void shouldFindElementByIdContainingText() {
        final var page = Page.builder().bodyContent("<div id='hello'>hello</div><input name='hola que tal'>hola</div>").build();
        final var element = item(having(name(containing(text("que tal")))));
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("hola que tal", webElement.getAttribute("name"));
    }

}
