package net.kyrptonaught.kyrptconfig.mixin.nonConflicting;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.mojang.blaze3d.platform.InputConstants;
import net.kyrptonaught.kyrptconfig.config.NonConflicting.NonConflictingKeyBinding;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(KeyMapping.class)
public class KeyBindingMixin {

    @WrapWithCondition(method = "<init>(Ljava/lang/String;Lcom/mojang/blaze3d/platform/InputConstants$Type;ILnet/minecraft/client/KeyMapping$Category;I)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    public <K, V> boolean dontRegister(Map<K, V> instance, K k, V v) {
        if (((Object) this instanceof NonConflictingKeyBinding))
            return false;

        return true;
    }

    @com.llamalad7.mixinextras.injector.v2.WrapWithCondition(
            method = "<init>(Ljava/lang/String;Lcom/mojang/blaze3d/platform/InputConstants$Type;ILnet/minecraft/client/KeyMapping$Category;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;registerMapping(Lcom/mojang/blaze3d/platform/InputConstants$Key;)V")
    )
    public boolean dontRegisterBinding(KeyMapping keyBinding, InputConstants.Key key) {
        if (((Object) this instanceof NonConflictingKeyBinding))
            return false;

        return true;
    }
}
