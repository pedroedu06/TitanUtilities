package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.block.TitanCrafterTileEntity;
import com.titanmods.titanutilities.block.TitanFactoryTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TitanUtilities.MOD_ID);

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<TitanFactoryTileEntity>> TITAN_FACTORY = TILE_ENTITIES.register("titan_factory",
            () -> TileEntityType.Builder.of(TitanFactoryTileEntity::new, ModBlocks.TITAN_FACTORY.get()).build(null));

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<TitanCrafterTileEntity>> TITAN_CRAFTER = TILE_ENTITIES.register("titan_crafter",
            () -> TileEntityType.Builder.of(TitanCrafterTileEntity::new, ModBlocks.TITAN_CRAFTER.get()).build(null));
}
