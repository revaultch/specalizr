package ch.qarts.specalizr.web.player;

import ch.qarts.specalizr.api.action.definition.WriteActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.web.utils.SeleniumUtils;
import ch.qarts.specalizr.web.ElementResolverFacade;
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
