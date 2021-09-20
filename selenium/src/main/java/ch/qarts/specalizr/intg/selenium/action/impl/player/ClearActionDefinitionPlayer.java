package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.ClearActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.SeleniumUtils;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class ClearActionDefinitionPlayer implements ActionDefinitionPlayer<ClearActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    @Override
    public void play(final ClearActionDefinition<?> clearActionDefinition) {
        SeleniumUtils.safelyLocate(this.webDriver, this.elementResolverFacade.resolve(clearActionDefinition.getWritableElement())).clear();
    }
}
