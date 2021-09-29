package ch.qarts.specalizr.web.xpath.resolver.impl;

import static java.lang.String.format;

public class ResolverNotFoundException extends RuntimeException {
    private final Object object;

    public ResolverNotFoundException(Object object) {
        this.object = object;
    }

    @Override
    public String getMessage() {
        return format("No resolver found for %s", object.getClass());
    }

}
