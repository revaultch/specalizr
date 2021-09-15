package ch.qarts.specalizr.intg.selenium.action.impl;

import ch.qarts.specalizr.api.action.definition.SelectActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

@AllArgsConstructor
public class SelectActionDefinitionPlayer implements ActionDefinitionPlayer<SelectActionDefinition<?>> {

    private WebDriver webDriver;

    private SeleniumXPathQueryComponentResolver elementLocationExtractor;

    @Override
    public void play(final SelectActionDefinition<?> selectActionDefinition) {
        final var webElement = SeleniumUtils.safelyLocate(this.webDriver, selectActionDefinition.getSelectableVisibleElement().accept(this.elementLocationExtractor));
        final Select selector = new Select(webElement);
        selector.selectByVisibleText(selectActionDefinition.getText());
    }
}
