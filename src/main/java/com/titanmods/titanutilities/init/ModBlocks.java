package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.block.TitanCrafterBlock;
import com.titanmods.titanutilities.block.TitanFactoryBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TitanUtilities.MOD_ID);

    // First machine: two columns of 3 inputs -> 1 output, with energy.
    public static final RegistryObject<Block> TITAN_FACTORY = BLOCKS.register("titan_factory",
            () -> new TitanFactoryBlock(machineProps()));

    // Second machine: 9x7 shaped crafting with energy (final crafts).
    public static final RegistryObject<Block> TITAN_CRAFTER = BLOCKS.register("titan_crafter",
            () -> new TitanCrafterBlock(machineProps()));

    // Farming: TitanOres essence crops only grow on Blue Soil (block tag titanores:essence_crop_soil).
    public static final RegistryObject<Block> BLUE_SOIL = BLOCKS.register("blue_soil",
            () -> new Block(AbstractBlock.Properties.of(Material.DIRT)
                    .strength(0.6F)
                    .sound(SoundType.GRASS)
                    .harvestTool(ToolType.SHOVEL)));

    private static AbstractBlock.Properties machineProps() {
        return AbstractBlock.Properties.of(Material.METAL)
                .strength(3.5F)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
                .requiresCorrectToolForDrops();
    }
}
