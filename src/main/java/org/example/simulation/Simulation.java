package org.example.simulation;

import org.example.action.Action;
import org.example.menu.ConsoleMenu;
import org.example.menu.Menu;
import org.example.menu.MenuFactory;
import org.example.renderer.Renderer;

import java.util.List;
import java.util.Scanner;

public class Simulation {

    private static final int INITIAL_TURN = 1;

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

            // todo: validate
            int choice = scanner.nextInt();
            menu.execute(choice);
        }
    }

    public void startInfiniteSimulation() {
        Thread infiniteSimulation = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                process();
                System.out.println("Нажмите Enter для остановки...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        infiniteSimulation.start();

        scanner.nextLine();
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        infiniteSimulation.interrupt();

        try {
            infiniteSimulation.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
