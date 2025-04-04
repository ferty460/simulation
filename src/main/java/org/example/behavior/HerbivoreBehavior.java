package org.example.behavior;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.entity.creature.Creature;

import java.util.Optional;

public class HerbivoreBehavior extends CreatureBehavior {

    @Override
    protected Optional<Coordinates> findNearestTarget(WorldMap map, Coordinates from) {
        return Optional.empty();
    }

    @Override
    protected void interact(Creature creature, Coordinates coordsOfInteractedEntity, WorldMap map) {

    }

}
