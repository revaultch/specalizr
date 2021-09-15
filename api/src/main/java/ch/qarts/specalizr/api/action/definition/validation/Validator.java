package ch.qarts.specalizr.api.action.definition.validation;

public interface Validator<T> {

    void validate(T objectToBeValidated);

}
