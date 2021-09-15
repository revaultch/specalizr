package ch.qarts.specalizr.intg.selenium.action;

import org.junit.jupiter.api.Test;

import static ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition.toggle;
import static ch.qarts.specalizr.api.action.definition.CheckUncheckActionDefinition.uncheck;
import static ch.qarts.specalizr.api.element.CheckBox.checkBox;
import static ch.qarts.specalizr.api.query.ElementQueryComponent.with;
import static ch.qarts.specalizr.api.query.TextQueryComponent.text;
import static java.lang.String.format;

public class CheckBoxCheckUncheckActionDefinitionPlayerTest extends CheckUncheckActionDefinitionPlayerTest {
    public CheckBoxCheckUncheckActionDefinitionPlayerTest() {
        super("checkbox");
    }


    @Test
    public void shouldUncheckChecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123' checked><label for='123'>123Label</label></div></div>", this.type), uncheck(this.toggable), false);
    }

    @Test
    public void shouldToggleUnchecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123'><label for='123'>123Label</label></div></div>", this.type), toggle(checkBox(with(text("123Label")))), true);
    }

    @Test
    public void shouldToggleChecked() {
        this.testAction(format("<div id='000'><div><input type='%s' id='123' name='123' checked><label for='123'>123Label</label></div></div>", this.type), toggle(checkBox(with(text("123Label")))), false);
    }

}
