package ch.qarts.specalizr.web;

import ch.qarts.specalizr.api.element.Element;
import ch.qarts.specalizr.web.xpath.resolver.impl.ElementQueryComponentResolver;
import ch.qarts.specalizr.web.xpath.resolver.impl.ElementResolver;
import ch.qarts.specalizr.web.xpath.resolver.impl.ResolverContext;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementResolverFacade {

    private final WebDriver webDriver;

    private ElementResolverFacade(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T extends Element> By resolve(T element) {
        var resolverContext = ResolverContext.builder()
                .element(element)
                .webDriver(webDriver)
                .elementResolverFacade(this)
                .build();
        var elementResolverRegistry = ElementResolverRegistry.getInstance();
        var constructor = elementResolverRegistry.elementResolverFor(element).getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        var resolver = (ElementResolver<T>) constructor.newInstance(resolverContext);
        return resolver.resolve(element);
    }

    public void registerElementResolver(Class<? extends ElementResolver<?>> elementResolverClass) {
        ElementResolverRegistry.getInstance().registerElementResolver(elementResolverClass);
    }

    public void registerElementQueryComponentResolver(Class<? extends ElementQueryComponentResolver<?>> elementQueryComponentResolverClass) {
        ElementResolverRegistry.getInstance().registerElementQueryComponentResolver(elementQueryComponentResolverClass);
    }


    public static ElementResolverFacade createDefault(WebDriver webDriver) {
        return new ElementResolverFacade(webDriver);
    }


}
