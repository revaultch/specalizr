package ch.qarts.specalizr.unit.registry;

import ch.qarts.specalizr.api.element.Clickable;
import ch.qarts.specalizr.api.element.ElementBase;
import ch.qarts.specalizr.api.query.ElementQueryComponent;
import ch.qarts.specalizr.api.query.TextQueryComponent;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolver;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolverRegistry;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ResolverContext;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.TextQueryComponentResolver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static ch.qarts.specalizr.unit.registry.CustomButton.customButton;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistryTest {


    @Test
    public void shouldRegisterCustomElementResolver() {
        ElementResolverRegistry.getInstance().registerElementResolver(CustomButtonResolver.class);
        assertEquals(CustomButtonResolver.class, ElementResolverRegistry.getInstance().elementResolverFor(customButton(with(text("kkk")))));
    }

    @Test
    public void shouldRegisterCustomElementQueryComponentResolver() {
        ElementResolverRegistry.getInstance().registerElementQueryComponentResolver(CustomTextResolver.class);
        assertEquals(CustomTextResolver.class, ElementResolverRegistry.getInstance().elementQueryComponentFor(text("kkk")));
    }

}

class CustomButton extends ElementBase implements Clickable {

    CustomButton(ElementQueryComponent[] elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    static CustomButton customButton(final ElementQueryComponent... elementQueryComponentList) {
        return new CustomButton(elementQueryComponentList);
    }
}

class CustomButtonResolver extends ElementResolver<CustomButton> {

    CustomButtonResolver(ResolverContext resolverContext) {
        super(resolverContext);
    }

    @Override
    public By resolve(CustomButton element) {
        return merge("//button[contains(@class, 's-btn__dropdown')]", element.getElementQueryComponentList());
    }

}

class CustomTextResolver extends TextQueryComponentResolver {

    CustomTextResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(TextQueryComponent textQueryComponent) {
        if (resolverContext.getElement() instanceof CustomButton) {
            return By.xpath(format("%s[./div[normalize-space(text()) = '%s']]", elementXPath, textQueryComponent.getText()));
        } else {
            return super.resolve(textQueryComponent);
        }
    }
}
