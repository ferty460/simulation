package org.example.action.turn;

import org.example.Logger;
import org.example.action.Action;
import org.example.entity.Entity;
import org.example.simulation.WorldMap;
import org.example.simulation.WorldMapUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class RegrowEntityAction<T extends Entity> implements Action {

    private static final int NOTHING_TO_REGROW = 0;

    private final double entityTypeDensityFactor;
    private final double entityDensityFactor;

    public RegrowEntityAction(double entityTypeDensityFactor, double entityDensityFactor) {
        this.entityTypeDensityFactor = entityTypeDensityFactor;
        this.entityDensityFactor = entityDensityFactor;
    }

    protected abstract Class<T> getEntityClass();
    protected abstract T createEntityInstance();
    protected abstract String getEntityName();

    @Override
    public void execute(WorldMap worldMap) {
        int requiredCount = (int) Math.round(worldMap.getArea() * entityTypeDensityFactor * entityDensityFactor);
        List<Entity> entities = new ArrayList<>(worldMap.getEntitiesMap().values());
        long currentCount = getCurrentEntityCount(entities, getEntityClass());
        int toRegrow = requiredCount - (int) currentCount;

        if (toRegrow <= NOTHING_TO_REGROW) {
            return;
        }

        for (int i = 0; i < toRegrow; i++) {
            WorldMapUtils.putEntityAtRandom(createEntityInstance(), worldMap);
        }

        Logger.info(String.format("Regrew %d %s", toRegrow, getEntityName()));
    }

    private long getCurrentEntityCount(List<Entity> entities, Class<T> type) {
        return entities.stream()
                .filter(type::isInstance)
                .count();
    }

}
