package org.example.behavior;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.entity.Entity;
import org.example.entity.static_object.Grass;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class HerbivoreBehavior extends CreatureBehavior {

    @Override
    protected Optional<List<Coordinates>> findNearestTarget(WorldMap map, Coordinates from) {
        Predicate<Entity> targetCondition = entity -> entity instanceof Grass;
        List<Coordinates> path = pathFinder.find(map, from, targetCondition);

        return Optional.ofNullable(path);
    }

    @Override
    protected void interact(Coordinates creatureCoords, Coordinates coordsOfInteractedEntity, WorldMap map) {
        map.removeEntityAt(coordsOfInteractedEntity);
        map.getEntityAt(creatureCoords).ifPresent(creature -> {
                map.removeEntityAt(creatureCoords);
                map.putEntityAt(coordsOfInteractedEntity, creature);
        });
    }

}
