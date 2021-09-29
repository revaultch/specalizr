package ch.qarts.specalizr.web.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.*;
import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.web.xpath.resolver.Resolver;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ElementQueryComponentResolver<Q extends ElementQueryComponent> implements Resolver<By, Q> {

    protected final ResolverContext resolverContext;

    protected final String elementXPath;

    protected ElementQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        this.resolverContext = resolverContext;
        this.elementXPath = elementXPath;
    }

    protected List<String> attributeTextMatchers(final String textMatcher, final String valueMatcher, final String labelMatcher, final String selectMatcher) {
        final Map<Class<?>, List<String>> matchers = new HashMap<>();
        matchers.put(Button.class, Arrays.asList(textMatcher, valueMatcher));
        matchers.put(CheckBox.class, Arrays.asList(labelMatcher, textMatcher, valueMatcher));
        matchers.put(Field.class, Arrays.asList(textMatcher, valueMatcher, labelMatcher));
        matchers.put(Link.class, List.of(textMatcher));
        matchers.put(Panel.class, List.of(textMatcher));
        matchers.put(RadioButton.class, Arrays.asList(valueMatcher, labelMatcher));
        matchers.put(Select.class, Arrays.asList(labelMatcher, selectMatcher));
        if (matchers.containsKey(this.resolverContext.getElement().getClass())) {
            return matchers.get(this.resolverContext.getElement().getClass());
        } else {
            return List.of(valueMatcher, textMatcher);
        }

    }


}
