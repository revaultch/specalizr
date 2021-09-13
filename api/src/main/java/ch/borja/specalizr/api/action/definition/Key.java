package ch.borja.specalizr.api.action.definition;

import lombok.Getter;

public enum Key {

    BACKSPACE('\uE003'),

    CLEAR('\uE005'),

    ENTER('\uE007'),

    ESCAPE('\uE00C'),

    TAB('\uE004'),

    RETURN('\uE006');


    @Getter
    private final char chr;

    Key(final char chr) {
        this.chr = chr;
    }

    @Override
    public String toString() {
        return String.valueOf(this.chr);
    }

}
