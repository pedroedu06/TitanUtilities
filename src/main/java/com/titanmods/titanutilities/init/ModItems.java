package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// Every item in the mod is fire resistant (like netherite): see props().
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TitanUtilities.MOD_ID);

    public static final RegistryObject<Item> TITAN_FACTORY = ITEMS.register("titan_factory", () -> blockItem(ModBlocks.TITAN_FACTORY.get()));
    public static final RegistryObject<Item> TITAN_CRAFTER = ITEMS.register("titan_crafter", () -> blockItem(ModBlocks.TITAN_CRAFTER.get()));

    // Titan Factory upgrade: each one installed doubles the machine speed (see TitanFactoryTileEntity).
    public static final RegistryObject<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade", () -> new Item(props()));

    private static Item.Properties props() {
        return new Item.Properties().tab(ModItemGroup.TITAN_UTILITIES).fireResistant();
    }

    private static Item blockItem(Block block) {
        return new BlockItem(block, props());
    }
}
