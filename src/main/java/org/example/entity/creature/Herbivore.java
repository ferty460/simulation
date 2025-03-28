package org.example.entity.creature;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 4;
    private static final int DEFAULT_HEALTH = 4;

    public Herbivore(int speed, double health) {
        super(speed, health);
    }

    public Herbivore() {
        this(DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public void makeMove() {

    }

}
