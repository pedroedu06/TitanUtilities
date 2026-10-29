package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.recipe.TitanCrafterRecipe;
import com.titanmods.titanutilities.recipe.TitanFactoryRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TitanUtilities.MOD_ID);

    // Titan Factory machine recipes (data/<namespace>/recipes, "type": "titanutilities:titan_factory").
    public static final IRecipeType<TitanFactoryRecipe> TITAN_FACTORY_TYPE = IRecipeType.register(TitanUtilities.MOD_ID + ":titan_factory");

    public static final RegistryObject<TitanFactoryRecipe.Serializer> TITAN_FACTORY_SERIALIZER =
            SERIALIZERS.register("titan_factory", TitanFactoryRecipe.Serializer::new);

    // Titan Crafter machine recipes ("type": "titanutilities:titan_crafting", fixed 9x7 pattern).
    public static final IRecipeType<TitanCrafterRecipe> TITAN_CRAFTING_TYPE = IRecipeType.register(TitanUtilities.MOD_ID + ":titan_crafting");

    public static final RegistryObject<TitanCrafterRecipe.Serializer> TITAN_CRAFTING_SERIALIZER =
            SERIALIZERS.register("titan_crafting", TitanCrafterRecipe.Serializer::new);
}
