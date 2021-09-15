package ch.qarts.specalizr.intg.selenium.action.impl;

import ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

@AllArgsConstructor
public class CheckUncheckActionDefinitionPlayer implements ActionDefinitionPlayer<CheckUncheckActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final CheckUncheckActionDefinition<?> checkUncheckActionDefinition) {
        final var webElement = SeleniumUtils.safelyLocate(this.webDriver, checkUncheckActionDefinition.getToggable().accept(this.elementLocationExtractor));
        if (checkUncheckActionDefinition.getAction().equals(CheckUncheckActionDefinition.Actions.CHECK)) {
            if (!webElement.isSelected()) {
                webElement.click();
            }
        } else if (checkUncheckActionDefinition.getAction().equals(CheckUncheckActionDefinition.Actions.UNCHECK)) {
            if (webElement.isSelected()) {
                webElement.click();
            }
        } else if (checkUncheckActionDefinition.getAction().equals(CheckUncheckActionDefinition.Actions.TOGGLE)) {
            webElement.click();
        } else {
            throw new RuntimeException(format("action %s not handled", checkUncheckActionDefinition.getAction()));
        }
    }
}
