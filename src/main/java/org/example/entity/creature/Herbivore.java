package org.example.entity.creature;

import org.example.Config;
import org.example.behavior.HerbivoreBehavior;

public class Herbivore extends Creature {

    private final int nutritionalValue;

    public Herbivore() {
        this(
                Config.getInt("herbivore.speed"),
                Config.getInt("herbivore.health"),
                Config.getInt("herbivore.energyConsumption"),
                Config.getInt("herbivore.nutritionalValue")
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
