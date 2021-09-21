package ch.qarts.specalizr.intg.web.action;

import ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition;
import ch.qarts.specalizr.api.element.Toggable;
import ch.qarts.specalizr.intg.web.action.impl.player.CheckUncheckActionDefinitionPlayer;
import ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl.ElementResolverFacade;
import ch.qarts.specalizr.intg.web.common.LocatorTestBase;
import ch.qarts.specalizr.intg.web.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition.check;
import static ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition.uncheck;
import static ch.qarts.specalizr.api.element.CheckBox.checkbox;
import static ch.qarts.specalizr.api.element.RadioButton.radioButton;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CheckUncheckActionDefinitionPlayerTest extends LocatorTestBase {

    protected final String type;

    protected Toggable toggable;

    public CheckUncheckActionDefinitionPlayerTest(final String type) {
        this.type = type;
        this.initToggable(type);
    }

    private void initToggable(final String type) {
        this.toggable = type.equals("checkbox") ? checkbox(with(text("123Label"))) : radioButton(with(text("123Label")));
    }


    protected void testAction(final String bodyContent, final CheckUncheckActionDefinition<Toggable> action, final boolean assrt) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var checkUncheckActionPlayer = new CheckUncheckActionDefinitionPlayer(this.webDriver, ElementResolverFacade.createDefault(webDriver));
        // when
        checkUncheckActionPlayer.play(action);
        // then
        final var webElement = this.webDriver.findElement(By.id("123"));
        assertEquals(assrt, webElement.isSelected());
    }


    @Test
    public void shouldCheckUnchecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123'><label for='123'>123Label</label></div></div>", this.type), check(this.toggable), true);
    }

    @Test
    public void shouldCheckChecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123' checked><label for='123'>123Label</label></div></div>", this.type), check(this.toggable), true);
    }


    @Test
    public void shouldUncheckUnChecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123'><label for='123'>123Label</label></div></div>", this.type), uncheck(this.toggable), false);
    }


}
