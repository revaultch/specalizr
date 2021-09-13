package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.element.*;
import ch.borja.specalizr.api.query.*;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ch.borja.specalizr.api.query.ProximityQueryComponent.Proximity.*;
import static java.lang.String.format;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

@AllArgsConstructor
public class SeleniumXPathQueryComponentResolver implements ElementVisitor<By> {


    private WebDriver webDriver;

    @Override
    public By visit(final AndQueryComponent andVisibleElementQuery) {
        throw new IllegalStateException("not implemented");
    }


    private WebElement resolveElement(final Element element) {
        return SeleniumUtils.safelyLocate(this.webDriver, element.accept(this));
    }

    @Override
    public By visit(final ProximityQueryComponent proximityQueryComponent) {
        if (proximityQueryComponent.getProximity().equals(ABOVE)) {
            return with(By.xpath("//*")).above(this.resolveElement(proximityQueryComponent.getElement()));
        } else if (proximityQueryComponent.getProximity().equals(BELOW)) {
            return with(By.xpath("//*")).below(this.resolveElement(proximityQueryComponent.getElement()));
        } else if (proximityQueryComponent.getProximity().equals(RIGHT_OF)) {
            return with(By.xpath("//*")).toRightOf(this.resolveElement(proximityQueryComponent.getElement()));
        } else if (proximityQueryComponent.getProximity().equals(LEFT_OF)) {
            return with(By.xpath("//*")).toLeftOf(this.resolveElement(proximityQueryComponent.getElement()));
        } else if (proximityQueryComponent.getProximity().equals(NEAR)) {
            return with(By.xpath("//*")).near(this.resolveElement(proximityQueryComponent.getElement()));
        } else {
            throw new IllegalStateException(format("%s Not handled", proximityQueryComponent.getProximity()));
        }

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

    private By parseElementQueryComponents(final List<ElementQueryComponent> elementQueryComponentList) {
        return new ByMatchAll(elementQueryComponentList.stream().map(elementQueryComponent -> elementQueryComponent.accept(this)).toArray(By[]::new));
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