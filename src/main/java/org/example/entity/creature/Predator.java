package org.example.entity.creature;

import org.example.Config;
import org.example.behavior.PredatorBehavior;

import static org.example.ConfigValues.*;

public class Predator extends Creature {

    private final int attack;

    public Predator() {
        this(
                Config.getInt("predator.speed", MIN_SPEED, MAX_SPEED),
                Config.getInt("predator.health", MIN_HEALTH, MAX_HEALTH),
                Config.getInt("predator.energyConsumption", MIN_ENERGY_CONSUMPTION, MAX_ENERGY_CONSUMPTION),
                Config.getInt("predator.attack", MIN_ATTACK, MAX_ATTACK)
        );
    }

    public Predator(int speed, int health, int energyConsumption, int attack) {
        super(speed, health, energyConsumption, new PredatorBehavior());
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

}
