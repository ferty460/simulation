package org.example.entity.creature;

import org.example.config.Config;
import org.example.behavior.HerbivoreBehavior;

import static org.example.config.ConfigValues.*;

public class Herbivore extends Creature {

    private final int nutritionalValue;

    public Herbivore() {
        this(
                Config.getInt("herbivore.speed", MIN_SPEED, MAX_SPEED),
                Config.getInt("herbivore.health", MIN_HEALTH, MAX_HEALTH),
                Config.getInt("herbivore.energyConsumption", MIN_ENERGY_CONSUMPTION, MAX_ENERGY_CONSUMPTION),
                Config.getInt("herbivore.nutritionalValue", MIN_NUTRITIONAL_VALUE, MAX_NUTRITIONAL_VALUE)
        );
    }

    public Herbivore(int speed, int health, int energyConsumption, int nutritionalValue) {
        super(speed, health, energyConsumption, new HerbivoreBehavior());
        this.nutritionalValue = nutritionalValue;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}
