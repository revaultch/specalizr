package ch.qarts.specalizr.web.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Item;
import org.openqa.selenium.By;

public class ItemResolver extends ElementResolver<Item> {

    protected ItemResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Item element) {
        return merge("//*", element.getElementQueryComponentList());
    }

}
