package ch.borja.specalizr.intg.selenium.action;

import ch.borja.specalizr.api.element.Selectable;
import ch.borja.specalizr.intg.selenium.action.impl.SelectActionDefinitionPlayer;
import ch.borja.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.borja.specalizr.intg.selenium.common.LocatorTestBase;
import ch.borja.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static ch.borja.specalizr.api.action.definition.SelectActionDefinition.select;
import static ch.borja.specalizr.api.element.Select.selector;
import static ch.borja.specalizr.api.query.ElementQueryComponent.with;
import static ch.borja.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectActionDefinitionPlayerTest extends LocatorTestBase {


    private String selectedValue() {
        return (String) ((JavascriptExecutor) this.webDriver).executeScript("return document.getElementById('select').value;");
    }


    private void testSelect(final String text, final Selectable element, final String bodyContent) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var action = select(text).from(element);
        final var selectActionDefinitionPlayer = new SelectActionDefinitionPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver(this.webDriver));
        // when
        selectActionDefinitionPlayer.play(action);
        // then
        assertEquals(text, this.selectedValue());
    }

    @Test
    public void shouldSelectRightOption() {
        this.testSelect("one", selector(with(text("none"))), "<div id='123'><select id='select' type='text' value='xyz'><option selected>none</option><option>one</option><option>two</option></select></div>");
    }


}
