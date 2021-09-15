package ch.qarts.specalizr.api.player;


import ch.qarts.specalizr.api.action.ActionDefinition;

public interface ActionDefinitionPlayerRegistry {

    <T extends ActionDefinition> ActionDefinitionPlayer<T> forType(Class<T> type);

}
