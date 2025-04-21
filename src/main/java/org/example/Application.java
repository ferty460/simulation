package org.example;

import org.example.ui.menu.ConsoleMenu;
import org.example.ui.menu.Menu;
import org.example.ui.menu.MenuFactory;
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
                    this,
                    new ConsoleMenu()
            );

            mainMenu.show();

            String choice = scanner.nextLine();
            mainMenu.execute(choice);
        }
    }

    public void startSimulation() {
        Simulation simulation = SimulationFactory.create();
        simulation.start();
    }

    public void exit() {
        isRunning = false;
    }

}
