package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.item.ForgeUpgradeItem;
import com.titanmods.titanutilities.item.RaidKingItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
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

    public static final RegistryObject<Item> ETHERNAL_DUST = ITEMS.register("ethernal_dust", () -> new Item(props()));

    // Hephaestus Forge (Forbidden & Arcanus) tier upgrades, made by rituals in the modpack.
    public static final RegistryObject<Item> FORGE_UPGRADE_TIER_2 = ITEMS.register("forge_upgrade_tier_2",
            () -> new ForgeUpgradeItem(props().rarity(Rarity.UNCOMMON).stacksTo(16), 2));
    public static final RegistryObject<Item> FORGE_UPGRADE_TIER_3 = ITEMS.register("forge_upgrade_tier_3",
            () -> new ForgeUpgradeItem(props().rarity(Rarity.RARE).stacksTo(16), 3));
    public static final RegistryObject<Item> FORGE_UPGRADE_TIER_4 = ITEMS.register("forge_upgrade_tier_4",
            () -> new ForgeUpgradeItem(props().rarity(Rarity.RARE).stacksTo(16), 4));
    public static final RegistryObject<Item> FORGE_UPGRADE_TIER_5 = ITEMS.register("forge_upgrade_tier_5",
            () -> new ForgeUpgradeItem(props().rarity(Rarity.EPIC).stacksTo(16), 5));

    public static final RegistryObject<Item> BLUE_SOIL = ITEMS.register("blue_soil", () -> blockItem(ModBlocks.BLUE_SOIL.get()));

    // Final items (modpack endgame components). Textures live in textures/item/finalitens/.
    public static final RegistryObject<Item> SOURCE_CRYSTAL = ITEMS.register("source_crystal",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> SPONGE_BOB = ITEMS.register("sponge_bob",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ANCESTRAL_SEED = ITEMS.register("ancestral_seed",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> WORLD_CORE = ITEMS.register("world_core",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> RAID_KING = ITEMS.register("raid_king",
            () -> new RaidKingItem(props().rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<Item> ANTENNA = ITEMS.register("antenna",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CALCULATOR = ITEMS.register("calculator",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> IRON_MAN_MASK = ITEMS.register("iron_man_mask",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MUSIC_DISC_TITANIUM = ITEMS.register("music_disc_titanium",
            () -> new Item(props().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> UNIVERSAL_REMOTE = ITEMS.register("universal_remote",
            () -> new Item(props().rarity(Rarity.EPIC)));

    private static Item.Properties props() {
        return new Item.Properties().tab(ModItemGroup.TITAN_UTILITIES).fireResistant();
    }

    private static Item blockItem(Block block) {
        return new BlockItem(block, props());
    }
}
