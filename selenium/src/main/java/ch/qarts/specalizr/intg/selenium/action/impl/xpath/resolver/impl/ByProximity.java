package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import ch.qarts.specalizr.api.query.ProximityQueryComponent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static ch.qarts.specalizr.api.query.ProximityQueryComponent.Proximity.*;

/**
 * NOTE : selenium relative selectors do not work at the moment (flawed) when there is an overlap (i.e. try relative with "=" and fields in google unit converter)<p>
 * also see comment here : https://angiejones.tech/selenium-4-relative-locators/
 */
@Slf4j
@AllArgsConstructor
public class ByProximity extends By {

    private ProximityQueryComponent.Proximity proximity;

    private By hint;

    private WebElement target;

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        return context.findElements(this.hint).stream().filter(item -> this.filter(item, target)).collect(Collectors.toList());
    }

    private boolean filter(final WebElement item, final WebElement reference) {
        try {
            if (this.proximity.equals(ABOVE)) {
                return item.getRect().getY() < reference.getRect().getY();
            } else if (this.proximity.equals(BELOW)) {
                return item.getRect().getY() > reference.getRect().getY();
            } else if (this.proximity.equals(LEFT_OF)) {
                return item.getRect().getX() < reference.getRect().getX();
            } else if (this.proximity.equals(RIGHT_OF)) {
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
