package org.example.entity.creature;

import org.example.WorldMap;
import org.example.behavior.Behavior;
import org.example.entity.Entity;

public abstract class Creature extends Entity {

    private final int speed;
    private final int maxHealth;
    private int health;
    private final Behavior behavior;

    public Creature(int speed, int health, Behavior behavior) {
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

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void heal(double amount) {
        this.health = (int) Math.min(maxHealth, this.health + amount);
    }

    public void takeDamage(double amount) {
        this.health = (int) Math.max(0, this.health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

}
