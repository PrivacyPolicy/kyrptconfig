package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class ListStringEntry extends ConfigItem<String> {
    boolean deleted = false;
    EditBox valueEntry;
    protected NotSuckyButton delButton;

    public ListStringEntry(String value) {
        super(Component.literal(""), value, value);
        valueEntry = new EditBox(Minecraft.getInstance().font, 0, 0, 125, 18, Component.literal("Text Entry"));
        valueEntry.setMaxLength(256);
        valueEntry.setValue(value);
        this.delButton = new NotSuckyButton(0, 0, 35, 20, Component.translatable("key.kyrptconfig.config.delete"), widget -> {
            setDeleted(true);
        });
    }

    public void setDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
        if (deleted) valueEntry.setValue("");
    }

    public String getValue() {
        if (deleted || valueEntry.getValue() == null) return null;
        if (valueEntry.getValue().isEmpty() || valueEntry.getValue().isBlank()) return null;
        return valueEntry.getValue();
    }

    @Override
    public int getContentSize() {
        if (deleted) return -23;
        return super.getContentSize();
    }

    @Override
    public boolean keyPressed(KeyEvent input) {
        super.keyPressed(input);
        if (deleted) return false;
        return valueEntry.keyPressed(input);
    }

    @Override
    public boolean charTyped(CharacterEvent input) {
        if (deleted) return false;
        return valueEntry.charTyped(input);
    }

    @Override
    public void mouseClicked(MouseButtonEvent click, boolean doubled) {
        super.mouseClicked(click, doubled);
        if (deleted) return;
        delButton.mouseClicked(click, doubled);
        valueEntry.setFocused(valueEntry.mouseClicked(click, doubled));
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        if (deleted) return;
        super.render(context, x, y, mouseX, mouseY, delta);
        this.delButton.setY(y);
        this.delButton.setX(Minecraft.getInstance().getWindow().getGuiScaledWidth() - delButton.getWidth() - 20);
        this.delButton.render(context, mouseX, mouseY, delta);

        if (valueEntry.isFocused())
            this.valueEntry.setWidth(175);
        else
            this.valueEntry.setWidth(125);

        this.valueEntry.setY(y + 1);
        this.valueEntry.setX(delButton.getX() - (valueEntry.getWidth()) - 7);
        this.valueEntry.render(context, mouseX, mouseY, delta);
    }
}