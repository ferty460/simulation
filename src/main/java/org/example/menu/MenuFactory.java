package org.example.menu;

import org.example.simulation.Simulation;

public final class MenuFactory {

    private MenuFactory() {
    }

    public static Menu createMainMenu(Runnable onStart, Runnable onSettings, Runnable onExit, Menu menu) {
        menu.setTitle("Main Menu");

        menu.addItem(1, "Start simulation", onStart);
        menu.addItem(2, "Settings", onSettings);
        menu.addItem(3, "Exit", onExit);

        return menu;
    }

    public static Menu createSimulationMenu(Simulation simulation, Menu menu) {
        menu.setTitle("Simulation menu");

        menu.addItem(1, "Next tick", simulation::process);
        menu.addItem(2, "Infinity", simulation::startInfiniteSimulation);
        menu.addItem(3, "Info", simulation::getInfo);
        menu.addItem(4, "Exit", simulation::stop);

        return menu;
    }

}
