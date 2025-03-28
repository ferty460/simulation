package org.example;

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

    public boolean isEmptyBlock(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public List<Coordinates> getEmptyBlocks() {
        return IntStream.range(0, height)
                .boxed()
                .flatMap(row -> IntStream.range(0, width)
                        .mapToObj(col -> new Coordinates(row, col)))
                .filter(this::isEmptyBlock)
                .collect(Collectors.toList());
    }

    public Optional<Entity> getEntity(Coordinates coordinates) {
        return Optional.ofNullable(entities.get(coordinates));
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
