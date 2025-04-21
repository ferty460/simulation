package org.example.simulation;

import org.example.action.Action;
import org.example.ui.menu.ConsoleMenu;
import org.example.ui.menu.Menu;
import org.example.ui.menu.MenuFactory;
import org.example.ui.renderer.Renderer;

import java.util.List;
import java.util.Scanner;

public class Simulation {

    private static final int INITIAL_TURN = 1;
    private static final int DELAY = 1000;

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final Menu menu;
    private final Scanner scanner;

    private int currentTurn = INITIAL_TURN;
    private boolean isRunning;

    public Simulation(WorldMap worldMap, Renderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.menu = MenuFactory.createSimulationMenu(this, new ConsoleMenu());
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    public void start() {
        preload();
        renderer.render(worldMap);
        renderer.printCurrentTurn(currentTurn++);

        isRunning = true;

        while (isRunning) {
            menu.show();

            String choice = scanner.nextLine();
            menu.execute(choice);
        }
    }

    public void startInfiniteSimulation() {
        Thread infiniteSimulation = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                process();
                System.out.println("Press Enter to stop...");

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        infiniteSimulation.start();

        scanner.nextLine();

        infiniteSimulation.interrupt();
    }

    private void preload() {
        for (Action action : initActions) {
            action.execute(worldMap);
        }
    }

    public void process() {
        for (Action action : turnActions) {
            action.execute(worldMap);
        }

        renderer.render(worldMap);
        renderer.printCurrentTurn(currentTurn++);
    }

    public void getInfo() {
        renderer.printInfo(worldMap);
    }

    public void stop() {
        isRunning = false;
    }

}
