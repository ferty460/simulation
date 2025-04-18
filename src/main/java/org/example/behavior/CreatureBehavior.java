package org.example.behavior;

import org.example.simulation.Coordinates;
import org.example.path_finder.PathFinder;
import org.example.path_finder.PathFinderBFS;
import org.example.simulation.WorldMap;
import org.example.entity.creature.Creature;

import java.util.List;
import java.util.Optional;

public abstract class CreatureBehavior implements Behavior {

    private static final int TARGET_INDEX = 1;
    private static final int NEXT_MOVE_INDEX = 1;
    private static final int INTERACTION_DISTANCE = 2;

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

        findNearestTarget(map, from).ifPresent(
            path -> processPathToTarget(creature, from, path, map)
        );
    }

    protected abstract Optional<List<Coordinates>> findNearestTarget(WorldMap map, Coordinates from);

    protected abstract void interact(
            Coordinates creatureCoords,
            Coordinates coordsOfInteractedEntity,
            WorldMap map
    );

    private void processPathToTarget(Creature creature, Coordinates from, List<Coordinates> path, WorldMap map) {
        if (path.isEmpty()) {
            return;
        }

        if (path.size() == INTERACTION_DISTANCE) {
            Coordinates target = path.get(TARGET_INDEX);
            interact(from, target, map);
        } else {
            Coordinates nextMove = path.get(NEXT_MOVE_INDEX);
            moveToTarget(creature, from, nextMove, map);
        }
    }

    private void moveToTarget(Creature creature, Coordinates from, Coordinates to, WorldMap map) {
        map.removeEntityAt(from);
        map.putEntityAt(to, creature);
    }

}
