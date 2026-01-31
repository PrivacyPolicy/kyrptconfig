package net.kyrptonaught.kyrptconfig.config.screen.items;

import com.mojang.blaze3d.platform.InputConstants;
import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.lwjgl.glfw.GLFW;

public class KeybindItem extends ConfigItem<String> {
    private final NotSuckyButton keyButton;
    private Boolean isListening = false;

    public KeybindItem(Component name, String key, String defaultKey) {
        super(name, key, defaultKey);
        this.keyButton = new NotSuckyButton(0, 0, 100, 20, getCleanName(key), widget -> {
            this.isListening = !this.isListening;
            if (!this.isListening) {
                widget.setMessage(this.getCleanName(this.value));
                widget.setTooltip(Tooltip.create(Component.literal(this.value)));
            } else {
                widget.setMessage(Component.literal("> ").append(this.getCleanName(this.value).append(Component.literal(" <"))));
            }
        });
        keyButton.setTooltip(Tooltip.create(Component.literal(this.value)));
        useDefaultResetBTN();
    }

    public void setValue(String value) {
        super.setValue(value);
        isListening = false;
        keyButton.setMessage(this.getCleanName(this.value));
        keyButton.setTooltip(Tooltip.create(Component.literal(this.value)));
    }

    public MutableComponent getCleanName(String str) {
        if (I18n.exists(value))
            return Component.translatable(str);
        if (str == null || str.isBlank() || str.isEmpty())
            return Component.translatable("key.keyboard.unknown");
        return Component.literal(str.substring(str.length() - 1).toUpperCase());
    }

    @Override
    public boolean keyPressed(KeyEvent input) {
        if (isListening) {
            if (input.input() == GLFW.GLFW_KEY_ESCAPE) {
                setValue(value);
                return true;
            }
            setValue(InputConstants.getKey(input).getName());
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseButtonEvent click, boolean doubled) {
        super.mouseClicked(click, doubled);
        boolean handled;
        handled = (keyButton.mouseClicked(click, doubled) || resetButton.mouseClicked(click, doubled));
        if (isListening && !handled) {
            setValue(InputConstants.Type.MOUSE.getOrCreate(click.button()).getName());
        }
    }

    @Override
    public void render(GuiGraphics context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        this.keyButton.setY(y);

        this.keyButton.setX(resetButton.getX() - resetButton.getWidth() - (keyButton.getWidth() / 2) - 20);

        keyButton.render(context, mouseX, mouseY, delta);
    }
}