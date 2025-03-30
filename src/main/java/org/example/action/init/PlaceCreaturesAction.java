package org.example.action.init;

import org.example.entity.Entity;

public class PlaceCreaturesAction extends PlaceEntitiesAction {

    @Override
    protected Entity createEntity() {
        return entityFactory.createRandomCreature();
    }

    // 4% of the map is occupied by creatures
    @Override
    protected double getDensityFactor() {
        return 0.04;
    }

}

