package ch.qarts.specalizr.intg.web.action.impl.xpath.resolver.impl;

public interface Resolver<T, U> {

    T resolve(U elementToBeResolved);

}
