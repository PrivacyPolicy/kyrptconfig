package net.kyrptonaught.kyrptconfig.config.NonConflicting;

import net.kyrptonaught.kyrptconfig.keybinding.CustomKeyBinding;
import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import java.util.function.Consumer;

public class NonConflictingKeyBinding extends DisplayOnlyKeyBind {

    public NonConflictingKeyBinding(String name, InputConstants.Type type, int code, KeyMapping.Category category) {
        super(name, type, code, category);
    }

    public NonConflictingKeyBinding(String translationKey, KeyMapping.Category category, CustomKeyBinding customKeyBinding, Consumer<InputConstants.Key> keySet) {
        super(translationKey, category, customKeyBinding, keySet);
    }
}