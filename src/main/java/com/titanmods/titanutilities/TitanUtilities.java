package com.titanmods.titanutilities;

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
    }
}
