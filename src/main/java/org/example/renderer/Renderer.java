package org.example.renderer;

import org.example.simulation.WorldMap;

public interface Renderer {

    void render(WorldMap map);

    void printInfo(WorldMap worldMap);

    void printCurrentTurn(int turn);

}
