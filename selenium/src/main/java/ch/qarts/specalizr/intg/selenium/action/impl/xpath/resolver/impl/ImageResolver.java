package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Image;
import org.openqa.selenium.By;

public class ImageResolver extends ElementResolver<Image> {

    ImageResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Image element) {
        return merge("(//button | //input[@type='submit'] | //*[@role='button'])", element.getElementQueryComponentList());
    }

}
