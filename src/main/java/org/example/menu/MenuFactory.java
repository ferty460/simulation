package org.example.menu;

import org.example.Simulation;

public class MenuFactory {

    public Menu createSimulationMenu(Simulation simulation) {
        Menu menu = new Menu("Simulation menu");

        menu.addItem(1, "Start simulation", simulation::process);
        menu.addItem(2, "Exit", simulation::stop);

        return menu;
    }

}
