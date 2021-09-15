package ch.qarts.specalizr.intg.selenium.action.impl;

import ch.qarts.specalizr.api.element.*;
import ch.qarts.specalizr.api.query.*;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class SeleniumXPathQueryComponentResolver implements ElementVisitor<By> {

    @Override
    public By visit(final AndQueryComponent andVisibleElementQuery) {
        throw new IllegalStateException("not implemented");
    }


    @Override
    public By visit(final ProximityQueryComponent proximityQueryComponent) {
        return new ByProximity(proximityQueryComponent);
    }

    @Override
    public By visit(final Select selectableVisibleElement) {
        return new ByMatchAll(By.xpath("//select"), this.parseElementQueryComponents(selectableVisibleElement.getElementQueryComponentList()));
    }

    @Override
    public By visit(final CheckBox checkBox) {
        return new ByMatchAll(By.xpath("//input[@type='checkbox']"), this.parseElementQueryComponents(checkBox.getElementQueryComponentList()));
    }

    @Override
    public By visit(final RadioButton radioButton) {
        return new ByMatchAll(By.xpath("//input[@type='radio']"), this.parseElementQueryComponents(radioButton.getElementQueryComponentList()));
    }

    @Override
    public By visit(final TextQueryComponent textVisibleElementQuery) {
        return By.xpath(format("(//*[text() = '%1$s'] | //*[@value = '%1$s'] | //select[option[@selected][text()='%1$s']] | //input[@id=//label[text()='%1$s']/@for])", textVisibleElementQuery.getText()));
    }

    @Override
    public By visit(final LabelQueryComponent labelVisibleElementQuery) {
        if (labelVisibleElementQuery.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("//*[@id=//label[text()='%1$s']/@for]", ((TextQueryComponent) labelVisibleElementQuery.getElementQueryComponent()).getText()));
        } else if (labelVisibleElementQuery.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("//*[@id=//label[contains(text(),'%1$s')]/@for]", ((ContainsQueryComponent) labelVisibleElementQuery.getElementQueryComponent()).getTextLocation().getText()));
        } else {
            throw new IllegalStateException(format("Unexpected type %s", labelVisibleElementQuery.getElementQueryComponent().getClass()));
        }
    }

    @Override
    public By visit(final BackgroundColorQueryComponent backgroundColorVisibleElementQuery) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public By visit(final SelectedTextQueryComponent selectedTextVisibleElementQuery) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public By visit(final ContainsQueryComponent containsVisibleElementQuery) {
        return By.xpath(format("(//*[contains(text(), '%1$s')] | //*[contains(@value, '%1$s')] | //select[option[@selected][contains(text(),'%1$s')]] | //input[@id=//label[contains(text(),'%1$s')]/@for])", containsVisibleElementQuery.getTextLocation().getText()));
    }


    @Override
    public By visit(final Button button) {
        return new ByMatchAll(By.xpath("(//button | //input[@type='submit'] | //*[@role='button'])"), this.parseElementQueryComponents(button.getElementQueryComponentList()));
    }


    private List<By> parseElementQueryComponents(final List<ElementQueryComponent> elementQueryComponentList) {
        return Arrays.asList(elementQueryComponentList.stream().map(elementQueryComponent -> elementQueryComponent.accept(this)).toArray(By[]::new));
    }


    @Override
    public By visit(final Panel panel) {
        return new ByMatchAll(By.xpath("(//div|//span|//a)"), this.parseElementQueryComponents(panel.getElementQueryComponentList()));
    }

    @Override
    public By visit(final Link link) {
        return new ByMatchAll(By.xpath("//a"), this.parseElementQueryComponents(link.getElementQueryComponentList()));
    }

    @Override
    public By visit(final AttributeQueryComponent attributeQuery) {
        if (attributeQuery.getElementQueryComponent() instanceof ContainsQueryComponent) {
            return By.xpath(format("//*[contains(@%s,'%s')]", attributeQuery.getAttributeName(), ((ContainsQueryComponent) attributeQuery.getElementQueryComponent()).getTextLocation().getText()));
        } else if (attributeQuery.getElementQueryComponent() instanceof TextQueryComponent) {
            return By.xpath(format("//*[@%s='%s']", attributeQuery.getAttributeName(), ((TextQueryComponent) attributeQuery.getElementQueryComponent()).getText()));
        } else {
            throw new IllegalStateException("Not implemented");
        }
    }

    @Override
    public By visit(final Image image) {
        return new ByMatchAll(By.xpath("//img"), this.parseElementQueryComponents(image.getElementQueryComponentList()));
    }

    @Override
    public By visit(final Item item) {
        return new ByMatchAll(By.xpath("//*"), this.parseElementQueryComponents(item.getElementQueryComponentList()));
    }


    @Override
    public By visit(final Writable writableElement) {
        return new ByMatchAll(By.xpath("(//input[not(@type)] | //input[@type='text'] | //textarea )"), this.parseElementQueryComponents(writableElement.getElementQueryComponentList()));
    }


}