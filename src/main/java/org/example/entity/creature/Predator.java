package org.example.entity.creature;

public class Predator extends Creature {

    private static final int DEFAULT_SPEED = 4;
    private static final int DEFAULT_HEALTH = 4;
    private static final int DEFAULT_ATTACK = 4;

    private final int attack;

    public Predator(int speed, double health, int attack) {
        super(speed, health);
        this.attack = attack;
    }

    public Predator() {
        this(DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_ATTACK);
    }

    @Override
    public void makeMove() {

    }

}
