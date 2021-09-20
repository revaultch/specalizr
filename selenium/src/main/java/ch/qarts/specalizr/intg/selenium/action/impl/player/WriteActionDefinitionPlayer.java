package ch.qarts.specalizr.intg.selenium.action.impl.player;

import ch.qarts.specalizr.api.action.definition.WriteActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.action.impl.SeleniumUtils;
import ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class WriteActionDefinitionPlayer implements ActionDefinitionPlayer<WriteActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    @Override
    public void play(final WriteActionDefinition<?> writeActionDefinition) {
        SeleniumUtils.safelyLocate(this.webDriver, this.elementResolverFacade.resolve(writeActionDefinition.getWritableElement()))
                .sendKeys(writeActionDefinition.getText());
    }
}
