package ch.borja.specalizr.api.player;

import ch.borja.specalizr.api.action.ActionDefinition;

public interface ActionDefinitionPlayer<T extends ActionDefinition> {

    void play(T actionDefinition);

}
