package org.example.action.init;

import org.example.entity.Entity;
import org.example.entity.EntityFactory;

import java.util.List;

public class PlaceStaticObjectsAction extends PlaceEntitiesAction {

    public PlaceStaticObjectsAction(EntityFactory factory, double densityFactor) {
        super(factory, densityFactor);
    }

    @Override
    protected List<Entity> createEntities(int count) {
        return entityFactory.createStaticEntities(count);
    }

}

