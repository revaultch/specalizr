package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Element;
import ch.qarts.specalizr.api.query.ElementQueryComponent;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

public class ElementResolverRegistry {

    public static ElementResolverRegistry INSTANCE = null;

    public static Set<Class<?>> RESOLVER_HOLDER = new HashSet<>();

    static {
        RESOLVER_HOLDER.add(ButtonResolver.class);
        RESOLVER_HOLDER.add(CheckBoxResolver.class);
        RESOLVER_HOLDER.add(IdQueryComponentResolver.class);
        RESOLVER_HOLDER.add(NameQueryComponentResolver.class);
        RESOLVER_HOLDER.add(PlaceholderQueryComponentResolver.class);
        RESOLVER_HOLDER.add(ContainsQueryComponentResolver.class);
        RESOLVER_HOLDER.add(FieldResolver.class);
        RESOLVER_HOLDER.add(ImageResolver.class);
        RESOLVER_HOLDER.add(ItemResolver.class);
        RESOLVER_HOLDER.add(LabelQueryComponentResolver.class);
        RESOLVER_HOLDER.add(LinkResolver.class);
        RESOLVER_HOLDER.add(PanelResolver.class);
        RESOLVER_HOLDER.add(ProximityComponentResolver.class);
        RESOLVER_HOLDER.add(RadioButtonResolver.class);
        RESOLVER_HOLDER.add(SelectResolver.class);
        RESOLVER_HOLDER.add(TextQueryComponentResolver.class);
    }

    private ElementResolverRegistry() {}

    public synchronized static ElementResolverRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ElementResolverRegistry();
        }
        return INSTANCE;
    }

    public synchronized void registerElementResolver(Class<ElementResolver< ?>> clazz) {
        RESOLVER_HOLDER.add(clazz);
    }

    public synchronized void registerElementQueryComponentResolver(Class<ElementQueryComponentResolver< ?>> clazz) {
        RESOLVER_HOLDER.add(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> Class<ElementResolver<T>> elementResolverFor(T element) {
        return (Class<ElementResolver<T>>)RESOLVER_HOLDER.stream().filter(resolverClass -> ((ParameterizedType)resolverClass.getGenericSuperclass())
                .getActualTypeArguments()[0].equals(element.getClass())).findFirst().orElseThrow(() -> new ResolverNotFoundException(element));
    }

    @SuppressWarnings("unchecked")
    public <T extends ElementQueryComponent> Class<ElementQueryComponentResolver<T>> queryComponentResolverFor(T element) {
        return (Class<ElementQueryComponentResolver<T>>)RESOLVER_HOLDER.stream().filter(resolverClass -> ((ParameterizedType)resolverClass.getGenericSuperclass())
                .getActualTypeArguments()[0].equals(element.getClass())).findFirst().orElseThrow(() -> new ResolverNotFoundException(element));
    }




}
