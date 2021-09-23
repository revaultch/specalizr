package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Element;
import ch.qarts.specalizr.api.query.ElementQueryComponent;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ElementResolver<E extends Element> implements Resolver<By, E> {

    protected ResolverContext resolverContext;

    protected ElementResolver(ResolverContext resolverContext) {
        this.resolverContext = resolverContext;
    }

    protected By merge(@NonNull String root, @NonNull List<ElementQueryComponent> elementQueryComponentList) {
        if (elementQueryComponentList.size() == 0) {
            // by xpath with root
            return By.xpath(root);
        } else {
            // by match all
            return new ByMatchAll(elementQueryComponentList.stream().map(elementQueryComponent -> mapOneElementQueryComponent(root, elementQueryComponent)).collect(Collectors.toList()));
        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private <T extends ElementQueryComponent> By mapOneElementQueryComponent(String root, T elementQueryComponent) {
        var elementResolverRegistry = ElementResolverRegistry.getInstance();
        var constructor = elementResolverRegistry.elementQueryComponentFor(elementQueryComponent).getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return ((ElementQueryComponentResolver<T>) constructor.newInstance(resolverContext, root)).resolve(elementQueryComponent);
    }

}
