package ch.borja.specalizr.intg.selenium.action.impl;

import ch.borja.specalizr.api.action.definition.WriteActionDefinition;
import ch.borja.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class WriteActionDefinitionPlayer implements ActionDefinitionPlayer<WriteActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final WriteActionDefinition<?> writeActionDefinition) {
        SeleniumUtils.safelyLocate(this.webDriver, writeActionDefinition.getWritableElement().accept(this.elementLocationExtractor))
                .sendKeys(writeActionDefinition.getText());
    }
}
