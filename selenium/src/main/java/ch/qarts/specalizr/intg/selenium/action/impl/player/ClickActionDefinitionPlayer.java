package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.ClickActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.SeleniumUtils;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.ByResolver;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@AllArgsConstructor
public class ClickActionDefinitionPlayer implements ActionDefinitionPlayer<ClickActionDefinition<?>> {

    private WebDriver webDriver;

    private ByResolver byResolver;

    @Override
    public void play(final ClickActionDefinition<?> clickActionDefinition) {
        new Actions(this.webDriver)
                .moveToElement(SeleniumUtils.safelyLocate(this.webDriver, this.byResolver.resolve(clickActionDefinition.getClickableElement())))
                .click()
                .perform();
    }
}
