package ch.qarts.specalizr.web.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Link;
import org.openqa.selenium.By;

public class LinkResolver extends ElementResolver<Link> {

    protected LinkResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Link element) {
        return merge("//a", element.getElementQueryComponentList());
    }

}
