package ch.borja.specalizr.api.player;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActionChainPlayResult {

    private List<ActionDefinitionPlayResult> actionDefinitionPlayResultList = new ArrayList<>();

}
