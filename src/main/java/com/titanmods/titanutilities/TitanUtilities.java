package com.titanmods.titanutilities;

import com.titanmods.titanutilities.client.TitanCrafterScreen;
import com.titanmods.titanutilities.client.TitanFactoryScreen;
import com.titanmods.titanutilities.init.ModBlocks;
import com.titanmods.titanutilities.init.ModContainers;
import com.titanmods.titanutilities.init.ModItems;
import com.titanmods.titanutilities.init.ModRecipes;
import com.titanmods.titanutilities.init.ModTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Titan Utilities: utilities and final items for the TitanMods modpack. Requires TitanOres (see mods.toml).
@Mod(TitanUtilities.MOD_ID)
public class TitanUtilities {
    public static final String MOD_ID = "titanutilities";
    public static final Logger LOGGER = LogManager.getLogger();

    public TitanUtilities() {
        // Mod event bus: mod loading events (registries, setup, etc.).
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
        ModContainers.CONTAINERS.register(modEventBus);
        ModRecipes.SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        // Forge event bus: in-game events.
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Titan Utilities common setup");
    }

    // Client only (rendering, key bindings, screens).
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Titan Utilities client setup");
        event.enqueueWork(() -> {
            ScreenManager.register(ModContainers.TITAN_FACTORY.get(), TitanFactoryScreen::new);
            ScreenManager.register(ModContainers.TITAN_CRAFTER.get(), TitanCrafterScreen::new);
        });
    }
}
