package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.ActionDefinition;
import ch.qarts.specalizr.api.element.Validatable;
import lombok.Getter;

public class ValidationActionDefinition<T extends Validatable> implements ActionDefinition {

    @Getter
    private final T validatableElement;

    @Getter
    private final ElementValidationCondition elementValidationCondition;

    private ValidationActionDefinition(final T validatableElement, final ElementValidationCondition elementValidationCondition) {
        this.validatableElement = validatableElement;
        this.elementValidationCondition = elementValidationCondition;
    }

    public static <T extends Validatable> ValidationActionDefinition<T> validate(final T element, final ElementValidationCondition validationCondition) {
        return new ValidationActionDefinition<T>(element, validationCondition);
    }


}
