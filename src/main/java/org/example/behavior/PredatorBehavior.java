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
    protected void interact(Coordinates predatorCoords, Coordinates herbivoreCoords, WorldMap map) {
        Optional<Entity> predatorOpt = map.getEntityAt(predatorCoords);
        Optional<Entity> herbivoreOpt = map.getEntityAt(herbivoreCoords);

        if (predatorOpt.isEmpty() || herbivoreOpt.isEmpty()) {
            return;
        }

        if (predatorOpt.get() instanceof Predator predator && herbivoreOpt.get() instanceof Herbivore herbivore) {
            int damage = predator.getAttack();
            herbivore.takeDamage(damage);

            Logger.info(String.format(
                    "Predator %s attacked herbivore %s. Herbivore lost %d health (%d hp)",
                    predatorCoords, herbivoreCoords, damage, herbivore.getHealth()
            ));

            // todo: пересмотреть механику смерти
            if (!herbivore.isAlive()) {
                handleHerbivoreDeath(predator, herbivore, predatorCoords, herbivoreCoords, map);
            }
        }
    }

    private void handleHerbivoreDeath(
            Predator predator,
            Herbivore herbivore,
            Coordinates predatorCoords,
            Coordinates herbivoreCoords,
            WorldMap map
    ) {
        map.removeEntityAt(herbivoreCoords);
        map.removeEntityAt(predatorCoords);
        map.putEntityAt(herbivoreCoords, predator);

        int healingEffect = herbivore.getNutritionalValue();
        predator.heal(healingEffect);

        Logger.info(String.format(
                "Predator %s restored %d health (%d hp) by eating herbivore",
                herbivoreCoords, healingEffect, predator.getHealth()
        ));
        Logger.info("Herbivore is dead.");
    }

}
