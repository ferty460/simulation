package org.example.action.turn;

import org.example.action.Action;
import org.example.entity.creature.Creature;
import org.example.simulation.WorldMap;
import org.example.simulation.WorldMapUtils;

public class MoveAction implements Action {

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : WorldMapUtils.getCreatures(worldMap)) {
            creature.makeMove(worldMap);
        }
    }

}
