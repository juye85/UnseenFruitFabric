package thy.unseenfruit.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UnseenEyeEffect extends StatusEffect {
    // Tracks which players have eaten the fruit (server-side)
    private static final Map<UUID, Boolean> infectedPlayers = new HashMap<>();

    public UnseenEyeEffect() {
        super(StatusEffectType.HARMFUL, 0xFF69B4); // Pink color (0xFF69B4 = hot pink)
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            boolean singleEye = amplifier > 0;
            infectedPlayers.put(player.getUuid(), singleEye);
        }
    }

    @Override
    public void onRemoved(LivingEntity entity) {
        super.onRemoved(entity);
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            infectedPlayers.remove(player.getUuid());
        }
    }

    /**
     * Check if a player should be visible to another player.
     * Infected players can only see other infected players.
     * Uninfected players are invisible to infected players.
     */
    public static boolean shouldPlayerBeVisible(ServerPlayerEntity viewer, ServerPlayerEntity target) {
        boolean viewerInfected = infectedPlayers.containsKey(viewer.getUuid());
        boolean targetInfected = infectedPlayers.containsKey(target.getUuid());

        if (viewerInfected) {
            // Infected viewers can only see other infected players
            return targetInfected;
        }

        // Uninfected viewers see everyone normally
        return true;
    }

    /**
     * Check if a given player is currently infected.
     */
    public static boolean isInfected(UUID playerUuid) {
        return infectedPlayers.containsKey(playerUuid);
    }

    /**
     * Remove a player from the infected list (e.g. on disconnect).
     */
    public static void removePlayer(UUID playerUuid) {
        infectedPlayers.remove(playerUuid);
    }
}
