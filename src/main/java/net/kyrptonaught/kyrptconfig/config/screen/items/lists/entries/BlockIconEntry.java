package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import net.kyrptonaught.kyrptconfig.TagHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.List;

public class BlockIconEntry extends IconEntry<Block> {
    public BlockIconEntry(String value, boolean allowTags) {
        super(value, allowTags);
    }

    @Override
    public ItemLike getItemToRender(float delta) {
        try {
            String entered = getValue();

            if (entered == null) return Items.BARRIER;

            if (entered.startsWith("#") && allowTags) {
                entered = entered.replaceAll("#", "");

                List<Block> blocks = TagHelper.getBlocksInTag(Identifier.parse(entered));
                if (blocks.size() > 0)
                    enteredTag = blocks;
                else enteredTag = null;

                if (enteredTag != null) {
                    tickTags(delta);
                    return enteredTag.get(selectedTag).asItem();
                }
            }
            if (entered.startsWith("#"))
                return Items.BARRIER;
            return BuiltInRegistries.BLOCK.getOptional(Identifier.parse(entered)).orElse(Blocks.BARRIER);
        } catch (Exception ignored) {
        }
        return Items.BARRIER;
    }
}