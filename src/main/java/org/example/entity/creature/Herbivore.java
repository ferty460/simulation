package org.example.entity.creature;

import org.example.behavior.HerbivoreBehavior;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 2;
    private static final int DEFAULT_HEALTH = 100;

    public Herbivore(int speed, int health) {
        super(speed, health, new HerbivoreBehavior());
    }

    public Herbivore() {
        this(DEFAULT_SPEED, DEFAULT_HEALTH);
    }

}
