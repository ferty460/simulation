package org.example.entity.static_object;

import org.example.entity.Entity;

public class Grass extends Entity {

    private static final int MIN_HEAL_EFFECT = 20;
    private static final int MAX_HEAL_EFFECT = 80;

    private final int healingEffect;

    public Grass() {
        this.healingEffect = MIN_HEAL_EFFECT + (int) (Math.random() * (MAX_HEAL_EFFECT - MIN_HEAL_EFFECT));
    }

    public int getHealingEffect() {
        return healingEffect;
    }

}
