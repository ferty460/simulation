package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "config.properties";

    public Config() {
        throw new UnsupportedOperationException("Utility class");
    }

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                PROPERTIES.load(input);
            } else {
                throw new RuntimeException(PROPERTIES_FILE + " not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + PROPERTIES_FILE, e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static int getInt(String key, int min, int max) {
        String value = get(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        try {
            int intValue = Integer.parseInt(value);
            if (intValue < min || intValue > max) {
                throw new IllegalArgumentException("Config key '" + key + "' is out of bounds: " + intValue);
            }
            return intValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Config key '" + key + "' has invalid int format: " + value);
        }
    }

    public static double getDouble(String key, double min, double max) {
        String value = get(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        try {
            double doubleValue = Double.parseDouble(value);
            if (doubleValue < min || doubleValue > max) {
                throw new IllegalArgumentException("Config key '" + key + "' is out of bounds: " + doubleValue);
            }
            return doubleValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Config key '" + key + "' has invalid double format: " + value);
        }
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

}
