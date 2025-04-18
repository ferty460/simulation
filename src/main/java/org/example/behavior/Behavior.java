package org.example.behavior;

import org.example.simulation.WorldMap;
import org.example.entity.creature.Creature;

public interface Behavior {

    void act(Creature creature, WorldMap map);

}
