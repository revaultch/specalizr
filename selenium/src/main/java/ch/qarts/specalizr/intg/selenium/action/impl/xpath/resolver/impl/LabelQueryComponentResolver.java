package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ContainsQueryComponent;
import ch.qarts.specalizr.api.query.LabelQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class LabelQueryComponentResolver extends ElementQueryComponentResolver<LabelQueryComponent> {

    LabelQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(LabelQueryComponent elementToBeResolved) {
        if (elementToBeResolved.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("%s[@id=//label[text()='%s']/@for]", this.elementXPath, ((TextQueryComponent)elementToBeResolved.getElementQueryComponent()).getText()));
        } else if (elementToBeResolved.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("%s[@id=//label[contains(text(),'%s')]/@for]",this.elementXPath, ((ContainsQueryComponent) elementToBeResolved.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", elementToBeResolved.getElementQueryComponent().getClass()));
        }
    }

}
