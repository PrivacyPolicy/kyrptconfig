package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;


import net.kyrptonaught.kyrptconfig.TagHelper;
import net.minecraft.IdentifierException;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import java.util.List;

public class ItemIconEntry extends IconEntry<Item> {
    public ItemIconEntry(String value, boolean allowTags) {
        super(value, allowTags);
    }

    @Override
    public ItemLike getItemToRender(float delta) {
        try {
            String entered = getValue();
            if (entered == null) return Items.BARRIER;

            if (entered.startsWith("#") && allowTags) {
                entered = entered.replaceAll("#", "");
                List<Item> items = TagHelper.getItemsInTag(Identifier.parse(entered));
                if (items.size() > 0)
                    enteredTag = items;
                else enteredTag = null;
                if (enteredTag != null) {
                    tickTags(delta);
                    return enteredTag.get(selectedTag).asItem();
                }
            }
            if (entered.startsWith("#"))
                return Items.BARRIER;
            return BuiltInRegistries.ITEM.getOptional(Identifier.parse(entered)).orElse(Items.BARRIER);
        } catch (IdentifierException ignored) {
        }
        return Items.BARRIER;
    }
}
