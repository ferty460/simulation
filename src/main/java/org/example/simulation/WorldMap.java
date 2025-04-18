package org.example.simulation;

import org.example.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<Entity> getAllEntities() {
        return List.copyOf(entities.values());
    }

    public void putEntityAt(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public void putEntityAtRandomCoordinates(Entity entity) {
        Random random = new Random();
        int randomBlock = random.nextInt(getEmptyBlocks().size());
        Coordinates coordinates = getEmptyBlocks().get(randomBlock);

        putEntityAt(coordinates, entity);
    }

    private List<Coordinates> getEmptyBlocks() {
        return IntStream.range(0, height)
                .boxed()
                .flatMap(row -> IntStream.range(0, width)
                        .mapToObj(col -> new Coordinates(row, col)))
                .filter(this::isEmptyBlock)
                .collect(Collectors.toList());
    }

    public void removeEntityAt(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isEmptyBlock(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public boolean isWithinBounds(Coordinates coordinates) {
        int row = coordinates.row();
        int col = coordinates.column();
        return row >= 0 && row < height && col >= 0 && col < width;
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
