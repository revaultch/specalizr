package ch.qarts.specalizr.web.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.RadioButton;
import org.openqa.selenium.By;

public class RadioButtonResolver extends ElementResolver<RadioButton> {

    protected RadioButtonResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(RadioButton element) {
        return merge("//input[@type='radio']", element.getElementQueryComponentList());
    }

}
