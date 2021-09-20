package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.element.Element;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
@Builder
public class ResolverContext {
    private WebDriver webDriver;
    private Element element;
    private ElementResolverFacade elementResolverFacade;
}
