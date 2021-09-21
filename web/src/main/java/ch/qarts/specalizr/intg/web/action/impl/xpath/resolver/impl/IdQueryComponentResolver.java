package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ContainsQueryComponent;
import ch.qarts.specalizr.api.query.IdQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class IdQueryComponentResolver extends ElementQueryComponentResolver<IdQueryComponent> {

    IdQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(IdQueryComponent idQueryComponent) {
        if (idQueryComponent.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[@id='%s']", this.elementXPath, ((TextQueryComponent) idQueryComponent.getElementQueryComponent()).getText()));
        } else if (idQueryComponent.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[contains(@id, '%s')]", this.elementXPath, ((ContainsQueryComponent) idQueryComponent.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", idQueryComponent.getElementQueryComponent().getClass()));
        }
    }

}
