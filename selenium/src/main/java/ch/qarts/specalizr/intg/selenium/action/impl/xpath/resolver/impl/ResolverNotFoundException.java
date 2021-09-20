package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import static java.lang.String.format;

public class ResolverNotFoundException extends RuntimeException {
    private final Object object;
    ResolverNotFoundException(Object object) {
        this.object = object;
    }

    @Override
    public String getMessage() {
        return format("No resolver found for %s", object.getClass());
    }

}
