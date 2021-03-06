package ch.qarts.specalizr.web.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Button;
import org.openqa.selenium.By;

public class ButtonResolver extends ElementResolver<Button> {

    protected ButtonResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Button element) {
        return merge("(//button | //input[@type='submit'] | //*[@role='button'])", element.getElementQueryComponentList());
    }

}
