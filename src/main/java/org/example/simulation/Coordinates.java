package org.example.simulation;

public record Coordinates(int row, int column) {

    @Override
    public String toString() {
        return "(r: " + row + ", c: " + column + ")";
    }

}
