package org.example.action.turn;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.action.Action;
import org.example.entity.creature.Creature;

public class MoveAction implements Action {

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : worldMap.getCreatures()) {
            worldMap.getCoordinatesOfEntity(creature).ifPresent(from -> {
                Coordinates to = new Coordinates(from.row(), from.column() - 1);
                creature.makeMove(from, to, worldMap);
            });
        }
    }

}
