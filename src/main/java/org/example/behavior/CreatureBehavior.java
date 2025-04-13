package org.example.behavior;

import org.example.Coordinates;
import org.example.path_finder.PathFinder;
import org.example.path_finder.PathFinderBFS;
import org.example.WorldMap;
import org.example.entity.creature.Creature;

import java.util.Optional;

public abstract class CreatureBehavior implements Behavior {

    protected final PathFinder pathFinder;

    public CreatureBehavior() {
        this.pathFinder = new PathFinderBFS();
    }

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
        map.removeEntityAt(from);
        map.putEntityAt(to, creature);
    }

    protected abstract void interact(
            Creature interactingCreature,
            Coordinates coordsOfInteractedEntity,
            WorldMap map
    );

}
