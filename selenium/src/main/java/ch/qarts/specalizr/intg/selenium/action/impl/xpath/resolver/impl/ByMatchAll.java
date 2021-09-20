package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A <b>reasonably performant</b> {@link By} implementation that matches all provided Bys <p>
 */
@ToString
@Log4j
class ByMatchAll extends By {

    private final List<By> byList = new ArrayList<>();

    ByMatchAll(final List<By> parseElementQueryComponents) {
        this.byList.addAll(parseElementQueryComponents);
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        final Map<String, Integer> all = new HashMap<>();
        final Set<RemoteWebElement> remoteWebElementList = new HashSet<>();
        for (final var by : this.byList) {
            new FluentWait<>(context)
                    .withTimeout(Duration.ofSeconds(30))
                    .ignoring(NoSuchElementException.class)
                    .until(searchContext -> {
                        final List<WebElement> elements = context.findElements(by);
                        ByMatchAll.log.debug(by + " : " + elements.size());
                        return elements;
                    })
                    .forEach(webElement -> {
                        final var remoteWebElement = (RemoteWebElement) webElement;
                        remoteWebElementList.add(remoteWebElement);
                        final var key = ((RemoteWebElement) webElement).getId();
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
