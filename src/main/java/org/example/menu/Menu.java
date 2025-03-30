package org.example.menu;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private final Map<Integer, MenuItem> items;
    private final String title;

    public Menu(String title) {
        this.items = new HashMap<>();
        this.title = title;
    }

    public void addItem(int key, String description, Runnable action) {
        items.put(key, new MenuItem(description, action));
    }

    public void show() {
        System.out.println(title);
        for (Map.Entry<Integer, MenuItem> entry : items.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().label());
        }
    }

    public void execute(int choice) {
        if (items.containsKey(choice)) {
            items.get(choice).action().run();
        } else {
            System.out.println("Wrong input. Try again.");
        }
    }

    private record MenuItem(String label, Runnable action) {}

}
