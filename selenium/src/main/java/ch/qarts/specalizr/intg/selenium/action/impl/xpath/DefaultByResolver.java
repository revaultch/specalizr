package ch.qarts.specalizr.intg.selenium.action.impl.xpath;

import ch.qarts.specalizr.api.element.*;
import ch.qarts.specalizr.api.query.*;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ch.qarts.specalizr.intg.selenium.action.impl.xpath.XPathUtils.toXPath;
import static java.lang.String.format;


public class DefaultByResolver implements ByResolver {

    @Override
    public By resolve(final Element element) {
        final var visitor = new Visitor(element);
        final var byList = Arrays.asList(element.getElementQueryComponentList().stream().map(elementQueryComponent -> elementQueryComponent.accept(visitor)).toArray(By[]::new));
        return new ByMatchAll(element, byList);
    }

}

class Visitor implements ElementVisitor<By> {

    private final Element element;

    Visitor(final Element element) {
        this.element = element;
    }

    @Override
    public By visit(final ProximityQueryComponent proximityQueryComponent) {
        return new ByProximity(proximityQueryComponent);
    }

    private List<String> attributeTextMatchers(final String textMatcher, final String valueMatcher, final String labelMatcher, final String selectMatcher) {
        final Map<Class<?>, List<String>> matchers = new HashMap<>();
        matchers.put(Button.class, Arrays.asList(textMatcher, valueMatcher));
        matchers.put(CheckBox.class, Arrays.asList(labelMatcher, textMatcher, valueMatcher));
        matchers.put(Field.class, Arrays.asList(textMatcher, valueMatcher, labelMatcher));
        matchers.put(Link.class, List.of(textMatcher));
        matchers.put(Panel.class, List.of(textMatcher));
        matchers.put(RadioButton.class, Arrays.asList(valueMatcher, labelMatcher));
        matchers.put(Select.class, Arrays.asList(labelMatcher, selectMatcher));

        if (matchers.containsKey(this.element.getClass())) {
            return matchers.get(this.element.getClass());
        } else {
            return List.of(valueMatcher, textMatcher);
        }
    }

    private final Function<String, String> wrapWithElement = query -> format("%s%s", toXPath(Visitor.this.element), query);

    @Override
    public By visit(final TextQueryComponent textVisibleElementQuery) {
        return By.xpath(format(this.attributeTextMatchers("[text() = '%1$s']", "[@value = '%1$s']", "[@id=//label[text()='%1$s']/@for]", "[option[@selected][text()='%1$s']]").stream().map(this.wrapWithElement).collect(Collectors.joining(" | ")), textVisibleElementQuery.getText()));
    }

    @Override
    public By visit(final ContainsQueryComponent containsVisibleElementQuery) {
        return By.xpath(format(this.attributeTextMatchers("[contains(text(), '%1$s')]", "[contains(@value, '%1$s')]", "[@id=//label[contains(text(),'%1$s')]/@for]", "[option[@selected][contains(text(),'%1$s')]]").stream().map(this.wrapWithElement).collect(Collectors.joining(" | ")), containsVisibleElementQuery.getTextLocation().getText()));
    }

    @Override
    public By visit(final BackgroundColorQueryComponent backgroundColorVisibleElementQuery) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public By visit(final SelectedTextQueryComponent selectedTextVisibleElementQuery) {
        throw new IllegalStateException("Not implemented");
    }


    @Override
    public By visit(final AttributeQueryComponent attributeQuery) {
        if (attributeQuery.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[contains(@%s,'%s')]", toXPath(this.element), attributeQuery.getAttributeName(), ((ContainsQueryComponent) attributeQuery.getElementQueryComponent()).getTextLocation().getText()));
        } else if (attributeQuery.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[@%s='%s']", toXPath(this.element), attributeQuery.getAttributeName(), ((TextQueryComponent) attributeQuery.getElementQueryComponent()).getText()));
        } else {
            throw new IllegalStateException("Not implemented");
        }
    }

    @Override
    public By visit(final LabelQueryComponent labelVisibleElementQuery) {
        if (labelVisibleElementQuery.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[@id=//label[text()='%s']/@for]", toXPath(this.element), ((TextQueryComponent) labelVisibleElementQuery.getElementQueryComponent()).getText()));
        } else if (labelVisibleElementQuery.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[@id=//label[contains(text(),'%s')]/@for]", toXPath(this.element), ((ContainsQueryComponent) labelVisibleElementQuery.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", labelVisibleElementQuery.getElementQueryComponent().getClass()));
        }
    }

}