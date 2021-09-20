package ch.qarts.specalizr.api.query;

import ch.qarts.specalizr.api.element.Element;
import lombok.Getter;
import lombok.ToString;

import static ch.qarts.specalizr.api.query.ProximityQueryComponent.Proximity.*;

@ToString
public class ProximityQueryComponent extends ElementQueryComponent {

    public enum Proximity {
        NEAR,
        BELOW,
        ABOVE,
        RIGHT_OF,
        LEFT_OF
    }

    @Getter
    private final Element element;

    @Getter
    private final Proximity proximity;

    private ProximityQueryComponent(final Proximity proximity, final Element element) {
        this.proximity = proximity;
        this.element = element;
    }

    public static ProximityQueryComponent near(final Element element) {
        return new ProximityQueryComponent(NEAR, element);
    }

    public static ProximityQueryComponent above(final Element element) {
        return new ProximityQueryComponent(ABOVE, element);
    }

    public static ProximityQueryComponent below(final Element element) {
        return new ProximityQueryComponent(BELOW, element);
    }

    public static ProximityQueryComponent rightOf(final Element element) {
        return new ProximityQueryComponent(RIGHT_OF, element);
    }

    public static ProximityQueryComponent leftOf(final Element element) {
        return new ProximityQueryComponent(LEFT_OF, element);
    }

}
