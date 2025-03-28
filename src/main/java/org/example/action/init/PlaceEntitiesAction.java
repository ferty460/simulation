package org.example.action.init;

import org.example.WorldMap;
import org.example.action.Action;
import org.example.entity.Entity;
import org.example.entity.EntityFactory;

public abstract class PlaceEntitiesAction implements Action {

    protected final EntityFactory entityFactory;

    protected PlaceEntitiesAction() {
        this.entityFactory = new EntityFactory();
    }

    @Override
    public void execute(WorldMap worldMap) {
        int mapArea = worldMap.getArea();
        int optimalNumEntities = (int) Math.floor(mapArea * getDensityFactor());

        for (int i = 0; i < optimalNumEntities; i++) {
            Entity entity = createEntity();
            worldMap.putEntityAtRandomCoordinates(entity);
        }
    }

    protected abstract Entity createEntity();

    protected abstract double getDensityFactor();

}

