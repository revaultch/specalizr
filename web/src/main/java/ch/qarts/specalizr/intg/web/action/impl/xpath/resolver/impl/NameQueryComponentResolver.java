package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ContainsQueryComponent;
import ch.qarts.specalizr.api.query.NameQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class NameQueryComponentResolver extends ElementQueryComponentResolver<NameQueryComponent> {

    protected NameQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(NameQueryComponent nameQueryComponent) {
        if (nameQueryComponent.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[@name='%s']", this.elementXPath, ((TextQueryComponent) nameQueryComponent.getElementQueryComponent()).getText()));
        } else if (nameQueryComponent.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[contains(@name, '%s')]", this.elementXPath, ((ContainsQueryComponent) nameQueryComponent.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", nameQueryComponent.getElementQueryComponent().getClass()));
        }
    }

}
