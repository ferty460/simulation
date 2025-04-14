package org.example.entity.creature;

import org.example.WorldMap;
import org.example.behavior.Behavior;
import org.example.entity.Entity;

public abstract class Creature extends Entity {

    private final int speed;
    private final double maxHealth;
    private double health;
    private final Behavior behavior;

    public Creature(int speed, double health, Behavior behavior) {
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.behavior = behavior;
    }

    public void makeMove(WorldMap map) {
        behavior.act(this, map);
    }

    public int getSpeed() {
        return speed;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void heal(double amount) {
        this.health = Math.min(maxHealth, this.health + amount);
    }

}
