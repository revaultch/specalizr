package ch.qarts.specalizr.web.xpath.resolver;

public interface Resolver<T, U> {

    T resolve(U elementToBeResolved);

}
