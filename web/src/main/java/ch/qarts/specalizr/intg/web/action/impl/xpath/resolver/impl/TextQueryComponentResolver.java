package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static java.lang.String.format;

public class TextQueryComponentResolver extends ElementQueryComponentResolver<TextQueryComponent> {

    protected TextQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(TextQueryComponent elementToBeResolved) {
        return By.xpath(format(attributeTextMatchers("[normalize-space(text()) = '%1$s']", "[@value = '%1$s']", "[@id=//label[normalize-space(text())='%1$s']/@for]", "[option[@selected][normalize-space(text())='%1$s']]").stream().map((item) -> format("%s%s", this.elementXPath, item)).collect(Collectors.joining(" | ")), elementToBeResolved.getText()));
    }

}
