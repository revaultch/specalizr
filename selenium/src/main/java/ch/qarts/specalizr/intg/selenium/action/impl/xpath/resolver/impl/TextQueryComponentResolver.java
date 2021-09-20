package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.TextQueryComponent;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static java.lang.String.format;

public class TextQueryComponentResolver extends ElementQueryComponentResolver<TextQueryComponent> {

    TextQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(TextQueryComponent elementToBeResolved) {
        return By.xpath(format(attributeTextMatchers("[text() = '%1$s']", "[@value = '%1$s']", "[@id=//label[text()='%1$s']/@for]", "[option[@selected][text()='%1$s']]").stream().map((item) -> format("%s%s",  this.elementXPath, item)).collect(Collectors.joining(" | ")), elementToBeResolved.getText()));
    }

}
