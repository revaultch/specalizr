package ch.qarts.specalizr.api.query;

public interface ElementVisitor<T> {

    T visit(ProximityQueryComponent nearVisibleElementQuery);

    T visit(TextQueryComponent textVisibleElementQuery);

    T visit(BackgroundColorQueryComponent backgroundColorVisibleElementQuery);

    T visit(SelectedTextQueryComponent selectedTextVisibleElementQuery);

    T visit(ContainsQueryComponent containsVisibleElementQuery);

    T visit(AttributeQueryComponent attributeQuery);

    T visit(LabelQueryComponent labelQueryComponent);

    /*
    T visit(Select selectableVisibleElement);

    T visit(CheckBox checkBox);

    T visit(RadioButton radioButton);

    T visit(Button button);

    T visit(Panel panel);

    T visit(Link link);

    T visit(Image image);

    T visit(Item item);

    T visit(Field writable);*/

}
