package org.example.behavior;

import org.example.simulation.Coordinates;
import org.example.Logger;
import org.example.simulation.WorldMap;
import org.example.entity.Entity;
import org.example.entity.creature.Herbivore;
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
        Entity creature = map.getEntityAt(creatureCoords).orElse(null);
        Entity target = map.getEntityAt(coordsOfInteractedEntity).orElse(null);

        if (creature instanceof Herbivore herbivore && target instanceof Grass grass) {
            int healingEffect = grass.getHealingEffect();
            herbivore.heal(healingEffect);

            Logger.info(String.format(
                    "Herbivore %s restored %d health (%d hp) by eating grass",
                    coordsOfInteractedEntity, healingEffect, herbivore.getHealth()
            ));
        }

        map.removeEntityAt(coordsOfInteractedEntity);
        map.removeEntityAt(creatureCoords);
        map.putEntityAt(coordsOfInteractedEntity, creature);
    }

}
