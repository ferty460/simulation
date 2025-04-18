package org.example.action.turn;

import org.example.entity.static_object.Grass;

public class RegrowGrassAction extends RegrowEntityAction<Grass> {

    public RegrowGrassAction(double staticObjectsDensityFactor, double grassDensityFactor) {
        super(staticObjectsDensityFactor, grassDensityFactor);
    }

    @Override
    protected Class<Grass> getEntityClass() {
        return Grass.class;
    }

    @Override
    protected Grass createEntityInstance() {
        return new Grass();
    }

    @Override
    protected String getEntityName() {
        return "grass";
    }

}
