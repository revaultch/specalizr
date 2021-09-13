package ch.borja.specalizr.api.query;

import ch.borja.specalizr.api.element.*;

public interface ElementVisitor<T> {

    T visit(AndQueryComponent andVisibleElementQuery);

    T visit(ProximityQueryComponent nearVisibleElementQuery);

    T visit(TextQueryComponent textVisibleElementQuery);

    T visit(LabelQueryComponent labelVisibleElementQuery);

    T visit(BackgroundColorQueryComponent backgroundColorVisibleElementQuery);

    T visit(SelectedTextQueryComponent selectedTextVisibleElementQuery);

    T visit(ContainsQueryComponent containsVisibleElementQuery);

    T visit(Select selectableVisibleElement);

    T visit(CheckBox checkBox);

    T visit(RadioButton radioButton);

    T visit(AttributeQueryComponent attributeQuery);

    T visit(Button button);

    T visit(Panel panel);

    T visit(Link link);

    T visit(Image image);

    T visit(Item item);

    T visit(Writable writable);

}
