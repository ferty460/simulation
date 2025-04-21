package org.example.ui.menu;

public interface Menu {

    void setTitle(String title);
    void addItem(String key, String description, Runnable action);
    void show();
    void execute(String choice);

}
