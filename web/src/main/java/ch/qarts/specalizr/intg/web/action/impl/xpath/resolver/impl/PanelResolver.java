package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Panel;
import org.openqa.selenium.By;

public class PanelResolver extends ElementResolver<Panel> {

    protected PanelResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Panel element) {
        return merge("(//div | //span | //a)", element.getElementQueryComponentList());
    }

}
