package ch.borja.specalizr.api.query;

import ch.borja.specalizr.api.color.Colors;
import lombok.Getter;


public class TextColorLocation {

    @Getter
    private final Colors color;

    private TextColorLocation(final Colors color) {
        this.color = color;
    }

    public static TextColorLocation textColor(final Colors color) {
        return new TextColorLocation(color);
    }

}
