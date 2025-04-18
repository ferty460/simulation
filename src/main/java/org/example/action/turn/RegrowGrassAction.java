package org.example.action.turn;

import org.example.Logger;
import org.example.simulation.WorldMap;
import org.example.action.Action;
import org.example.entity.Entity;
import org.example.entity.static_object.Grass;

import java.util.List;

public class RegrowGrassAction implements Action {

    private final double staticObjectsDensityFactor;
    private final double grassDensityFactor;

    public RegrowGrassAction(double staticObjectsDensityFactor, double grassDensityFactor) {
        this.staticObjectsDensityFactor = staticObjectsDensityFactor;
        this.grassDensityFactor = grassDensityFactor;
    }

    @Override
    public void execute(WorldMap worldMap) {
        int requiredGrass = (int) (worldMap.getArea() * staticObjectsDensityFactor * grassDensityFactor);
        int currentGrassCount = (int) getCurrentGrassCountOnMap(worldMap.getAllEntities());
        int grassToRegrow = requiredGrass - currentGrassCount;

        if (grassToRegrow <= 0) {
            return;
        }

        for (int i = 0; i < grassToRegrow; i++) {
            worldMap.putEntityAtRandomCoordinates(new Grass());
        }

        Logger.info(String.format("Regrew %d grass", grassToRegrow));
    }

    private long getCurrentGrassCountOnMap(List<Entity> entities) {
        return entities.stream()
                .filter(entity -> entity instanceof Grass)
                .count();
    }

}
