package net.kyrptonaught.kyrptconfig.config.NonConflicting;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.function.Consumer;
import net.minecraft.client.KeyMapping;

@Deprecated
public class NonConflictingKeyBindData {
    public String name;
    public KeyMapping.Category category;
    public InputConstants.Type inputType;
    public int keyCode;
    public Consumer<InputConstants.Key> keySetEvent;
    public String defaultKey;

    @Deprecated
    public NonConflictingKeyBindData(String Name, KeyMapping.Category Category, InputConstants.Type type, int KeyCode, Consumer<InputConstants.Key> keySetEvent) {
        this.name = Name;
        this.category = Category;
        this.inputType = type;
        this.keyCode = KeyCode;
        this.keySetEvent = keySetEvent;
    }

    @Deprecated
    public NonConflictingKeyBindData(String Name, KeyMapping.Category Category, InputConstants.Key boundKey, String defaultKey, Consumer<InputConstants.Key> keySetEvent) {
        this(Name, Category, boundKey.getType(), boundKey.getValue(), keySetEvent);
        this.defaultKey = defaultKey;
    }

    @Deprecated
    public InputConstants.Key getDefaultKey() {
        if (defaultKey == null || defaultKey.isEmpty())
            return InputConstants.UNKNOWN;
        try {
            return InputConstants.getKey(defaultKey);
        } catch (IllegalArgumentException e) {
            return InputConstants.UNKNOWN;
        }
    }
}
