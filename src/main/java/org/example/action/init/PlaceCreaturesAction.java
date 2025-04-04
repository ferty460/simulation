package org.example.action.init;

import org.example.entity.Entity;
import org.example.entity.EntityFactory;

public class PlaceCreaturesAction extends PlaceEntitiesAction {

    public PlaceCreaturesAction(EntityFactory factory) {
        super(factory);
    }

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

