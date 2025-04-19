package org.example.action.turn;

import org.example.entity.creature.Predator;

public class RegrowPredatorAction extends RegrowEntityAction<Predator> {

    public RegrowPredatorAction(double creaturesDensityFactor, double herbivoreDensityFactor) {
        super(creaturesDensityFactor, herbivoreDensityFactor);
    }

    @Override
    protected Class<Predator> getEntityClass() {
        return Predator.class;
    }

    @Override
    protected Predator createEntityInstance() {
        return new Predator();
    }

    @Override
    protected String getEntityName() {
        return "predator";
    }

}
