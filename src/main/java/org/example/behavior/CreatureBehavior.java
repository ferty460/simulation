package org.example.behavior;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.entity.creature.Creature;

import java.util.Optional;

public abstract class CreatureBehavior implements Behavior {

    @Override
    public void act(Creature creature, WorldMap map) {
        Coordinates from = map.getCoordinatesOfEntity(creature).orElse(null);
        if (from == null) {
            return;
        }

        findNearestTarget(map, from).ifPresent(to -> {
                moveToTarget(creature, from, to, map);
                interact(creature, to, map);
            }
        );
    }

    protected abstract Optional<Coordinates> findNearestTarget(WorldMap map, Coordinates from);

    private void moveToTarget(Creature creature, Coordinates from, Coordinates to, WorldMap map) {
        /*
        PathFinder.findPathBFS(map, from, to).ifPresent(path -> {
            // если цель не рядом
            if (path.size() > 1) {
                map.removeEntityAt(from);
                map.putEntityAt(nextMove, creature);
            }
        });
        */

        map.removeEntityAt(from);
        map.putEntityAt(to, creature);
    }

    protected abstract void interact(Creature interactingCreature, Coordinates coordsOfInteractedEntity, WorldMap map);

}
