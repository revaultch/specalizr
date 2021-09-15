package ch.qarts.specalizr.api.player;

import ch.qarts.specalizr.api.action.ActionDefinition;

public interface ActionDefinitionPlayer<T extends ActionDefinition> {

    void play(T actionDefinition);

}
