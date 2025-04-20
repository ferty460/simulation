package org.example;

public final class ConfigValues {

    // ======== MAP ========
    public static final int MIN_MAP_SIZE = 5;
    public static final int MAX_MAP_SIZE = 30;

    public static final int MAP_ROWS = Config.getInt("map.rows", MIN_MAP_SIZE, MAX_MAP_SIZE);
    public static final int MAP_COLUMNS = Config.getInt("map.columns", MIN_MAP_SIZE, MAX_MAP_SIZE);

    // ======== DENSITY FACTOR ========
    public static final double MIN_SPAWN_RATE = 0.0;
    public static final double MAX_SPAWN_RATE = 1.0;

    public static final double CREATURES_DENSITY_FACTOR =
            Config.getDouble("spawn.creatures", MIN_SPAWN_RATE, MAX_SPAWN_RATE);

    public static final double STATIC_OBJECTS_DENSITY_FACTOR =
            Config.getDouble("spawn.static_objects", MIN_SPAWN_RATE, MAX_SPAWN_RATE);

    public static final double WEIGHT_ROCK =
            Config.getDouble("spawn.rock", MIN_SPAWN_RATE, MAX_SPAWN_RATE);
    public static final double WEIGHT_GRASS =
            Config.getDouble("spawn.grass", MIN_SPAWN_RATE, MAX_SPAWN_RATE);
    public static final double WEIGHT_TREE =
            Config.getDouble("spawn.tree", MIN_SPAWN_RATE, MAX_SPAWN_RATE);
    public static final double WEIGHT_HERBIVORE =
            Config.getDouble("spawn.herbivore", MIN_SPAWN_RATE, MAX_SPAWN_RATE);
    public static final double WEIGHT_PREDATOR =
            Config.getDouble("spawn.predator", MIN_SPAWN_RATE, MAX_SPAWN_RATE);

    // ======== CREATURE ========
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 5;
    public static final int MIN_HEALTH = 1;
    public static final int MAX_HEALTH = 1000;
    public static final int MIN_ENERGY_CONSUMPTION = 0;
    public static final int MAX_ENERGY_CONSUMPTION = 200;

    // ======== HERBIVORE ========
    public static final int MIN_NUTRITIONAL_VALUE = 0;
    public static final int MAX_NUTRITIONAL_VALUE = 200;

    // ======== PREDATOR ========
    public static final int MIN_ATTACK = 0;
    public static final int MAX_ATTACK = 200;

    // ======== LOGGING ========
    public static final boolean LOGGING_IS_ENABLED = Config.getBoolean("logging.enabled");

    private ConfigValues() {
        throw new UnsupportedOperationException("Utility class");
    }

}

