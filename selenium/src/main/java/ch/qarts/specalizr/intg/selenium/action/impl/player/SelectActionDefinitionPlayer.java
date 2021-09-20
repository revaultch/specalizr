package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.SelectActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.SeleniumUtils;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

@AllArgsConstructor
public class SelectActionDefinitionPlayer implements ActionDefinitionPlayer<SelectActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    @Override
    public void play(final SelectActionDefinition<?> selectActionDefinition) {
        final var webElement = SeleniumUtils.safelyLocate(this.webDriver, this.elementResolverFacade.resolve(selectActionDefinition.getSelectableVisibleElement()));
        final Select selector = new Select(webElement);
        selector.selectByVisibleText(selectActionDefinition.getText());
    }
}
