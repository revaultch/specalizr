package ch.borja.specalizr.intg.selenium.action;

import ch.borja.specalizr.api.element.Clickable;
import ch.borja.specalizr.intg.selenium.action.impl.ClickActionDefinitionPlayer;
import ch.borja.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.borja.specalizr.intg.selenium.common.LocatorTestBase;
import ch.borja.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static ch.borja.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.borja.specalizr.api.element.Button.button;
import static ch.borja.specalizr.api.element.Link.link;
import static ch.borja.specalizr.api.query.ElementQueryComponent.with;
import static ch.borja.specalizr.api.query.TextQueryComponent.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickActionDefinitionPlayerTest extends LocatorTestBase {


    private String logValue() {
        return (String) ((JavascriptExecutor) this.webDriver).executeScript("return log;");
    }


    private void testClick(final Clickable element, final String bodyContent) {
        // given
        final var page = Page.builder().scriptContent("var log = '';").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var action = click(element);
        final var clickActionPlayer = new ClickActionDefinitionPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver(this.webDriver));
        // when
        clickActionPlayer.play(action);
        // then
        assertEquals("ok", this.logValue());
    }

    @Test
    public void shouldClickButton() {
        this.testClick(button(with(text("Send"))), "<div id='123'><button value='Send' onclick='log = \"ok\"'></div>");
    }

    @Test
    public void shouldClickButtonWithText() {
        this.testClick(button(with(text("Send"))), "<div id='123'><button onclick='log = \"ok\"'>Send</button></div>");
    }

    @Test
    public void shouldClickInputSubmit() {
        this.testClick(button(with(text("Send"))), "<div id='123'><input type='submit' value='Send' onclick='log = \"ok\"'></div>");
    }

    @Test
    public void shouldClickLink() {
        this.testClick(link(with(text("somelink"))), "<div id='123'><a href='/abc/def' onclick='log = \"ok\";return false;'>somelink</a></div>");
    }


}
