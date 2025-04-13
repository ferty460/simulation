package org.example.behavior;

import org.example.Coordinates;
import org.example.WorldMap;
import org.example.entity.Entity;
import org.example.entity.creature.Creature;
import org.example.entity.creature.Herbivore;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PredatorBehavior extends CreatureBehavior {

    @Override
    protected Optional<Coordinates> findNearestTarget(WorldMap map, Coordinates from) {
        Predicate<Entity> targetCondition = entity -> entity instanceof Herbivore;
        List<Coordinates> path = pathFinder.find(map, from, targetCondition);
        Coordinates target = path.size() > 1 ? path.get(0) : from;

        return Optional.ofNullable(target);
    }

    @Override
    protected void interact(Creature creature, Coordinates coordsOfInteractedEntity, WorldMap map) {

    }

}
