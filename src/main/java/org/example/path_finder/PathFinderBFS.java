package org.example.path_finder;

import org.example.simulation.Coordinates;
import org.example.simulation.WorldMap;
import org.example.entity.Entity;
import org.example.simulation.WorldMapUtils;

import java.util.*;
import java.util.function.Predicate;

public class PathFinderBFS implements PathFinder {

    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;

    private static final int[][] DIRECTIONS = {
            {-1, 0},  // TOP
            {1, 0},   // BOTTOM
            {0, -1},  // LEFT
            {0, 1},   // RIGHT
            {-1, -1}, // TOP-LEFT
            {1, -1},  // BOTTOM-LEFT
            {-1, 1},  // TOP-RIGHT
            {1, 1}    // BOTTOM-RIGHT
    };

    @Override
    public List<Coordinates> find(
            WorldMap worldMap,
            Coordinates start,
            Predicate<Entity> targetCondition
    ) {
        Queue<List<Coordinates>> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(List.of(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<Coordinates> path = queue.poll();
            Coordinates current = path.get(path.size() - 1);

            for (int[] dir : DIRECTIONS) {
                Coordinates neighbor = new Coordinates(
                        current.row() + dir[ROW_INDEX],
                        current.column() + dir[COLUMN_INDEX]
                );

                if (!WorldMapUtils.isWithinBounds(neighbor, worldMap) || visited.contains(neighbor)) {
                    continue;
                }

                Optional<Entity> entityOpt = worldMap.getEntityAt(neighbor);

                if (entityOpt.isPresent()) {
                    if (targetCondition.test(entityOpt.get())) {
                        List<Coordinates> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        return newPath;
                    }
                    continue;
                }

                visited.add(neighbor);
                List<Coordinates> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.add(newPath);
            }
        }

        return Collections.emptyList();
    }

}
