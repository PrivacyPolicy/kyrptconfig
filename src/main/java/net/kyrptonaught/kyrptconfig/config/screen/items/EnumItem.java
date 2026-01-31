package net.kyrptonaught.kyrptconfig.config.screen.items;

import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class EnumItem<T extends Enum<?>> extends ConfigItem<T> {
    private final NotSuckyButton displayWidget;
    T[] enumValues;

    public EnumItem(Component name, T[] enums, T value, T defaultValue) {
        super(name, value, defaultValue);
        this.enumValues = enums;
        this.displayWidget = new NotSuckyButton(0, 0, 100, 20, Component.literal("EnumButton"), widget -> {
            cycleSelectedValue();
        });
        setValue(value);
        useDefaultResetBTN();
    }


    @Override
    public void setValue(T value) {
        super.setValue(value);
        displayWidget.setMessage(Component.literal(value.toString()));
    }

    public void cycleSelectedValue() {
        int selected = 0;
        for (int i = 0; i < enumValues.length; i++)
            if (enumValues[i].equals(value)) {
                selected = i;
                break;
            }
        selected++;
        if (selected >= enumValues.length)
            selected = 0;
        setValue(enumValues[selected]);
    }

    @Override
    public void mouseClicked(MouseButtonEvent click, boolean doubled) {
        super.mouseClicked(click, doubled);
        displayWidget.mouseClicked(click, doubled);
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        this.displayWidget.setY(y);
        this.displayWidget.setX(resetButton.getX() - resetButton.getWidth() - (displayWidget.getWidth() / 2) - 20);

        displayWidget.render(context, mouseX, mouseY, delta);
    }
}
