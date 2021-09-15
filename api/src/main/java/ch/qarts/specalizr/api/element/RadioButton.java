package ch.qarts.specalizr.api.element;

import ch.qarts.specalizr.api.query.ElementQueryComponent;

public class RadioButton extends ElementBase implements Clickable, Toggable {

    private RadioButton(final ElementQueryComponent... elementQueryComponentList) {
        super(elementQueryComponentList);
    }

    public static RadioButton radioButton(final ElementQueryComponent... elementQueryComponentList) {
        return new RadioButton(elementQueryComponentList);
    }

}
