package org.example.action.init;

import org.example.simulation.WorldMap;
import org.example.action.Action;
import org.example.entity.Entity;
import org.example.entity.EntityFactory;

import java.util.List;

public abstract class PlaceEntitiesAction implements Action {

    protected final EntityFactory entityFactory;
    protected final double densityFactor;

    protected PlaceEntitiesAction(EntityFactory factory, double densityFactor) {
        this.entityFactory = factory;
        this.densityFactor = densityFactor;
    }

    @Override
    public void execute(WorldMap worldMap) {
        int total = (int) (worldMap.getArea() * densityFactor);
        List<Entity> entities = createEntities(total);
        for (Entity entity : entities) {
            worldMap.putEntityAtRandomCoordinates(entity);
        }
    }

    protected abstract List<Entity> createEntities(int count);

}

