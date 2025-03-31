package org.example.behavior;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.entity.creature.Creature;

import java.util.Optional;

public class PredatorBehavior extends CreatureBehavior {

    @Override
    protected Optional<Coordinates> findNearestTarget(WorldMap map, Coordinates from) {
        // todo
        return Optional.empty();
    }

    @Override
    protected void moveToTarget(Creature creature, Coordinates from, Coordinates to, WorldMap map) {
        // todo
    }

}
