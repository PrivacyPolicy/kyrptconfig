package net.kyrptonaught.kyrptconfig.mixin.nonConflicting;

import net.kyrptonaught.kyrptconfig.config.NonConflicting.NonConflictingKeyBinding;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = KeyBindsList.KeyEntry.class, priority = -1)
public class ControlListWidgetMixin {

    @Redirect(method = "refreshEntry", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;same(Lnet/minecraft/client/KeyMapping;)Z"), require = 0)
    public boolean dontConflict(KeyMapping instance, KeyMapping other) {
        if (instance instanceof NonConflictingKeyBinding || other instanceof NonConflictingKeyBinding)
            return false;
        return instance.same(other);
    }
}
