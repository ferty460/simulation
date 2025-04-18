package org.example.simulation;

public record Coordinates(int row, int column) {

    @Override
    public String toString() {
        return "(r: " + (row + 1) + ", c: " + (column + 1) + ")";
    }

}
