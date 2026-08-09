package thy.unseenfruit;

import thy.unseenfruit.block.UnseenCropBlock;
import thy.unseenfruit.effect.UnseenEyeEffect;
import thy.unseenfruit.event.ServerPlayerCallback;
import thy.unseenfruit.item.UnseenFruitItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class UnseenFruitMod implements ModInitializer {
    public static final String MOD_ID = "unseenfruit";

    // Crop block
    public static final UnseenCropBlock UNSEEN_CROP = new UnseenCropBlock(
            Settings.of(Material.PLANT)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP));

    // Seeds item (AliasedBlockItem links it to the crop block)
    public static final Item UNSEEN_SEEDS = new net.minecraft.item.AliasedBlockItem(
            UNSEEN_CROP,
            new Item.Settings().group(ItemGroup.MISC));

    // Fruit item
    public static final Item UNSEEN_FRUIT = new UnseenFruitItem(
            new Item.Settings().group(ItemGroup.FOOD).maxCount(64));

    // Status effect
    public static final StatusEffect UNSEEN_EYE_EFFECT = new UnseenEyeEffect();

    @Override
    public void onInitialize() {
        // Register crop block
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "unseen_crop"), UNSEEN_CROP);

        // Register items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "unseen_seeds"), UNSEEN_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "unseen_fruit"), UNSEEN_FRUIT);

        // Register status effect
        Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "unseen_eye"), UNSEEN_EYE_EFFECT);

        // Register server-side event callbacks
        ServerPlayerCallback.register();
    }
}
