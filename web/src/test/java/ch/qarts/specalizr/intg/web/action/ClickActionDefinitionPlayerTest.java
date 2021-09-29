package ch.qarts.specalizr.intg.web.action;

import ch.qarts.specalizr.api.element.Clickable;
import ch.qarts.specalizr.web.player.ClickActionDefinitionPlayer;
import ch.qarts.specalizr.web.ElementResolverFacade;
import ch.qarts.specalizr.intg.web.common.LocatorTestBase;
import ch.qarts.specalizr.intg.web.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static ch.qarts.specalizr.api.action.definition.ClickActionDefinition.click;
import static ch.qarts.specalizr.api.element.Button.button;
import static ch.qarts.specalizr.api.element.Link.link;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
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
        final var clickActionPlayer = new ClickActionDefinitionPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
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
