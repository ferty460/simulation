package org.example;

import org.example.action.Action;
import org.example.action.turn.MoveAction;
import org.example.action.init.PlaceCreaturesAction;
import org.example.action.init.PlaceStaticObjectsAction;
import org.example.entity.Entity;
import org.example.entity.EntityFactory;
import org.example.entity.creature.Creature;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.static_object.Grass;
import org.example.entity.static_object.Rock;
import org.example.entity.static_object.Tree;
import org.example.renderer.ConsoleRenderer;
import org.example.renderer.Renderer;

import java.util.List;
import java.util.Map;

public class Main {

    private static final int MAP_ROWS = 10;
    private static final int MAP_COLUMNS = 10;

    private static final double CREATURES_DENSITY_FACTOR = 0.04;
    private static final double STATIC_OBJECTS_DENSITY_FACTOR = 0.15;

    private static final double WEIGHT_ROCK = 0.3;
    private static final double WEIGHT_GRASS = 0.4;
    private static final double WEIGHT_TREE = 0.3;
    private static final double WEIGHT_HERBIVORE = 0.6;
    private static final double WEIGHT_PREDATOR = 0.4;

    public static void main(String[] args) {
        WorldMap map = new WorldMap(MAP_ROWS, MAP_COLUMNS);
        Renderer renderer = new ConsoleRenderer();

        Simulation simulation = getSimulation(map, renderer);
        simulation.start();
    }

    private static Simulation getSimulation(WorldMap map, Renderer renderer) {
        List<Action> initActions = getInitActions();
        List<Action> turnActions = List.of(
                new MoveAction()
        );

        return new Simulation(map, renderer, initActions, turnActions);
    }

    private static List<Action> getInitActions() {
        Map<Class<? extends Creature>, Double> creatureTypes = Map.of(
                Herbivore.class, WEIGHT_HERBIVORE,
                Predator.class, WEIGHT_PREDATOR
        );
        Map<Class<? extends Entity>, Double> staticObjectTypes = Map.of(
                Grass.class, WEIGHT_GRASS,
                Rock.class, WEIGHT_ROCK,
                Tree.class, WEIGHT_TREE
        );

        EntityFactory factory = new EntityFactory(staticObjectTypes, creatureTypes);

        return List.of(
                new PlaceStaticObjectsAction(factory, STATIC_OBJECTS_DENSITY_FACTOR),
                new PlaceCreaturesAction(factory, CREATURES_DENSITY_FACTOR)
        );
    }

}
