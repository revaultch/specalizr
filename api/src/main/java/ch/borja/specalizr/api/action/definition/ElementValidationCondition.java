package ch.borja.specalizr.api.action.definition;

import ch.borja.specalizr.api.action.definition.validation.ValidationConditionVisitor;
import ch.borja.specalizr.api.action.definition.validation.Validator;

public interface ElementValidationCondition {

    <T> Validator<T> accept(ValidationConditionVisitor<T> visitor);

}
