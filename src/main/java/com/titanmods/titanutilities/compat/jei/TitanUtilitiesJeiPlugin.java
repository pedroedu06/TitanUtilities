package com.titanmods.titanutilities.compat.jei;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.block.TitanCrafterTileEntity;
import com.titanmods.titanutilities.block.TitanFactoryTileEntity;
import com.titanmods.titanutilities.client.TitanCrafterScreen;
import com.titanmods.titanutilities.client.TitanFactoryScreen;
import com.titanmods.titanutilities.container.TitanCrafterContainer;
import com.titanmods.titanutilities.container.TitanFactoryContainer;
import com.titanmods.titanutilities.init.ModItems;
import com.titanmods.titanutilities.init.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

// JEI integration. Only loaded by JEI itself (@JeiPlugin), so the mod still works without JEI installed.
@JeiPlugin
public class TitanUtilitiesJeiPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(TitanUtilities.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TitanFactoryCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new TitanCrafterCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipes = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(recipes.getAllRecipesFor(ModRecipes.TITAN_FACTORY_TYPE), TitanFactoryCategory.UID);
        registration.addRecipes(recipes.getAllRecipesFor(ModRecipes.TITAN_CRAFTING_TYPE), TitanCrafterCategory.UID);

        // The Raid King crown has no recipe: an "Information" page explains how to get it.
        registration.addIngredientInfo(new ItemStack(ModItems.RAID_KING.get()), VanillaTypes.ITEM,
                "jei.titanutilities.raid_king.info.1", "jei.titanutilities.raid_king.info.2", "jei.titanutilities.raid_king.info.3");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.TITAN_FACTORY.get()), TitanFactoryCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModItems.TITAN_CRAFTER.get()), TitanCrafterCategory.UID);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        // Clicking either arrow of the machine GUI opens its recipes.
        registration.addRecipeClickArea(TitanFactoryScreen.class, 34, 35, 22, 14, TitanFactoryCategory.UID);
        registration.addRecipeClickArea(TitanFactoryScreen.class, 97, 35, 22, 14, TitanFactoryCategory.UID);
        registration.addRecipeClickArea(TitanCrafterScreen.class, TitanCrafterScreen.ARROW_X, TitanCrafterScreen.ARROW_Y,
                TitanCrafterScreen.ARROW_W, TitanCrafterScreen.ARROW_H, TitanCrafterCategory.UID);
        registration.addGuiContainerHandler(TitanFactoryScreen.class, new IGuiContainerHandler<TitanFactoryScreen>() {
            @Override
            public List<Rectangle2d> getGuiExtraAreas(TitanFactoryScreen screen) {
                return Collections.singletonList(screen.getUpgradeTabArea());
            }
        });
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        // "+" button: recipe inputs go to machine slots 0-5; the player inventory follows the output and upgrade slots.
        int playerStart = TitanFactoryTileEntity.SLOT_COUNT + TitanFactoryTileEntity.UPGRADE_SLOTS;
        registration.addRecipeTransferHandler(TitanFactoryContainer.class, TitanFactoryCategory.UID,
                0, TitanFactoryTileEntity.INPUT_SLOTS, playerStart, 36);
        // Titan Crafter: grid slots 0-53, then the output slot, then the player inventory.
        registration.addRecipeTransferHandler(TitanCrafterContainer.class, TitanCrafterCategory.UID,
                0, TitanCrafterTileEntity.GRID_SLOTS, TitanCrafterTileEntity.SLOT_COUNT, 36);
    }
}
