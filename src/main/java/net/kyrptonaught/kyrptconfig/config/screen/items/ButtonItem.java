package net.kyrptonaught.kyrptconfig.config.screen.items;

import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class ButtonItem extends ConfigItem {
    private final NotSuckyButton button;
    Runnable clickEvent;

    public ButtonItem(Component name) {
        super(name, null, null);
        this.button = new NotSuckyButton(0, 0, 100, 20, name, widget -> clickEvent.run());
    }

    public ButtonItem setClickEvent(Runnable clickEvent) {
        this.clickEvent = clickEvent;
        return this;
    }

    public void mouseClicked(MouseButtonEvent click, boolean doubled) {
        super.mouseClicked(click, doubled);
        this.button.mouseClicked(click, doubled);
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        this.button.setY(y);
        this.button.setX(Minecraft.getInstance().getWindow().getGuiScaledWidth() - button.getWidth() - 40);

        button.render(context, mouseX, mouseY, delta);
    }
}
