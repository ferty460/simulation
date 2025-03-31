package org.example.action.turn;

import org.example.WorldMap;
import org.example.action.Action;
import org.example.entity.creature.Creature;

public class MoveAction implements Action {

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : worldMap.getCreatures()) {
            creature.makeMove(worldMap);
        }
    }

}
