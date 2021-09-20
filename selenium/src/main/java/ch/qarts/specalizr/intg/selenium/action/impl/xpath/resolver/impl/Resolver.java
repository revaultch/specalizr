package ch.qarts.specalizr.intg.selenium.action.impl.xpath.resolver.impl;

public interface Resolver<T, U> {

    T resolve(U elementToBeResolved);

}
