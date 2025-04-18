package org.example.action.turn;

import org.example.entity.creature.Herbivore;

public class RegrowHerbivoreAction extends RegrowEntityAction<Herbivore> {

    public RegrowHerbivoreAction(double creaturesDensityFactor, double herbivoreDensityFactor) {
        super(creaturesDensityFactor, herbivoreDensityFactor);
    }

    @Override
    protected Class<Herbivore> getEntityClass() {
        return Herbivore.class;
    }

    @Override
    protected Herbivore createEntityInstance() {
        return new Herbivore();
    }

    @Override
    protected String getEntityName() {
        return "herbivore";
    }

}
