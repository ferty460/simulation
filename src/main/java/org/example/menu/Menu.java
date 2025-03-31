package org.example.menu;

public interface Menu {

    void setTitle(String title);
    void addItem(int key, String description, Runnable action);
    void show();
    void execute(int choice);

}
