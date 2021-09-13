package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.action.definition.ClickActionDefinition;
import ch.borja.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@AllArgsConstructor
public class ClickActionDefinitionPlayer implements ActionDefinitionPlayer<ClickActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final ClickActionDefinition<?> clickActionDefinition) {
        new Actions(this.webDriver)
                .moveToElement(SeleniumUtils.safelyLocate(this.webDriver, clickActionDefinition.getClickableElement().accept(this.elementLocationExtractor)))
                .click()
                .perform();
    }
}
