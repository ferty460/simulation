package org.example.renderer;

import org.example.entity.Entity;
import org.example.entity.creature.Creature;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.static_object.Grass;
import org.example.entity.static_object.Rock;
import org.example.entity.static_object.Tree;
import org.example.simulation.Coordinates;
import org.example.simulation.WorldMap;
import org.example.simulation.WorldMapUtils;

public class ConsoleRenderer implements Renderer {

    private static final String EMPTY_BLOCK = " __ ";

    private static final String TREE_SPRITE = "\uD83C\uDF33";
    private static final String GRASS_SPRITE = "\uD83C\uDF3F";
    private static final String ROCK_SPRITE = "\uD83E\uDEA8";
    private static final String HERBIVORE_SPRITE = "\uD83E\uDD8C";
    private static final String PREDATOR_SPRITE = "\uD83E\uDD81";

    @Override
    public void render(WorldMap worldMap) {
        int width = worldMap.getWidth();
        int height = worldMap.getHeight();

        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder();

            for (int column = 0; column < width; column++) {
                Coordinates coordinates = new Coordinates(row, column);

                if (WorldMapUtils.isEmptyBlock(coordinates, worldMap)) {
                    line.append(EMPTY_BLOCK);
                } else {
                    worldMap.getEntityAt(coordinates).ifPresent(entity ->
                            line.append(getSpriteForEntity(entity))
                    );
                }
            }

            System.out.println(line);
        }
    }

    private String getSpriteForEntity(Entity entity) {
        String entitySprite;

        if (entity instanceof Grass) {
            entitySprite = GRASS_SPRITE;
        } else if (entity instanceof Rock) {
            entitySprite = ROCK_SPRITE;
        } else if (entity instanceof Tree) {
            entitySprite = TREE_SPRITE;
        } else if (entity instanceof Creature creature) {
            entitySprite = getSpriteForCreature(creature);
        } else {
            throw new IllegalArgumentException("Unknown Entity: " + entity.getClass().getSimpleName());
        }

        return String.format(" %s ", entitySprite);
    }

    private String getSpriteForCreature(Creature creature) {
        if (creature instanceof Herbivore) {
            return HERBIVORE_SPRITE;
        } else if (creature instanceof Predator) {
            return PREDATOR_SPRITE;
        } else {
            throw new IllegalArgumentException("Unknown Creature: " + creature);
        }
    }

}
