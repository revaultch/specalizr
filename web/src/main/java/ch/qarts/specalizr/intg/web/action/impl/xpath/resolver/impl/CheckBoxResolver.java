package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.CheckBox;
import org.openqa.selenium.By;

public class CheckBoxResolver extends ElementResolver<CheckBox> {

    CheckBoxResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(CheckBox element) {
        return merge("//input[@type='checkbox']", element.getElementQueryComponentList());
    }

}
