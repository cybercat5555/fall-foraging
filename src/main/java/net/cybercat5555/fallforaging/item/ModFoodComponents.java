package net.cybercat5555.fallforaging.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CRANBERRY = new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.1f)
            .build();

    public static final FoodComponent PEANUT = new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.1f)
            .build();

    public static final FoodComponent PUMPKIN_SLICE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.3f)
            .build();

    public static final FoodComponent PEPITAS = new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100), 0.75f)
            .build();

    public static final FoodComponent SUNFLOWER_SEEDS = new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.1f)
            .build();

    public static final FoodComponent RAW_SQUIRREL = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300), 0.3f)
            .build();

    public static final FoodComponent COOKED_SQUIRREL = new FoodComponent.Builder().nutrition(5).saturationModifier(0.7f)
            .build();

    public static final FoodComponent ROASTED_ACORN = new FoodComponent.Builder().snack().nutrition(3).saturationModifier(0.3f)
            .build();
}
