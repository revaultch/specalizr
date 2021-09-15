package ch.qarts.specalizr.intg.selenium.action;

import ch.qarts.specalizr.api.element.Writable;
import ch.qarts.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.qarts.specalizr.intg.selenium.action.impl.WriteActionDefinitionPlayer;
import ch.qarts.specalizr.intg.selenium.common.LocatorTestBase;
import ch.qarts.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static ch.qarts.specalizr.api.action.definition.WriteActionDefinition.write;
import static ch.qarts.specalizr.api.element.Field.field;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteActionDefinitionPlayerTest extends LocatorTestBase {

    private static final String SCRIPT_CONTENT = "var log = '';";

    private void clearValue() {
        ((JavascriptExecutor) this.webDriver).executeScript("log = document.getElementById('field').value = '';");
    }

    private void refreshValue() {
        ((JavascriptExecutor) this.webDriver).executeScript("log = document.getElementById('field').value;");
    }

    private String logValue() {
        return (String) ((JavascriptExecutor) this.webDriver).executeScript("return log;");
    }


    private void testWrite(final String text, final Writable element, final String bodyContent) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var action = write(text).into(element);
        final var writeActionPlayer = new WriteActionDefinitionPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver());
        // when
        this.clearValue();
        writeActionPlayer.play(action);
        // then
        this.refreshValue();
        assertEquals(text, this.logValue());
    }

    @Test
    public void shouldWriteTextIntoInputText() {
        this.testWrite("hello1", field(with(text("xyz"))), "<div id='123'><input id='field' type='text' value='xyz'></div>");
    }

    @Test
    public void shouldWriteTextIntoTextArea() {
        this.testWrite("hello2", field(with(text("xyz"))), "<div id='123'><textarea id='field' value='xyz'></div>");
    }

}
