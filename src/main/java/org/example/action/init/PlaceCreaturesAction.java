package org.example.action.init;

import org.example.entity.Entity;

public class PlaceCreaturesAction extends PlaceEntitiesAction {

    @Override
    protected Entity createEntity() {
        return entityFactory.createRandomCreature();
    }

    @Override
    protected double getDensityFactor() {
        return 0.04;
    }

}

