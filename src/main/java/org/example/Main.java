package org.example;

import org.example.action.Action;
import org.example.action.turn.MoveAction;
import org.example.action.init.PlaceCreaturesAction;
import org.example.action.init.PlaceStaticObjectsAction;
import org.example.entity.Entity;
import org.example.entity.EntityFactory;
import org.example.entity.creature.Creature;
import org.example.entity.static_object.Grass;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.static_object.Rock;
import org.example.entity.static_object.Tree;
import org.example.renderer.ConsoleRenderer;
import org.example.renderer.Renderer;

import java.util.List;

public class Main {

    private static final int MAP_ROWS = 10;
    private static final int MAP_COLUMNS = 10;

    public static void main(String[] args) {
        WorldMap map = new WorldMap(MAP_ROWS, MAP_COLUMNS);
        Renderer renderer = new ConsoleRenderer();

        Simulation simulation = getSimulation(map, renderer);
        simulation.start();
    }

    private static Simulation getSimulation(WorldMap map, Renderer renderer) {
        List<Class<? extends Creature>> creatureTypes = List.of(
                Herbivore.class,
                Predator.class
        );
        List<Class<? extends Entity>> staticObjectTypes = List.of(
                Grass.class,
                Rock.class,
                Tree.class
        );
        EntityFactory factory = new EntityFactory(staticObjectTypes, creatureTypes);

        List<Action> initActions = List.of(
                new PlaceStaticObjectsAction(factory),
                new PlaceCreaturesAction(factory)
        );
        List<Action> turnActions = List.of(
                new MoveAction()
        );

        return new Simulation(map, renderer, initActions, turnActions);
    }

}
