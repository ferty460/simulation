package org.example.action.init;

import org.example.entity.Entity;

public class PlaceStaticObjectsAction extends PlaceEntitiesAction {

    @Override
    protected Entity createEntity() {
        return entityFactory.createRandomStaticEntity();
    }

    @Override
    protected double getDensityFactor() {
        return 0.15;
    }

}

