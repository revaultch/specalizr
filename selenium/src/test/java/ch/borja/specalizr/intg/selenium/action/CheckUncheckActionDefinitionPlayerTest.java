package ch.borja.specalizr.intg.selenium.action;

import ch.borja.specalizr.api.action.definition.CheckUncheckActionDefinition;
import ch.borja.specalizr.api.element.Toggable;
import ch.borja.specalizr.intg.selenium.action.impl.CheckUncheckActionDefinitionPlayer;
import ch.borja.specalizr.intg.selenium.action.impl.SeleniumXPathQueryComponentResolver;
import ch.borja.specalizr.intg.selenium.common.LocatorTestBase;
import ch.borja.specalizr.intg.selenium.common.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static ch.borja.specalizr.api.action.definition.CheckUncheckActionDefinition.check;
import static ch.borja.specalizr.api.action.definition.CheckUncheckActionDefinition.uncheck;
import static ch.borja.specalizr.api.element.CheckBox.checkBox;
import static ch.borja.specalizr.api.element.RadioButton.radioButton;
import static ch.borja.specalizr.api.query.ElementQueryComponent.with;
import static ch.borja.specalizr.api.query.TextQueryComponent.text;
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
        this.toggable = type.equals("checkbox") ? checkBox(with(text("123Label"))) : radioButton(with(text("123Label")));
    }


    protected void testAction(final String bodyContent, final CheckUncheckActionDefinition<Toggable> action, final boolean assrt) {
        // given
        final var page = Page.builder().scriptContent("").bodyContent(bodyContent).build();
        this.webDriver.navigate().to(page.generateAsDataUrl());
        final var checkUncheckActionPlayer = new CheckUncheckActionDefinitionPlayer(this.webDriver, new SeleniumXPathQueryComponentResolver());
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
