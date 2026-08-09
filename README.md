# Unseen Fruit - Fabric Mod for Minecraft 1.16.5

## Overview

Unseen Fruit is a Fabric mod that adds a mysterious crop to Minecraft.
Plant the seeds on farmland, grow the crop, harvest the fruit, and eat it...
if you dare.

## Features

- **Unseen Crop**: Plant on farmland, grows through 8 stages (age 0-7)
- **Unseen Fruit**: A pink egg-shaped fruit. Sweet like an apple, melts in your mouth.
- **The Effect**: After eating, you gain the "Unseen Eye" status effect for 30 seconds.
  - While infected, you can ONLY see other players who have also eaten the fruit.
  - All uninfected players become completely invisible to you.
  - 5% chance for single-eye infection (still hides others, but tracked separately).
- **Cure**: The effect wears off after 30 seconds. In multiplayer, rejoining the server also clears it.

## How to Build

1. Make sure you have JDK 8 or 11 installed.
2. Clone/download this project.
3. Run: `./gradlew build`
4. The built jar will be in `build/libs/`.

## How to Run (Development)

`./gradlew runClient`

## Texture Guide

You need to provide these textures (16x16 or higher):

| Path | Description |
|------|-------------|
| `assets/unseenfruit/textures/block/unseen_crop_stage0.png` | Crop stage 0-1 (sprout) |
| `assets/unseenfruit/textures/block/unseen_crop_stage1.png` | Crop stage 2-3 (growing) |
| `assets/unseenfruit/textures/block/unseen_crop_stage2.png` | Crop stage 4-5 (tall) |
| `assets/unseenfruit/textures/block/unseen_crop_stage3.png` | Crop stage 6-7 (mature, with pink fruit) |
| `assets/unseenfruit/textures/item/unseen_seeds.png` | Seeds item texture |
| `assets/unseenfruit/textures/item/unseen_fruit.png` | Fruit item texture (pink egg shape) |
| `assets/unseenfruit/icon.png` | Mod icon (256x256 recommended) |

## Project Structure

```
unseenfruit/
├── fabric.mod.json
├── build.gradle
├── settings.gradle
├── gradle.properties
├── README.md
└── src/main/
    ├── java/thy/unseenfruit/
    │   ├── UnseenFruitMod.java        (main entry)
    │   ├── UnseenFruitClient.java     (client entry)
    │   ├── block/
    │   │   └── UnseenCropBlock.java   (crop block)
    │   ├── item/
    │   │   └── UnseenFruitItem.java   (fruit item + effect logic)
    │   ├── effect/
    │   │   └── UnseenEyeEffect.java   (status effect + visibility logic)
    │   └── mixin/
    │       └── PlayerVisibilityMixin.java (hides players from infected)
    └── resources/
        ├── unseenfruit.mixins.json
        ├── assets/unseenfruit/
        │   ├── blockstates/unseen_crop.json
        │   └── models/
        │       ├── block/unseen_crop_stage0-3.json
        │       └── item/unseen_seeds.json, unseen_fruit.json
        └── data/unseenfruit/
            └── loot_tables/blocks/unseen_crop.json
```

## License

MIT
son
```

## License

MIT
