package org.example.ui.menu;

import java.util.HashMap;
import java.util.Map;

public class ConsoleMenu implements Menu {

    private final Map<String, MenuItem> items;
    private String title;

    public ConsoleMenu() {
        this.items = new HashMap<>();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void addItem(String key, String description, Runnable action) {
        items.put(key, new MenuItem(description, action));
    }

    @Override
    public void show() {
        System.out.println(title);
        for (Map.Entry<String, MenuItem> entry : items.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().label());
        }
    }

    @Override
    public void execute(String choice) {
        if (items.containsKey(choice)) {
            items.get(choice).action().run();
        } else {
            System.out.println("Wrong input.");
        }
    }

    private record MenuItem(String label, Runnable action) {}

}
