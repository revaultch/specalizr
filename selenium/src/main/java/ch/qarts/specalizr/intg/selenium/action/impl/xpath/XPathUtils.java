package ch.qarts.specalizr.intg.selenium.action.impl.xpath;

import ch.qarts.specalizr.api.element.*;

public class XPathUtils {

    public static String toXPath(final Element element) {
        if (element instanceof Button) {
            return "(//button | //input[@type='submit'] | //*[@role='button'])";
        } else if (element instanceof Link) {
            return "//a";
        } else if (element instanceof Field) {
            return "(//input[not(@type)] | //input[@type='text'] | //textarea)";
        } else if (element instanceof Panel) {
            return "(//div|//span|//a)";
        } else if (element instanceof Select) {
            return "//select";
        } else if (element instanceof CheckBox) {
            return "//input[@type='checkbox']";
        } else if (element instanceof RadioButton) {
            return "//input[@type='radio']";
        } else {
            return "//*";
        }
    }

}
