package ch.qarts.specalizr.web.player;

import ch.qarts.specalizr.api.action.definition.PressActionDefinition;
import ch.qarts.specalizr.api.player.ActionDefinitionPlayer;
import ch.qarts.specalizr.web.utils.SeleniumUtils;
import ch.qarts.specalizr.web.ElementResolverFacade;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class HitActionDefinitionPlayer implements ActionDefinitionPlayer<PressActionDefinition<?>> {

    private WebDriver webDriver;

    private ElementResolverFacade elementResolverFacade;

    @Override
    public void play(final PressActionDefinition<?> hitActionDefinition) {
        if (hitActionDefinition.getWritableElement() != null) {
            SeleniumUtils.safelyLocate(this.webDriver, this.elementResolverFacade.resolve(hitActionDefinition.getWritableElement()))
                    .sendKeys(hitActionDefinition.getKey().toString());
        } else {
            this.webDriver.switchTo().activeElement().sendKeys(hitActionDefinition.getKey().toString());
        }
    }
}
