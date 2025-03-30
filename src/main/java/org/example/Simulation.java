package org.example;

import org.example.action.Action;
import org.example.menu.Menu;
import org.example.menu.MenuFactory;
import org.example.renderer.Renderer;

import java.util.List;
import java.util.Scanner;

public class Simulation {

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final List<Action> initActions;

    private final Menu menu;
    private final Scanner scanner;

    private boolean isRunning;

    public Simulation(WorldMap worldMap, Renderer renderer, List<Action> initActions) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        this.initActions = initActions;
        this.menu = new MenuFactory().createSimulationMenu(this);
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    public void start() {
        preload();
        renderer.render(worldMap);

        while (isRunning) {
            menu.show();

            // todo: validate
            int choice = scanner.nextInt();
            menu.execute(choice);
        }
    }

    public void process() {
        renderer.render(worldMap);
    }

    public void stop() {
        isRunning = false;
    }

    private void preload() {
        for (Action action : initActions) {
            action.execute(worldMap);
        }
    }

}
