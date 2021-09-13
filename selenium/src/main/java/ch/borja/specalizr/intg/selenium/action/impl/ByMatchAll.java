package ch.borja.specalizr.intg.selenium.action.impl;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.IsRemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A <b>reasonably performant</b> {@link By} implementation that matches all provided Bys <p>
 */
@ToString
@Slf4j
class ByMatchAll extends By {

    private final List<By> byList;

    ByMatchAll(final By... byList) {
        this.byList = Arrays.asList(byList);
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        final Map<String, Integer> all = new HashMap<>();
        final Set<IsRemoteWebElement> remoteWebElementList = new HashSet<>();
        for (final var by : this.byList) {
            new FluentWait<>(context)
                    .withTimeout(Duration.ofSeconds(30))
                    .ignoring(NoSuchElementException.class)
                    .until(searchContext -> {
                        final var elements = context.findElements(by);
                        ByMatchAll.log.debug(by + " : " + elements.size());
                        return elements;
                    })
                    .forEach(webElement -> {
                        final var remoteWebElement = (IsRemoteWebElement) webElement;
                        remoteWebElementList.add(remoteWebElement);
                        final var key = ((IsRemoteWebElement) webElement).getId();
                        final var containsKey = all.containsKey(key);
                        if (containsKey) {
                            all.put(key, all.get(key) + 1);
                        } else {
                            all.put(remoteWebElement.getId(), 1);
                        }
                    });
        }
        final var candidatesIds = all.entrySet().stream().filter(stringIntegerEntry -> stringIntegerEntry.getValue().equals(this.byList.size())).map(Map.Entry::getKey).collect(Collectors.toList());
        return remoteWebElementList.stream().filter(isRemoteWebElement -> candidatesIds.contains(isRemoteWebElement.getId())).collect(Collectors.toList());
    }


}
