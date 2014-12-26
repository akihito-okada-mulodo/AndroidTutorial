package com.tutorial.okadaakihito.androidtutorial.common;

import android.content.Context;
import android.content.res.Resources;

import com.tutorial.okadaakihito.androidtutorial.R;
import com.tutorial.okadaakihito.androidtutorial.model.ApplicationController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenuContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ListItem> ITEMS = new ArrayList<ListItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ListItem> ITEM_MAP = new HashMap<String, ListItem>();

    static {
        // Add 3 sample items.
        Context context = ApplicationController.getInstance().getApplicationContext();
        Resources resources = context.getResources();
        addItem(new ListItem("1", resources.getString(R.string.tutorial_1)));
        addItem(new ListItem("2", resources.getString(R.string.tutorial_2)));
        //addItem(new ListItem("3", "Item 3"));
    }

    private static void addItem(ListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ListItem {
        public String id;
        public String content;

        public ListItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
