package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.action.definition.ClearActionDefinition;
import ch.borja.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class ClearActionDefinitionPlayer implements ActionDefinitionPlayer<ClearActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final ClearActionDefinition<?> clearActionDefinition) {
        SeleniumUtils.safelyLocate(this.webDriver, clearActionDefinition.getWritableElement().accept(this.elementLocationExtractor)).clear();
    }
}
