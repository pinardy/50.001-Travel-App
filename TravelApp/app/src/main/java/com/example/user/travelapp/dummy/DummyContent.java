package com.example.user.travelapp.dummy;

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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add some sample items.
        addItem(new DummyItem("1", "Buddha Tooth Relic Temple", "https://www.wikipedia.org/wiki/Buddha_Tooth_Relic_Temple_and_Museum"));
        addItem(new DummyItem("2", "Marina Bay Sands", "https://www.wikipedia.org/wiki/Marina_Bay_Sands"));
        addItem(new DummyItem("3", "Resorts World Sentosa", "https://www.wikipedia.org/wiki/Resorts_World_Sentosa"));
        addItem(new DummyItem("4", "Singapore Flyer", "https://www.wikipedia.org/wiki/Singapore_Flyer"));
        addItem(new DummyItem("5", "Singapore Zoo", "https://www.wikipedia.org/wiki/Singapore_Zoo"));
        addItem(new DummyItem("6", "VivoCity", "https://www.wikipedia.org/wiki/VivoCity"));

        }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String website_name;
        public final String website_url;

        public DummyItem(String id, String website_name, String website_url) {
            this.id = id;
            this.website_name = website_name;
            this.website_url = website_url;
        }

        @Override
        public String toString() {
            return website_name;
        }
    }
}
