package org.example;

public class Logger {

    public enum Type {
        INFO, ERROR
    }

    private static boolean enabled = true;

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static void log(Type type, String message) {
        if (!enabled) return;
        System.out.printf("[%s] %s%n", type, message);
    }

    public static void info(String message) {
        log(Type.INFO, message);
    }

}
