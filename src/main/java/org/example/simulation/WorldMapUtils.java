package org.example.simulation;

import org.example.entity.Entity;
import org.example.entity.creature.Creature;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class WorldMapUtils {

    private WorldMapUtils() {
    }

    public static List<Creature> getCreatures(WorldMap worldMap) {
        return worldMap.getEntitiesMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
    }

    private static List<Coordinates> getEmptyBlocks(WorldMap worldMap) {
        return IntStream.range(0, worldMap.getHeight())
                .boxed()
                .flatMap(row -> IntStream.range(0, worldMap.getWidth())
                        .mapToObj(col -> new Coordinates(row, col)))
                .filter(coords -> isEmptyBlock(coords, worldMap))
                .collect(Collectors.toList());
    }

    public static void putEntityAtRandom(Entity entity, WorldMap worldMap) {
        Random random = new Random();
        int randomBlock = random.nextInt(getEmptyBlocks(worldMap).size());
        Coordinates coordinates = getEmptyBlocks(worldMap).get(randomBlock);

        worldMap.putEntityAt(coordinates, entity);
    }

    public static boolean isEmptyBlock(Coordinates coordinates, WorldMap worldMap) {
        return !worldMap.getEntitiesMap().containsKey(coordinates);
    }

    public static boolean isWithinBounds(Coordinates coordinates, WorldMap worldMap) {
        int row = coordinates.row();
        int col = coordinates.column();
        return row >= 0 && row < worldMap.getHeight() && col >= 0 && col < worldMap.getWidth();
    }

}
