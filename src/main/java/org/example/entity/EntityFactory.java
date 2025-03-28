package org.example.entity;

import org.example.entity.creature.Creature;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;

import java.util.Random;

public class EntityFactory {

    private static final int STATIC_ENTITY_NUM = 3;
    private static final int ROCK = 0;
    private static final int GRASS = 1;

    private final Random rand;

    public EntityFactory() {
        this.rand = new Random();
    }

    public Entity createRandomStaticEntity() {
        return switch (rand.nextInt(STATIC_ENTITY_NUM)) {
            case ROCK -> new Rock();
            case GRASS -> new Grass();
            default -> new Tree();
        };
    }

    public Creature createRandomCreature() {
        if (rand.nextBoolean()) {
            return new Herbivore();
        }

        return new Predator();
    }

}
