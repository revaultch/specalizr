package ch.qarts.specalizr.intg.selenium.locator;

import ch.qarts.specalizr.intg.selenium.common.LocatorTestBase;
import ch.qarts.specalizr.intg.selenium.common.Page;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.element.Item.item;
import static ch.qarts.specalizr.api.query.ContainsQueryComponent.containing;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.ProximityQueryComponent.*;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByRelativeLocatorTest extends LocatorTestBase {

    private static final String TEMPLATE = "html/proximityTest.html";

    @Test
    @SneakyThrows
    public void shouldFindElementStrictlyLeftOf() {
        final var page = Page.builder().template(ByRelativeLocatorTest.TEMPLATE).build();
        final var element = item(containing(text("div")), leftOf(item(with(text("2 div")))), above(item(with(text("13 div")))));
        this.webDriver.navigate().to(page.generate().toURL());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("1", webElement.getAttribute("id"));
    }

    @Test
    @SneakyThrows
    public void shouldFindElementStrictlyRightOf() {
        final var page = Page.builder().template(ByRelativeLocatorTest.TEMPLATE).build();
        final var element = item(containing(text("div")), rightOf(item(with(text("2 div")))), above(item(with(text("12 div")))));
        this.webDriver.navigate().to(page.generate().toURL());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("3", webElement.getAttribute("id"));
    }

    @SneakyThrows
    @Test
    public void shouldFindElementStrictlyAbove() {
        final var page = Page.builder().template(ByRelativeLocatorTest.TEMPLATE).build();
        final var element = item(containing(text("div")), above(item(with(text("11 div")))), leftOf((item(with(text("2 div"))))));
        this.webDriver.navigate().to(page.generate().toURL());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("1", webElement.getAttribute("id"));
    }

    @SneakyThrows
    @Test
    public void shouldFindElementStrictlyBelow() {
        final var page = Page.builder().template(ByRelativeLocatorTest.TEMPLATE).build();
        final var element = item(containing(text("div")), below(item(with(text("1 div")))), leftOf(item(with(text("12 div")))));
        this.webDriver.navigate().to(page.generate().toURL());
        final var webElement = this.find(this.elementResolverFacade.resolve(element));
        assertEquals("11", webElement.getAttribute("id"));
    }

}
