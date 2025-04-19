package org.example.behavior;

import org.example.simulation.Coordinates;
import org.example.path_finder.PathFinder;
import org.example.path_finder.PathFinderBFS;
import org.example.simulation.WorldMap;
import org.example.entity.creature.Creature;

import java.util.List;
import java.util.Optional;

public abstract class CreatureBehavior implements Behavior {

    protected final PathFinder pathFinder;

    public CreatureBehavior() {
        this.pathFinder = new PathFinderBFS();
    }

    protected abstract Optional<List<Coordinates>> findNearestTarget(WorldMap map, Coordinates from);

    protected abstract void interact(
            Coordinates creatureCoords,
            Coordinates coordsOfInteractedEntity,
            WorldMap map
    );

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

    private void processPathToTarget(Creature creature, Coordinates from, List<Coordinates> path, WorldMap map) {
        if (path.isEmpty()) {
            return;
        }

        int interactionDistance = creature.getSpeed() + 1;

        if (path.size() <= interactionDistance) {
            int targetIndex = path.size() - 1;
            Coordinates target = path.get(targetIndex);
            interact(from, target, map);
        } else {
            int nextMoveIndex = Math.min(creature.getSpeed(), path.size() - 1);
            Coordinates nextMove = path.get(nextMoveIndex);
            moveToTarget(creature, from, nextMove, map);
        }
    }

    private void moveToTarget(Creature creature, Coordinates from, Coordinates to, WorldMap map) {
        map.removeEntityAt(from);
        map.putEntityAt(to, creature);
    }

}
