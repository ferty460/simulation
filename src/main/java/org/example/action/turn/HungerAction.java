package org.example.action.turn;

import org.example.Logger;
import org.example.action.Action;
import org.example.entity.creature.Creature;
import org.example.simulation.Coordinates;
import org.example.simulation.WorldMap;
import org.example.simulation.WorldMapUtils;

public class HungerAction implements Action {

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : WorldMapUtils.getCreatures(worldMap)) {
            String creatureName = creature.getClass().getSimpleName();
            int hungerDamage = creature.getEnergyConsumption();
            creature.takeDamage(hungerDamage);

            // todo: пересмотреть механику смерти
            if (!creature.isAlive()) {
                worldMap.getCoordinatesOfEntity(creature).ifPresent(worldMap::removeEntityAt);
                Logger.info(String.format("%s died of hunger", creatureName));
            }
        }
    }

}
