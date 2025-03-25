package org.example;

public class Map {

    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEmptyBlock(Coordinates coordinates) {
        return true; // todo
    }

    public Entity getEntity(Coordinates coordinates) {
        return null; // todo
    }
}
