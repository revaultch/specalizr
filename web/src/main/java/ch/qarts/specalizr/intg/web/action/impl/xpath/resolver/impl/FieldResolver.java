package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Field;
import org.openqa.selenium.By;

public class FieldResolver extends ElementResolver<Field> {

    protected FieldResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(Field element) {
        return merge("(//input[not(@type)] | //input[@type='text'] | //input[@type='password'] | //input[@type='email'] | //input[@type='search']  | //textarea)", element.getElementQueryComponentList());
    }

}
