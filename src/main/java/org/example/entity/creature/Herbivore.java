package org.example.entity.creature;

import org.example.behavior.HerbivoreBehavior;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_ENERGY_CONSUMPTION = 20;
    private static final int DEFAULT_NUTRITIONAL_VALUE = 50;

    private final int nutritionalValue;

    public Herbivore(int speed, int health, int energyConsumption, int nutritionalValue) {
        super(speed, health, energyConsumption, new HerbivoreBehavior());
        this.nutritionalValue = nutritionalValue;
    }

    // used in EntityFactory by reflection API
    public Herbivore() {
        this(DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_ENERGY_CONSUMPTION, DEFAULT_NUTRITIONAL_VALUE);
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }
}
