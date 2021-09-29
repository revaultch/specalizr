package ch.qarts.specalizr.web.player;

import ch.qarts.specalizr.api.action.definition.ClickActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.web.utils.SeleniumUtils;
import ch.qarts.specalizr.web.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@AllArgsConstructor
public class ClickActionDefinitionPlayer implements ActionDefinitionPlayer<ClickActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    @Override
    public void play(final ClickActionDefinition<?> clickActionDefinition) {
        new Actions(this.webDriver)
                .moveToElement(SeleniumUtils.safelyLocate(this.webDriver, this.elementResolverFacade.resolve(clickActionDefinition.getClickableElement())))
                .click()
                .perform();
    }
}
