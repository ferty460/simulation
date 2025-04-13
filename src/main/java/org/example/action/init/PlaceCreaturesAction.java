package org.example.action.init;

import org.example.entity.Entity;
import org.example.entity.EntityFactory;

import java.util.ArrayList;
import java.util.List;

public class PlaceCreaturesAction extends PlaceEntitiesAction {

    public PlaceCreaturesAction(EntityFactory factory, double densityFactor) {
        super(factory, densityFactor);
    }

    @Override
    protected List<Entity> createEntities(int count) {
        return new ArrayList<>(entityFactory.createCreatures(count));
    }

}

