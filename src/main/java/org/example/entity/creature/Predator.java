package org.example.entity.creature;

import org.example.behavior.PredatorBehavior;

public class Predator extends Creature {

    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_ENERGY_CONSUMPTION = 10;
    private static final int DEFAULT_ATTACK = 20;

    private final int attack;

    public Predator(int speed, int health, int energyConsumption, int attack) {
        super(speed, health, energyConsumption, new PredatorBehavior());
        this.attack = attack;
    }

    // used in EntityFactory by reflection API
    public Predator() {
        this(DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_ENERGY_CONSUMPTION, DEFAULT_ATTACK);
    }

    public int getAttack() {
        return attack;
    }

}
