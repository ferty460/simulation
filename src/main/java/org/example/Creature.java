package org.example;

public abstract class Creature extends Entity {

    private final int speed;
    private final double health;

    public Creature(int speed, double health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove();

}
