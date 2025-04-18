package org.example.action.turn;

import org.example.simulation.WorldMap;
import org.example.action.Action;
import org.example.entity.creature.Creature;

import java.util.List;

public class MoveAction implements Action {

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : getCreatures(worldMap)) {
            creature.makeMove(worldMap);
        }
    }

    private List<Creature> getCreatures(WorldMap worldMap) {
        return worldMap.getAllEntities().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
    }

}
