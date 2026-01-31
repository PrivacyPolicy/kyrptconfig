package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class NumberItem<T extends Number> extends ConfigItem<T> {

    protected T min, max;
    EditBox valueEntry;
    boolean lastInputFixed = false;

    public NumberItem(Component name, T value, T defaultValue) {
        super(name, value, defaultValue);
        useDefaultResetBTN();
        valueEntry = new EditBox(Minecraft.getInstance().font, 0, 0, 96, 17, Component.literal("Number Entry"));
        valueEntry.setValue(value.toString());
        valueEntry.setResponder(this::onTyped);
    }

    public NumberItem setMinMax(T min, T max) {
        this.min = min;
        this.max = max;
        valueEntry.setTooltip(Tooltip.create(Component.literal(min + " - " + max)));
        return this;
    }

    @Override
    public void setValue(T value) {
        valueEntry.setValue(fixInput(value).toString());
    }

    public T fixInput(T value) {
        BigDecimal big = new BigDecimal(value.toString());
        if (min != null && big.compareTo(new BigDecimal(min.toString())) < 0)
            return min;
        if (max != null && big.compareTo(new BigDecimal(max.toString())) > 0)
            return max;

        return value;
    }

    public abstract T parseValue(String value);

    public void onTyped(String s) {
        boolean isValid = isValid(s);
        if (isValid) {
            valueEntry.setTextColor(0xE0E0E0E0);
        } else {
            valueEntry.setTextColor(CommonColors.RED);
        }
        lastInputFixed = false;
    }

    public boolean isValid(String s) {
        try {
            T parsed = parseValue(s);
            if (Objects.equals(fixInput(parsed), parsed))
                return true;
        } catch (NumberFormatException ignored) {

        }
        return false;
    }

    @Override
    public void tick() {
        if (!valueEntry.isFocused() && !lastInputFixed) {
            fixLastInput();
        }
    }

    @Override
    public void save() {
        fixLastInput();
        super.save();
    }

    public void fixLastInput() {
        valueEntry.setValue(fixInput(parseValue(valueEntry.getValue())).toString());
        value = parseValue(valueEntry.getValue());
        lastInputFixed = true;
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
        this.valueEntry.setY(y + 2);
        this.valueEntry.setX(resetButton.getX() - resetButton.getWidth() - (valueEntry.getWidth() / 2) - 20);

        valueEntry.render(context, mouseX, mouseY, delta);
    }
}