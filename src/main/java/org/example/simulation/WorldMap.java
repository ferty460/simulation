package org.example.simulation;

import org.example.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WorldMap {

    private final int width;
    private final int height;
    private final Map<Coordinates, Entity> entities;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public Optional<Entity> getEntityAt(Coordinates coordinates) {
        return Optional.ofNullable(entities.get(coordinates));
    }

    public Optional<Coordinates> getCoordinatesOfEntity(Entity entity) {
        return entities.entrySet().stream()
                .filter(entry -> entry.getValue().equals(entity))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public Map<Coordinates, Entity> getEntitiesMap() {
        return Map.copyOf(entities);
    }

    public void putEntityAt(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public void removeEntityAt(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }

}
