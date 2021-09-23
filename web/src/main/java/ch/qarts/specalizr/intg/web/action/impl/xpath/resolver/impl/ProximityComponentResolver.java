package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ProximityQueryComponent;
import ch.qarts.specalizr.intg.web.action.impl.SeleniumUtils;
import org.openqa.selenium.By;

public class ProximityComponentResolver extends ElementQueryComponentResolver<ProximityQueryComponent> {


    protected ProximityComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(ProximityQueryComponent elementToBeResolved) {
        var hint = By.xpath(elementXPath);
        var target = SeleniumUtils.safelyLocate(resolverContext.getWebDriver(), this.resolverContext.getElementResolverFacade().resolve(elementToBeResolved.getElement()));
        return new ByProximity(elementToBeResolved.getProximity(), hint, target);
    }

}
