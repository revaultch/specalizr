package ch.qarts.specalizr.intg.web.action;

import ch.qarts.specalizr.api.element.Writable;
import ch.qarts.specalizr.web.player.ClearActionDefinitionPlayer;
import ch.qarts.specalizr.web.ElementResolverFacade;
import ch.qarts.specalizr.intg.web.common.LocatorTestBase;
import ch.qarts.specalizr.intg.web.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static ch.qarts.specalizr.api.action.definition.ClearActionDefinition.clear;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitActionDefinitionPlayerTest extends LocatorTestBase {


    private void testClear(final Writable element, final String bodyContent) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var action = clear(element);
        final var clearActionPlayer = new ClearActionDefinitionPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
        // when
        clearActionPlayer.play(action);
        // then
        final var webElement = this.webDriver.findElement(By.id("field"));
        assertEquals("", webElement.getText());

    }

    @Test
    public void shouldClearTextFromInput() {
        this.testClear(field(with(text("xyz"))), "<div id='123'><input id='field' type='text' value='xyz'></div>");
    }

    @Test
    public void shouldClearTextFromTextarea() {
        this.testClear(field(with(text("xyz"))), "<div id='123'><textarea id='field' value='xyz'></textarea></div>");
    }

}
