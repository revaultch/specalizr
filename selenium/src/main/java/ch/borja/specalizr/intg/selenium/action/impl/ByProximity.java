package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.query.ProximityQueryComponent;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static ch.borja.specalizr.api.query.ProximityQueryComponent.Proximity.*;
import static ch.borja.specalizr.intg.selenium.action.impl.SeleniumUtils.singlify;

/**
 * NOTE : selenium relative selectors do not work at the moment (flawed) when there is an overlap (i.e. try relative with "=" and fields in google unit converter)<p>
 * also see comment here : https://angiejones.tech/selenium-4-relative-locators/
 */
@Slf4j
public class ByProximity extends By {

    private final ProximityQueryComponent proximityQueryComponent;

    private By by;

    public ByProximity(final ProximityQueryComponent proximityQueryComponent) {
        this.proximityQueryComponent = proximityQueryComponent;
    }

    public ByProximity with(final By by) {
        this.by = by;
        return this;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        final var reference = singlify(context, this.proximityQueryComponent.getElement().accept(new SeleniumXPathQueryComponentResolver()));
        return context.findElements(this.by).stream().filter(item -> this.filter(item, reference)).collect(Collectors.toList());
    }

    private boolean filter(final WebElement item, final WebElement reference) {
        try {
            if (this.proximityQueryComponent.getProximity().equals(ABOVE)) {
                return item.getRect().getY() < reference.getRect().getY();
            } else if (this.proximityQueryComponent.getProximity().equals(BELOW)) {
                return item.getRect().getY() > reference.getRect().getY();
            } else if (this.proximityQueryComponent.getProximity().equals(LEFT_OF)) {
                return item.getRect().getX() < reference.getRect().getX();
            } else if (this.proximityQueryComponent.getProximity().equals(RIGHT_OF)) {
                return item.getRect().getX() > reference.getRect().getX();
            } else {
                throw new IllegalStateException("Unhandled proximity type");
            }
        } catch (final StaleElementReferenceException e) {
            // TODO fix this ... when stale performance drops massively
            e.printStackTrace();
            return false;
        }
    }
}
