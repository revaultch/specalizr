package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ContainsQueryComponent;
import ch.qarts.specalizr.api.query.PlaceholderQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class PlaceholderQueryComponentResolver extends ElementQueryComponentResolver<PlaceholderQueryComponent> {

    PlaceholderQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(PlaceholderQueryComponent placeholderQueryComponent) {
        if (placeholderQueryComponent.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%1$s[@placeholder='%2$s'] | %1$s[@aria-label='%2$s']", this.elementXPath, ((TextQueryComponent) placeholderQueryComponent.getElementQueryComponent()).getText()));
        } else if (placeholderQueryComponent.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%1$s[contains(@placeholder, '%2$s')] | %1$s[contains(@aria-label, '%2$s')]", this.elementXPath, ((ContainsQueryComponent) placeholderQueryComponent.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", placeholderQueryComponent.getElementQueryComponent().getClass()));
        }
    }

}
