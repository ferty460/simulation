package org.example.entity.creature;

import org.example.WorldMap;
import org.example.behavior.Behavior;
import org.example.entity.Entity;

public abstract class Creature extends Entity {

    private final int speed;
    private final double health;
    private final Behavior behavior;

    public Creature(int speed, double health, Behavior behavior) {
        this.speed = speed;
        this.health = health;
        this.behavior = behavior;
    }

    public void makeMove(WorldMap map) {
        behavior.act(this, map);
    }

}
