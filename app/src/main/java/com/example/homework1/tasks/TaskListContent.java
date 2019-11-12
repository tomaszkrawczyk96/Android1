package com.example.homework1.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Item " + position, makeDetails(position),"1.01.2001","111111111");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static void removeItem(int currentItemPosition) {
        String itemId = ITEMS.get(currentItemPosition).id;
        ITEMS.remove(currentItemPosition);
        ITEM_MAP.remove(itemId);
    }


    public static class Task {
        public final String id;
        public final String name;
        public final String surname;
        public final String birthday;
        public final String phone;
        public final String sPath;

        public Task(String id, String name, String surname, String birthday, String phone) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.phone = phone;
            this.sPath = "";
        }
        public Task(String id, String name, String surname, String birthday, String phone, String sPath) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.phone = phone;
            this.sPath = sPath;
        }


        @Override
        public String toString() {
            return name;
        }
    }
}
