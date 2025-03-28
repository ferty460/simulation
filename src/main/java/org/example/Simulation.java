package org.example;

import org.example.action.Action;
import org.example.renderer.Renderer;

import java.util.List;

public class Simulation {

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final List<Action> initActions;

    public Simulation(WorldMap worldMap, Renderer renderer, List<Action> initActions) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        this.initActions = initActions;
    }

    public void start() {
        for (Action action : initActions) {
            action.execute(worldMap);
        }

        renderer.render(worldMap);
    }

}
