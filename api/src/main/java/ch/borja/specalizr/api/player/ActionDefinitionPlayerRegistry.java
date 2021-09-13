package ch.borja.specalizr.api.player;


import ch.borja.specalizr.api.action.ActionDefinition;

public interface ActionDefinitionPlayerRegistry {

    <T extends ActionDefinition> ActionDefinitionPlayer<T> forType(Class<T> type);

}
