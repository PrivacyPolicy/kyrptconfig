package net.kyrptonaught.kyrptconfig;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagHelper {

    public static List<Identifier> getBlockIDsInTag(Identifier blockTagKey) {
        List<Identifier> blocks = new ArrayList<>();
        List<HolderSet.Named<Block>> tags = BuiltInRegistries.BLOCK.getTags().toList();

        for (HolderSet.Named<Block> tagKey : tags) {
            if (tagKey.key().location().equals(blockTagKey)) {
                BuiltInRegistries.BLOCK.getTagOrEmpty(tagKey.key()).forEach(registryEntry -> {
                    registryEntry.unwrapKey().ifPresent(registryEntry2 -> blocks.add(registryEntry2.identifier()));
                });
                break;
            }
        }
        return blocks;
    }

    public static List<Block> getBlocksInTag(Identifier blockTagKey) {
        List<Block> blocks = new ArrayList<>();
        List<HolderSet.Named<Block>> tags = BuiltInRegistries.BLOCK.getTags().toList();

        for (HolderSet.Named<Block> tagKey : tags) {
            if (tagKey.key().location().equals(blockTagKey)) {
                BuiltInRegistries.BLOCK.getTagOrEmpty(tagKey.key()).forEach(registryEntry -> {
                    blocks.add(registryEntry.value());
                });
                break;
            }
        }
        return blocks;
    }

    public static List<Identifier> getItemsIDsInTag(Identifier blockTagKey) {
        List<Identifier> items = new ArrayList<>();
        List<HolderSet.Named<Item>> tags = BuiltInRegistries.ITEM.getTags().toList();

        for (HolderSet.Named<Item> tagKey : tags) {
            if (tagKey.key().location().equals(blockTagKey)) {
                BuiltInRegistries.ITEM.getTagOrEmpty(tagKey.key()).forEach(registryEntry -> {
                    registryEntry.unwrapKey().ifPresent(registryEntry2 -> items.add(registryEntry2.identifier()));
                });
                break;
            }
        }
        return items;
    }

    public static List<Item> getItemsInTag(Identifier blockTagKey) {
        List<Item> items = new ArrayList<>();
        List<HolderSet.Named<Item>> tags = BuiltInRegistries.ITEM.getTags().toList();

        for (HolderSet.Named<Item> tagKey : tags) {
            if (tagKey.key().location().equals(blockTagKey)) {
                BuiltInRegistries.ITEM.getTagOrEmpty(tagKey.key()).forEach(registryEntry -> {
                    items.add(registryEntry.value());
                });
                break;
            }
        }
        return items;
    }
}