package net.kyrptonaught.kyrptconfig.config.screen.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class TextItem extends ConfigItem<String> {

    EditBox valueEntry;

    public TextItem(Component name, String value, String defaultValue) {
        super(name, value, defaultValue);
        useDefaultResetBTN();
        valueEntry = new EditBox(Minecraft.getInstance().font, 0, 0, 96, 18, Component.literal("Text Entry"));
        setMaxLength(256);
        valueEntry.setValue(value);
        valueEntry.setResponder(this::setValue);
    }

    public TextItem setMaxLength(int length) {
        valueEntry.setMaxLength(length);
        return this;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    @Override
    public void resetToDefault() {
        setValue(defaultValue);
        valueEntry.setValue(value);
    }

    @Override
    public boolean keyPressed(KeyEvent input) {
        super.keyPressed(input);
        return valueEntry.keyPressed(input);
    }

    @Override
    public boolean charTyped(CharacterEvent input) {
        return valueEntry.charTyped(input);
    }

    @Override
    public void mouseClicked(MouseButtonEvent click, boolean doubled) {
        super.mouseClicked(click, doubled);
        valueEntry.setFocused(valueEntry.mouseClicked(click, doubled));
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);

        if (valueEntry.isFocused())
            this.valueEntry.setWidth(150);
        else
            this.valueEntry.setWidth(96);

        this.valueEntry.setY(y + 1);
        this.valueEntry.setX(resetButton.getX() - (valueEntry.getWidth()) - 7);

        valueEntry.render(context, mouseX, mouseY, delta);
    }
}