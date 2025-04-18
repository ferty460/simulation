package org.example.behavior;

import org.example.simulation.Coordinates;
import org.example.Logger;
import org.example.simulation.WorldMap;
import org.example.entity.Entity;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PredatorBehavior extends CreatureBehavior {

    @Override
    protected Optional<List<Coordinates>> findNearestTarget(WorldMap map, Coordinates from) {
        Predicate<Entity> targetCondition = entity -> entity instanceof Herbivore;
        List<Coordinates> path = pathFinder.find(map, from, targetCondition);

        return Optional.ofNullable(path);
    }

    @Override
    protected void interact(Coordinates creatureCoords, Coordinates coordsOfInteractedEntity, WorldMap map) {
        Entity creature = map.getEntityAt(creatureCoords).orElse(null);
        Entity targetCreature = map.getEntityAt(coordsOfInteractedEntity).orElse(null);

        if (creature instanceof Predator predator && targetCreature instanceof Herbivore herbivore) {
            int damage = predator.getAttack();
            herbivore.takeDamage(damage);

            Logger.info(String.format(
                    "Predator %s attacked herbivore %s. Herbivore lost %d health (%d hp)",
                    creatureCoords, coordsOfInteractedEntity, damage, herbivore.getHealth()
            ));

            if (!herbivore.isAlive()) {
                map.removeEntityAt(coordsOfInteractedEntity);
                map.removeEntityAt(creatureCoords);
                map.putEntityAt(coordsOfInteractedEntity, predator);

                Logger.info("Herbivore is dead.");
            }
        }
    }

}
