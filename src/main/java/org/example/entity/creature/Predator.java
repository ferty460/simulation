package org.example.entity.creature;

import org.example.Config;
import org.example.behavior.PredatorBehavior;

public class Predator extends Creature {

    private final int attack;

    public Predator() {
        this(
                Config.getInt("predator.speed"),
                Config.getInt("predator.health"),
                Config.getInt("predator.energyConsumption"),
                Config.getInt("predator.attack")
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
