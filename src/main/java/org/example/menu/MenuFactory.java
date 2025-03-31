package org.example.menu;

import org.example.Simulation;

public class MenuFactory {

    public static Menu createSimulationMenu(Simulation simulation, Menu menu) {
        menu.setTitle("Simulation menu");

        menu.addItem(1, "Start simulation", simulation::process);
        menu.addItem(2, "Exit", simulation::stop);

        return menu;
    }

}
