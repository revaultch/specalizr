package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Button;
import org.openqa.selenium.By;

public class ButtonResolver extends ElementResolver<Button> {

    ButtonResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Button element) {
        return merge("(//button | //input[@type='submit'] | //*[@role='button'])", element.getElementQueryComponentList());
    }

}
