package org.example.path_finder;

import org.example.simulation.Coordinates;
import org.example.simulation.WorldMap;
import org.example.entity.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface PathFinder {

    List<Coordinates> find(WorldMap map, Coordinates start, Predicate<Entity> targetCondition);

}
