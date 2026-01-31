package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class IconEntry<E> extends ListStringEntry {
    protected boolean allowTags = false;
    protected List<E> enteredTag;
    int selectedTag = 0;
    float deltas;

    public IconEntry(String value, boolean allowTags) {
        super(value);
        this.allowTags = allowTags;
    }

    public ItemLike getItemToRender(float delta) {
        return Items.BARRIER;
    }

    public void tickTags(float delta) {
        deltas += delta;
        if (deltas > 45 && allowTags && enteredTag != null) {
            selectedTag++;
            deltas = 0;
        }
        if (enteredTag == null || selectedTag >= enteredTag.size()) selectedTag = 0;
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        if (deleted) return;
        ItemLike item = getItemToRender(delta);
        context.renderItem(new ItemStack(item), x, y);
    }
}
