package ch.qarts.specalizr.intg.selenium.action.impl.xpath;

import ch.qarts.specalizr.api.element.Element;
import org.openqa.selenium.By;

public interface ByResolver {
    By resolve(Element element);
}
