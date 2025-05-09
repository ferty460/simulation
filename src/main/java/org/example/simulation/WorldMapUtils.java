package org.example.simulation;

import org.example.Logger;
import org.example.entity.Entity;
import org.example.entity.creature.Creature;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class WorldMapUtils {

    public static final int MIN_ROW_INDEX = 0;
    public static final int MIN_COLUMN_INDEX = 0;

    private static final int INITIAL_ROW = 0;
    private static final int INITIAL_COL = 0;

    private WorldMapUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<Creature> getCreatures(WorldMap worldMap) {
        return worldMap.getEntitiesMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
    }

    private static List<Coordinates> getEmptyBlocks(WorldMap worldMap) {
        return IntStream.range(INITIAL_ROW, worldMap.getHeight())
                .boxed()
                .flatMap(row -> IntStream.range(INITIAL_COL, worldMap.getWidth())
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
        return row >= MIN_ROW_INDEX &&
                row < worldMap.getHeight() &&
                col >= MIN_COLUMN_INDEX &&
                col < worldMap.getWidth();
    }

    public static void handleDeath(Creature creature, WorldMap map) {
        Optional<Coordinates> coordsOpt = map.getCoordinatesOfEntity(creature);
        if (coordsOpt.isEmpty()) return;

        Coordinates coords = coordsOpt.get();
        map.removeEntityAt(coords);

        Logger.info(String.format("%s at %s died.", creature.getClass().getSimpleName(), coords));
    }

}
