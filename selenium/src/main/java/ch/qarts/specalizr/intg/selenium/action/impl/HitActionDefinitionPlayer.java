package ch.qarts.specalizr.intg.selenium.action.impl;

import ch.qarts.specalizr.api.action.definition.PressActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class HitActionDefinitionPlayer implements ActionDefinitionPlayer<PressActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final PressActionDefinition<?> hitActionDefinition) {
        if (hitActionDefinition.getWritableElement() != null) {
            SeleniumUtils.safelyLocate(this.webDriver, hitActionDefinition.getWritableElement().accept(this.elementLocationExtractor))
                    .sendKeys(hitActionDefinition.getKey().toString());
        } else {
            this.webDriver.switchTo().activeElement().sendKeys(hitActionDefinition.getKey().toString());
        }
    }
}
