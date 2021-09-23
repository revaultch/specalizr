package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Image;
import org.openqa.selenium.By;

public class ImageResolver extends ElementResolver<Image> {

    protected ImageResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Image element) {
        return merge("//img", element.getElementQueryComponentList());
    }

}
