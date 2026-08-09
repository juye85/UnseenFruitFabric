package thy.unseenfruit.item;

import thy.unseenfruit.UnseenFruitMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class UnseenFruitItem extends Item {
    private static final Random RANDOM = new Random();
    private static final int EFFECT_DURATION = 600; // 30 seconds (ticks)
    private static final float SINGLE_EYE_CHANCE = 0.05f; // 5% chance for single-eye infection

    public UnseenFruitItem(Settings settings) {
        super(settings.food(new FoodComponent.Builder()
                .hunger(2)
                .saturationModifier(0.2f)
                .alwaysEdible()
                .snack()
                .build()));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;

            // 5% chance: only one eye infected (amplifier = 1)
            boolean singleEye = RANDOM.nextFloat() < SINGLE_EYE_CHANCE;
            int amplifier = singleEye ? 1 : 0;

            StatusEffectInstance effect = new StatusEffectInstance(
                    UnseenFruitMod.UNSEEN_EYE_EFFECT,
                    EFFECT_DURATION,
                    amplifier,
                    false, // ambient
                    true,  // show particles
                    true   // show icon
            );

            player.addStatusEffect(effect);
        }

        return super.finishUsing(stack, world, user);
    }
}
