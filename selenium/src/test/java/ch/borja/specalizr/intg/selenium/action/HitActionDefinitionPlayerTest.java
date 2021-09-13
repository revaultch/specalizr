package ch.borja.specalizr.intg.selenium.action;

import ch.borja.specalizr.api.element.Writable;
import ch.borja.specalizr.intg.selenium.action.impl.ClearActionDefinitionPlayer;
import ch.borja.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.borja.specalizr.intg.selenium.common.LocatorTestBase;
import ch.borja.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static ch.borja.specalizr.api.action.definition.ClearActionDefinition.clear;
import static ch.borja.specalizr.api.element.Field.field;
import static ch.borja.specalizr.api.query.ElementQueryComponent.with;
import static ch.borja.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitActionDefinitionPlayerTest extends LocatorTestBase {


    private void testClear(final Writable element, final String bodyContent) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var action = clear(element);
        final var clearActionPlayer = new ClearActionDefinitionPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver(this.webDriver));
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
