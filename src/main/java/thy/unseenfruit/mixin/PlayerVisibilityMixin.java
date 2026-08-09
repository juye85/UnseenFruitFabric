package thy.unseenfruit.mixin;

import thy.unseenfruit.effect.UnseenEyeEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

/**
 * Mixin to hide uninfected players from infected players.
 * Hooks into the visibility check that the server uses to decide
 * whether to send entity updates to a client.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class PlayerVisibilityMixin {

    @Inject(method = "canSee", at = @At("HEAD"), cancellable = true)
    private void unseenfruit_onCanSee(ServerPlayerEntity other, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;

        UUID selfUuid = self.getUuid();
        UUID otherUuid = other.getUuid();

        if (UnseenEyeEffect.isInfected(selfUuid)) {
            // Infected players can only see other infected players
            if (!UnseenEyeEffect.isInfected(otherUuid)) {
                cir.setReturnValue(false);
            }
        }
    }
}
