package ch.borja.specalizr.api.action.definition.validation;

public interface Validator<T> {

    void validate(T objectToBeValidated);

}
