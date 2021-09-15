package ch.qarts.specalizr.api.action.definition;

import ch.qarts.specalizr.api.action.definition.validation.ValidationConditionVisitor;
import ch.qarts.specalizr.api.action.definition.validation.Validator;

public interface ElementValidationCondition {

    <T> Validator<T> accept(ValidationConditionVisitor<T> visitor);

}
