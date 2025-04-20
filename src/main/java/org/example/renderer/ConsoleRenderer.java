package org.example.renderer;

import org.example.Config;
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

import java.util.List;

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

        printColumnNumbers(width);

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

            System.out.printf("%02d ", row);
            System.out.println(line);
        }

        System.out.println();
    }

    @Override
    public void renderInfo(WorldMap worldMap) {
        List<Creature> creatures = WorldMapUtils.getCreatures(worldMap);
        System.out.println("\nWorld map info:");

        for (Creature creature : creatures) {
            String creatureSprite = getSpriteForEntity(creature);
            int health = creature.getHealth();
            int maxHealth = creature.getMaxHealth();

            worldMap.getCoordinatesOfEntity(creature).ifPresent(coordinates ->
                    System.out.printf("%s at %s - hp: %d/%d\n", creatureSprite, coordinates, health, maxHealth)
            );
        }

        int predatorHungerDamage = Config.getInt("predator.energyConsumption");
        int herbivoreHungerDamage = Config.getInt("herbivore.energyConsumption");

        System.out.printf(
                "\nDamage from hunger by tick:\n%s - %d dmg\n%s - %d dmg\n\n",
                PREDATOR_SPRITE, predatorHungerDamage, HERBIVORE_SPRITE, herbivoreHungerDamage
        );
    }

    private void printColumnNumbers(int width) {
        StringBuilder header = new StringBuilder("\n   ");
        for (int col = 0; col < width; col++) {
            header.append(String.format(" %02d ", col));
        }
        System.out.println(header);
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
