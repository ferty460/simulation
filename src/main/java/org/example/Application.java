package org.example;

import org.example.menu.Menu;
import org.example.menu.MenuFactory;
import org.example.simulation.Simulation;
import org.example.simulation.SimulationFactory;

import java.util.Scanner;

public class Application {

    private final Scanner scanner;
    private boolean isRunning;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    public void launch() {
        while (isRunning) {
            Menu mainMenu = MenuFactory.createMainMenu(
                    this::startSimulation,
                    this::openSettings,
                    this::exit
            );

            mainMenu.show();

            // todo: validate
            int choice = scanner.nextInt();
            mainMenu.execute(choice);
        }
    }

    private void startSimulation() {
        Simulation simulation = SimulationFactory.create();
        simulation.start();
    }

    private void openSettings() {
        System.out.println("Settings are not implemented yet.");
    }

    private void exit() {
        isRunning = false;
    }

}
