package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

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
            return By.xpath(format("%s[@placeholder='%s']", this.elementXPath, ((TextQueryComponent)placeholderQueryComponent.getElementQueryComponent()).getText()));
        } else if (placeholderQueryComponent.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[contains(@placeholder, '%s')]",this.elementXPath, ((ContainsQueryComponent) placeholderQueryComponent.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", placeholderQueryComponent.getElementQueryComponent().getClass()));
        }
    }

}
