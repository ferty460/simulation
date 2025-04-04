package org.example.action.init;

import org.example.entity.Entity;
import org.example.entity.EntityFactory;

public class PlaceStaticObjectsAction extends PlaceEntitiesAction {

    public PlaceStaticObjectsAction(EntityFactory factory) {
        super(factory);
    }

    @Override
    protected Entity createEntity() {
        return entityFactory.createRandomStaticEntity();
    }

    // 15% of the map is occupied by static objects
    @Override
    protected double getDensityFactor() {
        return 0.15;
    }

}

