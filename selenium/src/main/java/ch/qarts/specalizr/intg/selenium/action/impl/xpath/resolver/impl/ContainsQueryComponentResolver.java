package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ContainsQueryComponent;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static java.lang.String.format;

public class ContainsQueryComponentResolver extends ElementQueryComponentResolver<ContainsQueryComponent> {

    ContainsQueryComponentResolver(ResolverContext resolverContext, String elementXPath) {
        super(resolverContext, elementXPath);
    }

    @Override
    public By resolve(ContainsQueryComponent elementToBeResolved) {
        return By.xpath(format(attributeTextMatchers("[contains(text(), '%1$s')]", "[contains(@value, '%1$s')]", "[@id=//label[contains(text(),'%1$s')]/@for]", "[option[@selected][contains(text(),'%1$s')]]").stream().map((item) -> format("%s%s",  this.elementXPath, item)).collect(Collectors.joining(" | ")), elementToBeResolved.getTextLocation().getText()));
    }

}
