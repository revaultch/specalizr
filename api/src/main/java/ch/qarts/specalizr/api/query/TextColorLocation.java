package ch.qarts.specalizr.api.query;

import ch.qarts.specalizr.api.color.Colors;
import lombok.Getter;


public class TextColorLocation {

    @Getter
    private final Colors color;

    protected TextColorLocation(final Colors color) {
        this.color = color;
    }

    public static TextColorLocation textColor(final Colors color) {
        return new TextColorLocation(color);
    }

}
