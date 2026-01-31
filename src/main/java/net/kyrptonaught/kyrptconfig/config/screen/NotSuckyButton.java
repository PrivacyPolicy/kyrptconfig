package net.kyrptonaught.kyrptconfig.config.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.util.CommonColors;

public class NotSuckyButton extends Button.Plain {
    int buttonColor = CommonColors.WHITE;
    public boolean disableHover = false;

    public NotSuckyButton(int x, int y, int width, int height, net.minecraft.network.chat.Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
    }

    public boolean detectHover(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }

    @Override
    public net.minecraft.network.chat.Component getMessage() {
        net.minecraft.network.chat.Component message = super.getMessage();
        if (this.active) message = message.copy().withColor(buttonColor);
        return message;
    }

    @Override
    public boolean isHovered() {
        return !disableHover && super.isHovered();
    }
}
