package org.example.menu;

import org.example.Application;
import org.example.simulation.Simulation;

public final class MenuFactory {

    private static final String START_SIMULATION = "1";
    private static final String EXIT_SIMULATION = "2";

    private static final String NEXT_TICK = "1";
    private static final String INFINITY = "2";
    private static final String INFO = "3";
    private static final String EXIT = "4";

    private MenuFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Menu createMainMenu(Application application, Menu menu) {
        menu.setTitle("Main Menu");

        menu.addItem(START_SIMULATION, "Start simulation", application::startSimulation);
        menu.addItem(EXIT_SIMULATION, "Exit", application::exit);

        return menu;
    }

    public static Menu createSimulationMenu(Simulation simulation, Menu menu) {
        menu.setTitle("Simulation menu");

        menu.addItem(NEXT_TICK, "Next tick", simulation::process);
        menu.addItem(INFINITY, "Infinity", simulation::startInfiniteSimulation);
        menu.addItem(INFO, "Info", simulation::getInfo);
        menu.addItem(EXIT, "Exit", simulation::stop);

        return menu;
    }

}
