package net.kyrptonaught.kyrptconfig.config.screen.items.lists;

import net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries.ItemIconEntry;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries.ListStringEntry;
import net.minecraft.network.chat.Component;
import java.util.List;

public class ItemIconList extends StringList {
    boolean allowTags = false;

    @Deprecated
    public ItemIconList(Component name, List<String> value, List<String> defaultValue) {
        super(name, value, defaultValue);
        setToolTip();
    }

    public ItemIconList(Component name, List<String> value, List<String> defaultValue, Boolean allowTags) {
        super(name, value, defaultValue, false);
        this.allowTags = allowTags;
        setToolTip();
        populateFromList();
    }

    public void setToolTip() {
        if (allowTags) {
            setToolTip(Component.translatable("key.kyrptconfig.config.hastags"),
                    Component.translatable("key.kyrptconfig.config.tagsdisplay"));
        } else {
            setToolTip(Component.translatable("key.kyrptconfig.config.nothastags"));
        }
    }

    @Deprecated
    public ItemIconList setAllowTags(boolean allowTags) {
        this.allowTags = allowTags;
        setToolTip();
        return this;
    }

    public ListStringEntry createNewEntry(String string) {
        return new ItemIconEntry(string, allowTags);
    }
}
