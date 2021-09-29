package ch.qarts.specalizr.web;

import ch.qarts.specalizr.api.element.Element;
import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.web.xpath.resolver.impl.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ElementResolverRegistry {

    private static ElementResolverRegistry INSTANCE = null;

    private final List<Class<?>> resolverHolder = new ArrayList<>();

    private ElementResolverRegistry() {
    }

    public synchronized static ElementResolverRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ElementResolverRegistry();
            INSTANCE.initDefaults();
        }
        return INSTANCE;
    }

    private void initDefaults() {
        resolverHolder.add(ButtonResolver.class);
        resolverHolder.add(CheckBoxResolver.class);
        resolverHolder.add(IdQueryComponentResolver.class);
        resolverHolder.add(NameQueryComponentResolver.class);
        resolverHolder.add(PlaceholderQueryComponentResolver.class);
        resolverHolder.add(ContainsQueryComponentResolver.class);
        resolverHolder.add(FieldResolver.class);
        resolverHolder.add(ImageResolver.class);
        resolverHolder.add(ItemResolver.class);
        resolverHolder.add(LabelQueryComponentResolver.class);
        resolverHolder.add(LinkResolver.class);
        resolverHolder.add(PanelResolver.class);
        resolverHolder.add(ProximityComponentResolver.class);
        resolverHolder.add(RadioButtonResolver.class);
        resolverHolder.add(SelectResolver.class);
        resolverHolder.add(TextQueryComponentResolver.class);
    }

    // TODO : fix this ... (old items remain in list)
    public synchronized void registerElementResolver(Class<? extends ElementResolver<?>> clazz) {
        resolverHolder.add(0, clazz);
    }

    public synchronized void registerElementQueryComponentResolver(Class<? extends ElementQueryComponentResolver<?>> clazz) {
        resolverHolder.add(0, clazz);
    }


    private Type getResolverArgumentType(Class<?> resolver) {
        Class<?> found = resolver;
        while (found != null && !found.equals(Object.class)) {
            if (found.getGenericSuperclass() != null && found.getGenericSuperclass() instanceof ParameterizedType) {
                var generic = (ParameterizedType) found.getGenericSuperclass();
                if (generic.getRawType().equals(ElementResolver.class) || generic.getRawType().equals(ElementQueryComponentResolver.class)) {
                    return generic.getActualTypeArguments()[0];
                }
            }
            found = found.getSuperclass();
        }
        throw new IllegalStateException("No resolver argument type found for " + resolver);
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> Class<ElementResolver<T>> elementResolverFor(T element) {
        return (Class<ElementResolver<T>>) resolverHolder.stream().filter(resolverClass -> getResolverArgumentType(resolverClass).equals(element.getClass())).findFirst().orElseThrow(() -> new ResolverNotFoundException(element));
    }


    @SuppressWarnings("unchecked")
    public <T extends ElementQueryComponent> Class<ElementQueryComponentResolver<T>> elementQueryComponentFor(T elementQueryComponent) {
        return (Class<ElementQueryComponentResolver<T>>) resolverHolder.stream()
                .filter(resolverClass -> getResolverArgumentType(resolverClass).equals(elementQueryComponent.getClass())).findFirst().orElseThrow(() -> new ResolverNotFoundException(elementQueryComponent));
    }


}
