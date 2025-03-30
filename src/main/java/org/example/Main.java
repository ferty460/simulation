package org.example;

import org.example.action.Action;
import org.example.action.init.PlaceCreaturesAction;
import org.example.action.init.PlaceStaticObjectsAction;
import org.example.renderer.ConsoleRenderer;
import org.example.renderer.Renderer;

import java.util.List;

public class Main {

    private static final int MAP_ROWS = 10;
    private static final int MAP_COLUMNS = 10;

    public static void main(String[] args) {
        WorldMap map = new WorldMap(MAP_ROWS, MAP_COLUMNS);
        Renderer renderer = new ConsoleRenderer();

        List<Action> initActions = List.of(
                new PlaceStaticObjectsAction(),
                new PlaceCreaturesAction()
        );

        Simulation simulation = new Simulation(map, renderer, initActions);
        simulation.start();
    }

}